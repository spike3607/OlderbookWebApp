/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

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
    
    @Override
    public List<Author> getAuthorList() {
        
    }
}
