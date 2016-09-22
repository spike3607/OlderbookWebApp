/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Spike
 */
public class AuthorService {
    
    private AuthorDaoStrategy dao;
    
//    private List<Author> fakeDatabase = Arrays.asList(
//        new Author(1,"Sally Smith", new Date(2010, 12, 2)),
//        new Author(2,"Steve Kennedy", new Date(2014, 1, 30)),
//        new Author(3,"Kevin Rogers", new Date(2016, 5, 5)));
//    
//    public List findAllAuthors () {
//       return fakeDatabase;
//    }
    
    public List<Author> getAuthorList() throws Exception {
        return dao.getAuthorList();
    }
    
    public static void main(String[] args) {
        
    }
}
