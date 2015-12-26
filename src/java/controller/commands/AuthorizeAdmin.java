/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands;

import controller.filters.AuthenticateUser;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Facade;
import model.entities.Abitur;
import model.entities.Administrator;
import org.apache.log4j.Logger;

/**
 *
 * @author andre
 */
public class AuthorizeAdmin implements Command {

    final private static String JSP_PATH = "/AuthorizeAdmin.jsp";
    final private static String JSP_PATH_HOME_REDIRECT = "/AuthorizeAdmin.jsp";
    final private static String PARAM_LOGIN = "authorizationAdminLogin";
    final private static String PARAM_PASSWORD = "authorizationAdminPassword";
    final private static String ATTRIBUTE_ADMIN = "admin";
    final private static String ATTRIBUTE_USER_ROLE = "userRole";
    final private static String ATTRIBUTE_USER_ROLE_VALUE = "admin";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String login = request.getParameter(PARAM_LOGIN);
            String password = request.getParameter(PARAM_PASSWORD);

            if (login != null && password != null) {
                try {
                    Facade facade = new Facade();
                    Administrator admin = facade.loginAdmin(login, password);
                    if (admin != null) {
                        HttpSession session = request.getSession();
                        synchronized (session) {
                            session.setAttribute(ATTRIBUTE_ADMIN, admin);
                            session.setAttribute(ATTRIBUTE_USER_ROLE, ATTRIBUTE_USER_ROLE_VALUE);
                        }

                    }
                } catch (FileNotFoundException | ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    Logger.getLogger(getClass().getName()).error(ex);
                }
            }

            request.getRequestDispatcher(JSP_PATH).forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(getClass().getName()).error(ex);
        }
    }
}
