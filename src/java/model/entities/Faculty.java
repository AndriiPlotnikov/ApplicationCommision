/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.util.List;

/**
 * faculty POJO
 * 
 * @author andre
 */
public class Faculty {

    final private int id;
    final private String name;
    private List<FacultyReport> facultyReports;

    /**
     * empty, non identified faculty.
     *
     * @param name - faculty name
     */
    public Faculty(String name) {
        this.id = 0;
        this.name = name;
        this.facultyReports = null;
    }

    /**
     * fully build faculty
     *
     * @param id - identifier
     * @param name - name
     * @param facultyReports list of reports
     */
    public Faculty(int id, String name, List<FacultyReport> facultyReports) {
        this.id = id;
        this.name = name;
        this.facultyReports = facultyReports;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<FacultyReport> getFacultyReports() {
        return facultyReports;
    }

    public void setFacultyReports(List<FacultyReport> facultyReports) {
        this.facultyReports = facultyReports;
    }

}
