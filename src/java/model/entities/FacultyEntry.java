/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.util.HashMap;
import java.util.List;

/**
 * entry POJO
 * 
 * @author andre
 */
public class FacultyEntry {

    private int id;
    final private Abitur abitur;
    final private List<Subject> subjects;
    final private int schoolScore;
    private boolean accepted;
    private int scoreSum = 0;

    public FacultyEntry(int id, Abitur author, List<Subject> subjects, int schoolScore) {
        this.id = id;
        this.abitur = author;
        this.subjects = subjects;
        this.schoolScore = schoolScore;
        this.accepted = false;
        countScore();
    }

    public FacultyEntry(int id, Abitur abitur, List<Subject> subjects, int schoolScore, boolean accepted) {
        this.id = id;
        this.abitur = abitur;
        this.subjects = subjects;
        this.schoolScore = schoolScore;
        this.accepted = accepted;
        countScore();
    }

    public int getId() {
        return id;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public int getSchoolScore() {
        return schoolScore;
    }

    public Abitur getAbitur() {
        return abitur;
    }

    /**
     * find score of subject
     *
     * @param subjectName
     * @return score or null if not found
     */
    public Integer getSubjectScore(String subjectName) {
        for (Subject subject : subjects) {
            if (subjectName.equalsIgnoreCase(subject.getName())) {
                return subject.getScore();
            }
        }
        return null;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public void setId(int id) {
        this.id = id;
    }

    private void countScore() {
        int temp = 0;
        for(Subject subj : subjects){
            temp += subj.getScore();
        }
        scoreSum = temp;
    }

    public int getScoreSum() {
        return scoreSum;
    }
    
    
    
    
}
