/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * report POJO
 * 
 * @author andre
 */
public class FacultyReport {

    final private int id;
    final private int year;
    /**
     * 0 and 200 are Ukrainian default values to subject scores
     * created with default values, because of mistake in design on my part
     * and to stay backwards compatible
     */
    private int minScore = 0;
    private int maxScore = 200;
    private int places;
    private List<Subject> subjects;
    private List<FacultyEntry> entrants;
    private List<FacultyEntry> accepted;
    private boolean open;

    public FacultyReport(int id, int year, List<Subject> subjects, List<FacultyEntry> entrants, List<FacultyEntry> accepted, int places) {
        this.id = id;
        this.year = year;
        this.entrants = entrants;
        this.accepted = accepted;
        this.subjects = subjects;
        this.places = places;
        this.open = true;
    }

    public FacultyReport(int id, int year, List<Subject> subjects, List<FacultyEntry> entrants, List<FacultyEntry> accepted, int places, boolean open) {
        this.id = id;
        this.year = year;
        this.entrants = entrants;
        this.accepted = accepted;
        this.subjects = subjects;
        this.places = places;
        this.open = open;
    }

    public FacultyReport(int id, int year, List<Subject> subjects, int places) {
        this.id = id;
        this.year = year;
        this.entrants = new ArrayList<>();
        this.accepted = new ArrayList<>();
        this.subjects = subjects;
        this.places = places;
        this.open = true;
    }

    public FacultyReport(int id, int year, int places, boolean open) {
        this.id = id;
        this.year = year;
        this.entrants = new ArrayList<>();
        this.accepted = new ArrayList<>();
        this.subjects = new ArrayList<>();
        this.places = places;
        this.open = open;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public void confirmEntry(FacultyEntry entry) {
        if (open == false) {
            throw new UnsupportedOperationException("Closed for edit");
        }
        if (entrants.contains(entry)) {
            synchronized (this) {
                if (entrants.contains(entry)) {
                    entrants.remove(entry);
                    accepted.add(entry);
                }
            }
        } else {
            throw new UnsupportedOperationException("Cannot find entry.");
        }
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public List<FacultyEntry> getEntrants() {
        return entrants;
    }
    
    public List<FacultyEntry> getAccepted() {
        return accepted;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void setAccepted(List<FacultyEntry> accepted) {
        this.accepted = accepted;
    }

    public void setEntrants(List<FacultyEntry> entrants) {
        this.entrants = entrants;
    }

    public int getMinScore() {
        return minScore;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMinScore(int minScore) {
        this.minScore = minScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public boolean isOpen() {
        return open;
    }
    
    
    
}
