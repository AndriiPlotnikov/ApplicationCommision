/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import java.sql.SQLException;
import java.util.List;
import model.entities.Faculty;
import model.entities.FacultyEntry;
import model.entities.FacultyReport;

/**
 * report DAO interface
 *
 * @author andre
 */
public interface DAOFacultyReport {

    void create(FacultyReport subject) throws SQLException;

    /**
     * find by id in realization
     *
     * @param id
     * @return FacultyReport or null if not found
     * @throws java.sql.SQLException
     */
    FacultyReport read(int id) throws SQLException;

    void update(FacultyReport report) throws SQLException;

    void delete(FacultyReport report) throws SQLException;

    List<FacultyReport> getAllByFaculty(int id) throws SQLException;

    public void insertEntry(FacultyEntry entry, int report_id) throws SQLException;

}
