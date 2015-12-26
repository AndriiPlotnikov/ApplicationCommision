/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import controller.application.properties.Paths;
import model.dao.interfaces.DAOFacultyReport;
import model.dao.interfaces.DAOFacultyEntry;
import model.dao.interfaces.DAOFaculty;
import model.dao.interfaces.DAOAdministrator;
import model.dao.interfaces.DAOAbitur;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import model.dao.interfaces.DAOSubject;

/**
 * abstract factory
 *
 * @author andre
 */
public abstract class DAOFactory {

    // path to properties file, containing factory class name

    private static final String PATH = "DAOProperties.properties";
    // codename for factory class
    private static final String PROPERTY = "dao.factory";

    public abstract DAOAbitur getDAOAbitur();

    public abstract DAOAdministrator getDAOAdministrator();

    public abstract DAOFaculty getDAOFaculty();

    public abstract DAOFacultyEntry getDAOFacultyEntry();

    public abstract DAOFacultyReport getDAOFacultyReport();

    public abstract DAOSubject getDAOSubject();

    /**
     * get concrete factory
     *
     * @return concrete factory
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static DAOFactory getFactory() throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        Properties config = new Properties();

        try (InputStream istream = DAOFactory.class.getResourceAsStream(PATH)) {
            config.load(istream);
        }

        return (DAOFactory) Class.forName(config.getProperty(PROPERTY)).newInstance();
    }

}
