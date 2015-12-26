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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.dao.interfaces.DAOFacultyReport;
import model.entities.Faculty;
import model.entities.FacultyEntry;
import model.entities.FacultyReport;
import model.entities.Subject;

/**
 *
 * @author andre
 */
class JDBCDAOFacultyReport implements DAOFacultyReport {

    final static private String GET_REPORT_BY_ID
            = "SELECT year, places,open "
            + "FROM ent_faculty_report "
            + "WHERE id_report = ?";
    // здесь что-то не так. но я сонный, завтра исправлю :(
    final private static String GET_REPORTS_BY_FACULTY
            = "SELECT ent.id_report, ent.year, ent.places, ent.open "
            + "FROM ent_faculty_report as ent INNER JOIN ref_faculty_to_report as ref "
            + "ON (ent.id_report = ref.id_report) "
            + "WHERE ref.id_faculty = ?";

    final private static String CONNECT_REPORT_AND_ENTRY
            = "INSERT INTO `commision`.`ref_report_to_entry` "
            + "(`id_report_to_entry`, `id_report`, `id_entry`)"
            + " VALUES (NULL, ?, ?);";

    @Override
    public void update(FacultyReport report) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * find by id in db via jdbc
     *
     * @param id of report
     * @return FacultyReport object or null if not found
     */
    @Override
    public FacultyReport read(int id) throws SQLException {
        try (Connection con = JDBCConnection.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(GET_REPORT_BY_ID)) {
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    return buildReport(id, rs.getInt("year"),rs.getInt("places"), rs.getInt("open"));
                }
            }
        }

        return null;

    }

    @Override
    public List<FacultyReport> getAllByFaculty(int id) throws SQLException {
        List<FacultyReport> reports = new ArrayList<>();
        try (Connection con = JDBCConnection.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(GET_REPORTS_BY_FACULTY)) {
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    reports.add(buildReport(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
                }
            }
        }

        return reports;
    }

    private FacultyReport buildReport(int id, int year, int places, int open) throws SQLException {
        FacultyReport report = new FacultyReport(id, year, places, (open != 0));
        report.setSubjects(new JDBCDAOSubject().findByReport(report.getId()));
        report.setAccepted(new JDBCDAOFacultyEntry().getAcceptedForFacultyReport(id));
        report.setEntrants(new JDBCDAOFacultyEntry().getEntrantsForFacultyReport(id));

        return report;
    }

    @Override
    public void create(FacultyReport report) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(FacultyReport report) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertEntry(FacultyEntry entry, int report_id) throws SQLException {
        try (Connection con = JDBCConnection.getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(CONNECT_REPORT_AND_ENTRY)) {
                statement.setInt(1, report_id);
                statement.setInt(2, entry.getId());
                statement.execute();
            }
        }
    }

}
