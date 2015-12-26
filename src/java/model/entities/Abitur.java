/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

/**
 * Abitur POJO
 * 
 * @author andre
 */
public class Abitur {

    final private int id;
    final private String name;
    final private String pass;

    public Abitur(String name, String pass) {
        this.id = 0;
        this.name = name;
        this.pass = pass;
    }

    public Abitur(int id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

}
