/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import model.dao.DAOFactory;
import model.dao.interfaces.DAOFaculty;
import model.entities.Abitur;
import model.entities.Administrator;
import model.entities.Faculty;
import model.entities.FacultyEntry;
import model.entities.FacultyReport;
import model.entities.Subject;

/**
 *
 * @author andre
 */
public class Facade {

    /**
     * authorize administrator
     *
     * @param login - admins login
     * @param password - his password
     * @return administrator if found, null if not found
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Administrator loginAdmin(String login, String password) throws IOException, FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        return DAOFactory.getFactory().getDAOAdministrator().authorize(login, password);
    }

    /**
     * login abitur if entry data is correct
     *
     * @param login - user login
     * @param password - user password
     * @return user if found, null - if not found
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Abitur loginAbitur(String login, String password) throws IOException, FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        return DAOFactory.getFactory().getDAOAbitur().authorize(login, password);
    }

    /**
     * create entry by provided values
     *
     * @param abitur
     * @param subjects
     * @param schoolScore
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void createEntry(int report_id, Abitur abitur, List<Subject> subjects, int schoolScore) throws IOException, FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        FacultyEntry entry = new FacultyEntry(0, abitur, subjects, schoolScore);
        entry = DAOFactory.getFactory().getDAOFacultyEntry().create(entry);
        if (entry.getId() != 0) {
            DAOFactory.getFactory().getDAOFacultyReport().insertEntry(entry, report_id);
        } else {
            throw new UnsupportedOperationException("0 in get id");
        }

    }

    /**
     * take entry from entrants to accepted
     *
     * @param master - admin, the one making decision
     * @param entry_id
     * @param report
     * @param entry
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void confirmEntry(Administrator master, int entry_id) throws IOException, FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        DAOFactory.getFactory().getDAOFacultyEntry().confirm(entry_id);
    }

    /**
     * find fully build Faculty Report
     *
     * @param id - identificator
     * @return FacultyReport object or null if not found
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public FacultyReport getReportById(int id) throws IOException, FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        return DAOFactory.getFactory().getDAOFacultyReport().read(id);
    }

    /**
     * find list of reports for given faculty
     *
     * @param id of faculty
     * @return list of faculty reports
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public List<FacultyReport> getFacultyReports(int id) throws IOException, FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        return DAOFactory.getFactory().getDAOFacultyReport().getAllByFaculty(id);
    }

    /**
     * create faculty by given name
     *
     * @param facultyName
     * @return created Faculty object
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Faculty createFaculty(String facultyName) throws IOException, FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        return DAOFactory.getFactory().getDAOFaculty().create(new Faculty(facultyName));
    }

    /**
     * find faculty with provided id
     *
     * @param id
     * @return found faculty, or null if not found
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Faculty getFacultyById(int id) throws IOException, FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        return DAOFactory.getFactory().getDAOFaculty().read(id);
    }

    /**
     * get list of all faculties
     *
     * @return list of faculties
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public List<Faculty> getAllFaculties() throws IOException, FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        return DAOFactory.getFactory().getDAOFaculty().readAll();
    }

    /**
     * register user by provided name and pass
     *
     * @param name
     * @param pass
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void createAbitur(String name, String pass) throws IOException, FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        DAOFactory.getFactory().getDAOAbitur().create(new Abitur(name, pass));
    }

    /**
     * find subjects needed for specified report
     *
     * @param report_id id of report in question
     * @return List of subjects or null if not found
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public List<Subject> getSubjectsForReport(int report_id) throws IOException, FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        FacultyReport rep = getReportById(report_id);
        return rep == null ? null : rep.getSubjects();
    }

    public boolean checkAbiturExists(String name)  throws IOException, FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        return DAOFactory.getFactory().getDAOAbitur().findByName(name);
    }

}
