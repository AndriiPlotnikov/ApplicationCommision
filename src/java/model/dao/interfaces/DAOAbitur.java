/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import java.sql.SQLException;
import model.entities.Abitur;

/**
 * abitur dao interface
 *
 * @author andre
 */
public interface DAOAbitur {

    Abitur authorize(String login, String password) throws SQLException;

    void create(Abitur abitur) throws SQLException;

    Abitur read(int id) throws SQLException;

    void update(Abitur abitur) throws SQLException;

    void delete(Abitur abitur) throws SQLException;

    boolean findByName(String name) throws SQLException;

}
