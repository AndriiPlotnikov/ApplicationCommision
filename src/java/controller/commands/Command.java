/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * for classes that execute user requests
 *
 * @author andre
 */
public interface Command {

    /**
     * for responsibilities
     * 
     * @param request
     * @param response 
     */
    void execute(HttpServletRequest request, HttpServletResponse response);
}
