/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.jdbc;

import model.dao.interfaces.DAOAbitur;
import model.dao.interfaces.DAOAdministrator;
import model.dao.DAOFactory;
import model.dao.interfaces.DAOFaculty;
import model.dao.interfaces.DAOFacultyEntry;
import model.dao.interfaces.DAOFacultyReport;
import model.dao.interfaces.DAOSubject;

/**
 *
 * @author andre
 */
public class JDBCDAOFactory extends DAOFactory {

    @Override
    public DAOAbitur getDAOAbitur() {
        return new JDBCDAOAbitur();
    }

    @Override
    public DAOAdministrator getDAOAdministrator() {
        return new JDBCDAOAdministrator();
    }

    @Override
    public DAOFaculty getDAOFaculty() {
        return new JDBCDAOFaculty();
    }

    @Override
    public DAOFacultyEntry getDAOFacultyEntry() {
        return new JDBCDAOFacultyEntry();
    }

    @Override
    public DAOFacultyReport getDAOFacultyReport() {
        return new JDBCDAOFacultyReport();
    }

    @Override
    public DAOSubject getDAOSubject() {
        return new JDBCDAOSubject();
    }

}
