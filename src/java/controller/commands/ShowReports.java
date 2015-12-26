/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Facade;
import org.apache.log4j.Logger;

/**
 * list of the faculty report
 *
 * @author andre
 */
public class ShowReports implements Command {

    final private static String JSP_PATH = "/ShowReports.jsp";
    final private static String PARAM_FACULTY = "faculty";
    final private static String PARAM_REPORTS = "Reports";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Facade facade = new Facade();

            int id = Integer.parseInt(request.getParameter(PARAM_FACULTY));

            request.setAttribute(PARAM_REPORTS, facade.getFacultyReports(id));

            request.getRequestDispatcher(JSP_PATH).forward(request, response);
        } catch (SQLException | ServletException | IOException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(getClass().getName()).error(ex);
        }
    }

}
