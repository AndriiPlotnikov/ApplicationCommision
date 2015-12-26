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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import model.dao.interfaces.DAOFaculty;
import model.entities.Faculty;
import model.entities.FacultyReport;

/**
 *
 * @author andre
 */
class JDBCDAOFaculty implements DAOFaculty {

    private final static String READ = "SELECT name FROM ent_faculty where id_faculty = ?";
    private final static String FIND_ALL = "SELECT id_faculty, name FROM ent_faculty";

    @Override
    public Faculty read(int id) throws SQLException {
        try (Connection con = JDBCConnection.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(READ)) {
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    return buildFaculty(id, rs.getString(1));
                }
            }
        }
        return null;
    }

    @Override
    public Faculty create(Faculty faculty) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Faculty faculty) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Faculty faculty) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Faculty> readAll() throws SQLException {

        try (Connection con = JDBCConnection.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(FIND_ALL)) {
                ResultSet rs = statement.executeQuery();
                List<Faculty> faculties = new ArrayList<>();
                while (rs.next()) {
                    faculties.add(buildFaculty(rs.getInt(1), rs.getString(2)));
                }
                return faculties;
            }
        }

    }

    private Faculty buildFaculty(int id, String name) throws SQLException {
        Faculty faculty = new Faculty(id, name, null);
        faculty.setFacultyReports(new JDBCDAOFacultyReport().getAllByFaculty(faculty.getId()));
        return faculty;

    }

}
