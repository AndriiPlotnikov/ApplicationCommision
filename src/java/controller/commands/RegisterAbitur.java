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
 * register new system user
 * 
 * @author andre
 */
public class RegisterAbitur implements Command {

    final private static String JSP_PATH = "/RegisterAbitur.jsp";

    final private static String PARAM_NAME = "login";
    final private static String PARAM_PASS = "password";
    final private static String PARAM_CONFIRM = "confirm";
    final private static String PARAM_SUBMIT = "Submit";
    final private static String ERROR_MSG = "errorMessage";
    final private static String ERROR_ABITUR_EXISTS = "user with that name is already registred!";
    final private static String ERROR_NOT_ENOUGH_INFO = "not every field is set!";
    final private static String ERROR_PASS_NOT_MATCH = "passwords don't match!";
    final private static String SUCCESS = "succesfully added!";
    private String msg = null;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = request.getParameter(PARAM_NAME);
            String pass = request.getParameter(PARAM_PASS);
            String confirm = request.getParameter(PARAM_CONFIRM);
            String submit = request.getParameter(PARAM_CONFIRM);
            if (validate(submit, name, pass, confirm)) {
                new Facade().createAbitur(name, pass);
            }
            request.setAttribute(ERROR_MSG, msg);
            request.getRequestDispatcher(JSP_PATH).forward(request, response);
        } catch (ServletException | IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(getClass().getName()).error(ex);
        }
    }

    private boolean validate(String submit, String name, String pass, String confirm)   throws IOException, FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        if(submit == null){
            return false;
        }
        if (name == null || pass == null || confirm == null
                || name.isEmpty() || pass.isEmpty() || confirm.isEmpty()) {
            msg = ERROR_NOT_ENOUGH_INFO;
            return false;
        }
        if (!pass.equals(confirm)) {
            msg = ERROR_PASS_NOT_MATCH;
            return false;
        }
        if (new Facade().checkAbiturExists(name)) {
            msg = ERROR_ABITUR_EXISTS;
            return false;
        }
        msg = SUCCESS;
        return true;
    }

}
