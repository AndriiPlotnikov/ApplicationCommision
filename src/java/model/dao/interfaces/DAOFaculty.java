/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import java.sql.SQLException;
import java.util.List;
import model.entities.Faculty;

/**
 * faculty DAO interface 
 * 
 * @author andre
 */
public interface DAOFaculty {

    Faculty create(Faculty faculty) throws SQLException;

    Faculty read(int id) throws SQLException;

    List<Faculty> readAll() throws SQLException;

    void update(Faculty faculty) throws SQLException;

    void delete(Faculty faculty) throws SQLException;

}
