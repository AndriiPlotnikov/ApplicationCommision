/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

/**
 * Subject POJO
 * 
 * @author andre
 */
public class Subject {

    int id;
    final private String name;
    private int score;

    public Subject(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setId(int id) {
        this.id = id;
    }

}
