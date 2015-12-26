/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Facade;
import model.entities.Administrator;
import model.entities.FacultyEntry;
import org.apache.log4j.Logger;

/**
 * admin display of entries
 *
 * @author andre
 */
class ShowEntries implements Command {

    final private static String ATTRIBUTE_ADMIN = "admin";
    final private static String JSP_PATH = "/ShowEntries.jsp";
    private static final String PARAM_CONFIRM_ACCEPTED = "confirmAccepted";
    private static final String ATTR_ACCEPTED = "accepted";
    private static final String ATTR_ENTRIES = "entries";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {

            String param = request.getParameter(PARAM_CONFIRM_ACCEPTED);
            if (param != null) {
                int entry_id = Integer.parseInt(param);

                HttpSession session = request.getSession();
                Administrator admin;
                synchronized (session) {
                    admin = (Administrator) session.getAttribute(ATTRIBUTE_ADMIN);
                }

                if (admin != null) {
                    new Facade().confirmEntry(admin, entry_id);
                }
            }

            Facade facade = new Facade();

            int id = Integer.parseInt(request.getParameter("report"));

            List<FacultyEntry> entries = facade.getReportById(id).getEntrants();
            List<FacultyEntry> accepted = facade.getReportById(id).getAccepted();
            request.setAttribute(ATTR_ENTRIES, entries);
            request.setAttribute(ATTR_ACCEPTED, accepted);
            request.getRequestDispatcher(JSP_PATH).forward(request, response);
        } catch (SQLException | NumberFormatException | ServletException | IOException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(getClass().getName()).error(ex);
        }
    }

}
