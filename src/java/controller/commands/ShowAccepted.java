/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Facade;
import model.entities.Administrator;
import model.entities.FacultyEntry;
import org.apache.log4j.Logger;

/**
 * let the app show list of accepted entries
 *
 * @author andre
 */
public class ShowAccepted implements Command {

    final private static String JSP_PATH = "/ShowAccepted.jsp";
    private static final String ATTR_ACCEPTED = "accepted";
    private static final String PARAM_REPORT = "report";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Facade facade = new Facade();
            int id = Integer.parseInt(request.getParameter(PARAM_REPORT));
            List<FacultyEntry> accepted = facade.getReportById(id).getAccepted();
            request.setAttribute(ATTR_ACCEPTED, accepted);
            request.getRequestDispatcher(JSP_PATH).forward(request, response);
        } catch (SQLException | NumberFormatException | ServletException | IOException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(getClass().getName()).error(ex);
        }
    }

}
