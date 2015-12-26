/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 * Dummy Command, used to test some aspekts
 *
 * @author andre
 */
class TestCommand implements Command {

    final private static String JSP_PATH = "/fakejsp.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher(JSP_PATH).forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(getClass().getName()).error(ex);
        }
    }

}
