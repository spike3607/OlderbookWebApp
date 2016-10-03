/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Spike
 */
public class AuthorDao implements AuthorDaoStrategy {
    private DbStrategy db;
    private String driverClassName;
    private String url;
    private String userName;
    private String password;
    
    public AuthorDao(DbStrategy db, String driverClass, String url, String userName, String password) {
        this.db = db;
        this.driverClassName = driverClass;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }
    
    @Override
    public List<Author> getAllAuthors() throws Exception {
        db.openConnection(driverClassName, url, userName, password);
        List<Author> records = new ArrayList<>();

        List<Map<String,Object>> rawData = db.findAllRecords("author", 500);
        for(Map rawRec : rawData) {
            Author author = new Author();
            Object obj = rawRec.get("author_id");
            author.setAuthorId(Integer.parseInt(obj.toString()));
            
            String name = rawRec.get("author_name") == null ? "" : rawRec.get("author_name").toString();
            author.setAuthorName(name);
            
            obj = rawRec.get("date_added");
            Date dateAdded = (obj == null) ? new Date() : (Date)rawRec.get("date_added");
            author.setDateAdded(dateAdded);
            records.add(author);
        }
        
        db.closeConnection();
        
        return records;
        
    }
    
    @Override
    public void addAuthor(String name, Date date) throws Exception {
        db.openConnection(driverClassName, url, userName, password);
        
        ArrayList columns = new ArrayList();
        columns.add("author_name");
        columns.add("date_added");
        
        ArrayList values = new ArrayList();
        values.add(name);
        values.add(date);
        
        db.createRecord("author", columns, values);
        
        db.closeConnection();
    }
    
    @Override
    public void updateAuthor(Object key, String columnName, Object newObject) throws Exception {
        db.openConnection(driverClassName, url, userName, password);
        
        db.updateRecordByPrimaryKey("author", columnName, newObject, "author_id", key);
        
        db.closeConnection();
    }
    
    @Override
    public void deleteAuthor(Object key) throws Exception {
        db.openConnection(driverClassName, url, userName, password);
        
        db.deleteRecordByPK("author", "author_id", (int) key);
        
        db.closeConnection();
    }
}
