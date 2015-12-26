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
import model.dao.interfaces.DAOFacultyEntry;
import model.entities.Abitur;
import model.entities.FacultyEntry;
import model.entities.Subject;

/**
 *
 * @author andre
 */
public class JDBCDAOFacultyEntry implements DAOFacultyEntry {

    final private static String GET_ENTRANTS
            = "SELECT `ent`.`id_entry`,`ent`.`id_abitur`,`ent`.`school_score` "
            + "FROM `ent_faculty_entry` AS `ent` "
            + "INNER JOIN `ref_report_to_entry` AS `ref` "
            + "ON (`ent`.`id_entry` = `ref`.`id_entry`) "
            + "WHERE `ref`.`id_report` = ? AND `accepted` = 0";
    final private static String GET_ACCEPTED
            = "SELECT `ent`.`id_entry`,`ent`.`id_abitur`,`ent`.`school_score` "
            + "FROM `ent_faculty_entry` AS `ent` "
            + "INNER JOIN `ref_report_to_entry` AS `ref` "
            + "ON (`ent`.`id_entry` = `ref`.`id_entry`) "
            + "WHERE `ref`.`id_report` = ? AND `accepted` != 0";
    //change this
    final private static String CREATE = "INSERT INTO `commision`.`ent_faculty_entry`"
            + " (`id_entry`, `id_abitur`, `school_score`, `accepted`) "
            + "VALUES (NULL, ?, ?, ?);";
    final private static String READ = "SELECT `id_entry`,`id_abitur`,`school_score`, `accepted` FROM `ent_faculty_entry` "
            + "WHERE `id_entry` = ? ";
    final private static String CONFIRM = "UPDATE `ent_faculty_entry` SET `accepted`=1 WHERE `id_entry` = ?";
    final private static String CONNECT_SUBJECT_AND_ENTRY = "INSERT INTO `commision`.`ref_entry_to_subject` "
            + "(`id_entry_to_subject`, `id_entry`, `id_subject`) "
            + "VALUES (NULL, ?, ?);";

    @Override
    public List<FacultyEntry> getEntrantsForFacultyReport(int id) throws SQLException {
        List<FacultyEntry> entries = new ArrayList<>();
        try (Connection con = JDBCConnection.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(GET_ENTRANTS)) {
                statement.setInt(1, id);

                ResultSet rs = statement.executeQuery();
                while (rs.next()) {

                    entries.add(createEntry(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
                }
            }
        }
        return entries;
    }

    @Override
    public List<FacultyEntry> getAcceptedForFacultyReport(int id) throws SQLException {
        List<FacultyEntry> accepted = new ArrayList<>();
        try (Connection con = JDBCConnection.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(GET_ACCEPTED)) {
                statement.setInt(1, id);

                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    accepted.add(createEntry(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
                }

            }
        }
        return accepted;
    }

    @Override
    public FacultyEntry create(FacultyEntry entry) throws SQLException {
        try (Connection con = JDBCConnection.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, entry.getAbitur().getId());
                statement.setInt(2, entry.getSchoolScore());
                // 0 in db equals to accepted --> false, 1 --> true
                statement.setInt(3, entry.isAccepted() ? 1 : 0);
                statement.execute();
                try (ResultSet key = statement.getGeneratedKeys()) {
                    if (key.next()) {

                        entry.setId(key.getInt(1));
                    }
                }
            }
        }
        fillInSubjects(entry);
        return entry;
    }

    @Override
    public FacultyEntry read(int id) throws SQLException {
        try (Connection con = JDBCConnection.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(READ)) {
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    return createEntry(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
                }
            }
        }

        return null;
    }

    @Override
    public void update(FacultyEntry entry) throws SQLException {
        
        //we do the job!
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(FacultyEntry entry) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void fillInSubjects(FacultyEntry entry) throws SQLException {
        List<Subject> subjects = entry.getSubjects();
        JDBCDAOSubject dao = new JDBCDAOSubject();
        try (Connection con = JDBCConnection.getConnection()) {
            for (Subject subject : subjects) {
                dao.create(subject);
                try (PreparedStatement statement = con.prepareStatement(CONNECT_SUBJECT_AND_ENTRY)) {
                    statement.setInt(1, entry.getId());
                    statement.setInt(2, subject.getId());
                    statement.execute();
                }
            }

        }
    }

    private FacultyEntry createEntry(int entry_id, int abitur_id, int schoolScore) throws SQLException {
        JDBCDAOAbitur dao = new JDBCDAOAbitur();

        Abitur abitur = dao.read(abitur_id);
        List<Subject> subjects = new JDBCDAOSubject().findByEntry(entry_id);
        FacultyEntry entry = new FacultyEntry(entry_id, abitur, subjects, schoolScore);
        return entry;
    }

    private FacultyEntry createEntry(int entry_id, int abitur_id, int schoolScore, int accepted) throws SQLException {
        FacultyEntry entry = createEntry(entry_id, abitur_id, schoolScore);
        entry.setAccepted(accepted != 0);

        return entry;
    }

    private void UpdateSubjects(FacultyEntry entry) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void confirm(int entry_id) throws SQLException {
        try (Connection con = JDBCConnection.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(CONFIRM)) {
                statement.setInt(1, entry_id);
                statement.executeUpdate();
            }
        }
    }

}
