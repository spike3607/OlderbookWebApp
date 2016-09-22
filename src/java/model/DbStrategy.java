/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Spike
 */
public interface DbStrategy {

    void closeConnection() throws Exception;

    List<Map> findAllRecords(String tableName, int maxRecords) throws Exception;

    void openConnection(String driverClass, String url, String userName, String password) throws Exception;
    
}
