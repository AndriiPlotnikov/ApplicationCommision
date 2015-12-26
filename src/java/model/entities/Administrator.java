/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

/**
 * administrator POJO
 * 
 * @author andre
 */
public class Administrator {

    private static final String DEFAULT_ADMIN_NAME = "System";
    final private int id;
    final private String name;

    public Administrator() {
        this.id = 0;
        this.name = DEFAULT_ADMIN_NAME; // dummy name
    }
    

    public Administrator(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
