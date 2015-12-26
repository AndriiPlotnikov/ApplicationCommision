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
import model.dao.interfaces.DAOAbitur;
import model.entities.Abitur;

/**
 *
 * @author andre
 */
class JDBCDAOAbitur implements DAOAbitur {
    
    private final static String READ = "SELECT `name`, `password` FROM `ent_abitur` WHERE `id_abitur` = ?";
    private final static String READ_BY_NAME = "SELECT `id_abitur` FROM `ent_abitur` WHERE `name` = ?";
    private final static String AUTHORIZE = "select id_abitur from ent_abitur where name = ? AND password = ?";
    private final static String CREATE
            = "INSERT INTO `commision`.`ent_abitur` ( `name`, `password`) "
            + "VALUES ( ?, ?);";

    /**
     * authorize via jdbc
     *
     * @param login
     * @param password
     * @return Abitur object or null if not found
     */
    @Override
    public Abitur authorize(String login, String password) throws SQLException{
        try (Connection con = JDBCConnection.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(AUTHORIZE)) {
                statement.setString(1, login);
                statement.setString(2, password);
                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    return new Abitur(rs.getInt(1), login, password);
                }
            }
        } 
        return null;
    }

    @Override
    public void create(Abitur abitur) throws SQLException{
        try (Connection con = JDBCConnection.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(CREATE)) {
                statement.setString(1, abitur.getName());
                statement.setString(2, abitur.getPass());
                statement.execute();
            }
        }
    }

    @Override
    public Abitur read(int id) throws SQLException{
         try (Connection con = JDBCConnection.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(READ)) {
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    return new Abitur(id, rs.getString(1), rs.getString(2));
                }
            }
        } 
        return null;
    }

    @Override
    public void update(Abitur abitur) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Abitur abitur) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean findByName(String name) throws SQLException {
        try (Connection con = JDBCConnection.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(READ_BY_NAME)) {
                statement.setString(1, name);
                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    return true;
                }
            }
        } 
        return false;
    }

}
