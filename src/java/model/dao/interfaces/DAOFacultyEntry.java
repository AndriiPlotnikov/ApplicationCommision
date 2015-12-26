/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import java.sql.SQLException;
import java.util.List;
import model.entities.FacultyEntry;

/**
 * entry DAO interface
 *
 * @author andre
 */
public interface DAOFacultyEntry {

    /**
     * get list of entrants
     *
     * @param facultyReportId - id of report
     * @return list if found, null if not
     */
    List<FacultyEntry> getEntrantsForFacultyReport(int facultyReportId) throws SQLException;

    /**
     * get list of accepted
     *
     * @param facultyReportId - id of report
     * @return list if found, null if not
     */
    List<FacultyEntry> getAcceptedForFacultyReport(int facultyReportId) throws SQLException;

    FacultyEntry create(FacultyEntry entry) throws SQLException;

    FacultyEntry read(int id) throws SQLException;

    void update(FacultyEntry entry) throws SQLException;

    void confirm(int entry_id) throws SQLException;

    void delete(FacultyEntry entry) throws SQLException;
}
