/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 *
 * @author Spike
 */
public class MySqlDBStrategy implements DbStrategy {
    private Connection conn;
    
    @Override
    public void openConnection(String driverClass, String url, String userName, 
                                String password) throws Exception {
        
        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }
    
    @Override
    public void closeConnection() throws Exception {
        conn.close();
    }
    
    @Override
    public List<Map<String, Object>> findAllRecords(String tableName, int maxRecords) throws Exception {
        String sql = "SELECT * FROM " + tableName + " LIMIT " + maxRecords;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Map<String, Object>> records = new ArrayList<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        
        while(rs.next()) {
            Map<String,Object> record = new LinkedHashMap<>();
            for (int i = 1; i < colCount+1; i++) {
                String colName = rsmd.getColumnName(i);
                Object colData = rs.getObject(colName);
                record.put(colName, colData);
            }
            records.add(record);
        }
        return records;
    }
    
    @Override
    public int deleteRecordByPK(String tableName, String primaryKey, int value) throws Exception {
        //DELETE FROM author WHERE author_id = 1
        String sql = "DELETE FROM " + tableName + " WHERE " + primaryKey + " = " + value;
        Statement stmt = conn.createStatement();
        int recordsUpdated = stmt.executeUpdate(sql);
        return recordsUpdated;
    }
    
    public void createRecord(String tableName, List<String> colNames, List<Object> colValues) {
        //INSERT INTO author (author_id, author_name, date_added) VALUES ('4', 'max maxian', '2009-12-24')
        String sql = "INSERT INTO " + tableName;
        StringJoiner sj = new StringJoiner(", "," (",")");
        for(String colName : colNames) {
            sj.add(colName);
        }
        sql += sj.toString();
        sql += " VALUES ";
    }
    
    public static void main(String[] args) throws Exception {
        MySqlDBStrategy db = new MySqlDBStrategy();
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
        List<Map<String, Object>> records = db.findAllRecords("author", 500);
        System.out.println(records);
        db.closeConnection();
    }
}
