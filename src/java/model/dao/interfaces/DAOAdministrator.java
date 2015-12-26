/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import java.sql.SQLException;
import model.entities.Administrator;

/**
 * administrator DAO interface
 *
 * @author andre
 */
public interface DAOAdministrator {

    Administrator authorize(String login, String password) throws SQLException;

    void create(Administrator admin) throws SQLException;

    Administrator read(int id) throws SQLException;

    void update(Administrator admin) throws SQLException;

    void delete(Administrator admin) throws SQLException;
}
