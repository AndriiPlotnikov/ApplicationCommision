/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dao.interfaces.DAOAdministrator;
import model.entities.Abitur;
import model.entities.Administrator;

/**
 *
 * @author andre
 */
class JDBCDAOAdministrator implements DAOAdministrator {

    private final static String AUTHORIZE = "SELECT `id_admin` "
            + "FROM `ent_admin` "
            + "WHERE `name` = ? AND `password` = ?";
    
    @Override
    public Administrator authorize(String login, String password) throws SQLException {
        try (Connection con = JDBCConnection.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(AUTHORIZE)) {
                statement.setString(1, login);
                statement.setString(2, password);
                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    return new Administrator(rs.getInt(1), login);
                }
            }
        }
        return null;
    }

    @Override
    public void create(Administrator admin) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Administrator read(int id) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Administrator admin) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Administrator admin) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
