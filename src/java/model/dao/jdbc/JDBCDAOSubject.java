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
import model.dao.interfaces.DAOSubject;
import model.entities.FacultyReport;
import model.entities.Subject;

/**
 *
 * @author andre
 */
class JDBCDAOSubject implements DAOSubject {

    private final static String READ_SUBJECTS_OF_REPORT
            = "SELECT ent.id_subject,subject_name, score "
            + "FROM ent_subject AS ent "
            + "INNER JOIN ref_report_to_subject AS ref "
            + "ON (ent.id_subject = ref.id_subject) "
            + "WHERE id_report = ?";
    final private static String READ_SUBJECTS_OF_ENTRY
            = "SELECT ent.id_subject,subject_name, score "
            + "FROM ent_subject AS ent "
            + "INNER JOIN ref_entry_to_subject AS ref "
            + "ON (ent.id_subject = ref.id_subject) "
            + "WHERE ref.id_entry = ?";
    final private static String CREATE = "INSERT INTO `commision`.`ent_subject` "
            + "(`id_subject`, `subject_name`, `score`) "
            + "VALUES (NULL, ?, ?);";

    @Override
    public void create(Subject subject) throws SQLException {
        try (Connection con = JDBCConnection.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, subject.getName());
                statement.setInt(2, subject.getScore());
                statement.execute();
                
                try (ResultSet key = statement.getGeneratedKeys()) {
                    if (key.next()) {

                        subject.setId(key.getInt(1));
                    }
                }
            }
        }
    }

    @Override
    public Subject read(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Subject subject) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Subject subject) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Subject> findByReport(int report_id) throws SQLException {
        List<Subject> subjects = new ArrayList<>();
        try (Connection con = JDBCConnection.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(READ_SUBJECTS_OF_REPORT)) {
                statement.setInt(1, report_id);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    subjects.add(new Subject(rs.getInt(1), rs.getString(2), rs.getInt(3)));
                }
            }
        }

        return subjects;
    }

    @Override
    public List<Subject> findByEntry(int entry_id) throws SQLException {

        List<Subject> subjects = new ArrayList<>();
        try (Connection con = JDBCConnection.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(READ_SUBJECTS_OF_ENTRY)) {

                statement.setInt(1, entry_id);

                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    subjects.add(new Subject(rs.getInt(1), rs.getString(2), rs.getInt(3)));
                }
            }
        }

        return subjects;
    }

}
