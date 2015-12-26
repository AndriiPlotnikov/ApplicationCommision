/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands;

import java.io.IOException;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Facade;

/**
 * show list of faculties
 *
 * @author andre
 */
class ShowFaculties implements Command {

    final private static String JSP_PATH = "/ShowFaculties.jsp";
    final private static String ATTR_FACULTIES = "Faculties";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Facade facade = new Facade();

            request.setAttribute(ATTR_FACULTIES, facade.getAllFaculties());
            request.getRequestDispatcher(JSP_PATH).forward(request, response);
        } catch (SQLException | ServletException | IOException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(getClass().getName()).error(ex);
        }
    }

}
