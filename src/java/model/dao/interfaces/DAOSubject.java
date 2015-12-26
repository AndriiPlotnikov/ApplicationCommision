/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import java.sql.SQLException;
import java.util.List;
import model.entities.FacultyReport;
import model.entities.Subject;

/**
 * subject DAO interface
 *
 * @author andre
 */
public interface DAOSubject {

    void create(Subject subject) throws SQLException;

    Subject read(int id) throws SQLException;

    void update(Subject subject) throws SQLException;

    void delete(Subject subject) throws SQLException;

    List<Subject> findByReport(int report_id) throws SQLException;

    List<Subject> findByEntry(int entry_id) throws SQLException;
}
