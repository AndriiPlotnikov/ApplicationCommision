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
import model.entities.Abitur;
import model.entities.FacultyReport;
import model.entities.Subject;
import org.apache.log4j.Logger;

/**
 * creates entries
 *
 * @author andre
 */
class MakeEntry implements Command {

    final private static String JSP_PATH = "/MakeEntry.jsp";
    final private static String PARAM_REPORT = "report";
    final private static String ATTRIBUTE_SUBJECTS = "subjects";
    final private static String PARAMETER_SCHOOL_SCORE = "schoolScore";
    final private static String ERROR_MSG = "errorMessage";
    final private static String ERROR_NOT_LOGGED_IN = "not logged in!";
    final private static String ERROR_NOT_ENOUGH_INFO = "not every field is set!";
    final private static String ERROR_SUBJECT_NOT_FOUND = "not found subject!";
    final private static String ERROR_SUBJECT_SCORE_BAD = "score is incorrect!";
    final private static String ERROR_SCHOOL_SCORE_BAD = "schoolScore is incorrect!";
    final private static String SUCCESS = "succesfully added!";
    private static final String ATTR_USER = "user";

    private static final String ATTR_USER_ROLE = "userRole";
    private static final String PARAM_SUBMIT = "Submit";

    /**
     * create entry by some abitur
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            checkSubmition(request, response);
            setAndGo(request, response);
        } catch (ServletException | IOException |
                ClassNotFoundException | InstantiationException |
                IllegalAccessException | SQLException ex) {
            Logger.getLogger(getClass().getName()).error(ex);
        }
    }

    /**
     * set parameters by given report and create interaction page
     *
     * @param request
     * @param response
     */
    private void setAndGo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Facade facade = new Facade();
        int report_id = 0;
        List<Subject> subjects = null;

        report_id = request.getParameter(PARAM_REPORT) == null ? 0 : Integer.parseInt(request.getParameter(PARAM_REPORT));

        subjects = facade.getSubjectsForReport(report_id);

        request.setAttribute(ATTRIBUTE_SUBJECTS, subjects);

        request.getRequestDispatcher(JSP_PATH).forward(request, response);

    }

    /**
     * if someone submited application -- check submition and create entry, if
     * everything ok
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void checkSubmition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Facade facade = new Facade();

        if (!validateSubmit(request, facade)) {
            return;
        }

        HttpSession session = request.getSession(false);
        Abitur abitur;
        synchronized (session) {
            abitur = (Abitur) session.getAttribute(ATTR_USER);
        }

        int report_id = Integer.parseInt(request.getParameter(PARAM_REPORT));
        List<Subject> subjects = facade.getSubjectsForReport(report_id);

        FacultyReport report = facade.getReportById(report_id);

        int minScore = report.getMinScore();
        int maxScore = report.getMaxScore();
        int score;
        for (Subject subject : subjects) {

            score = Integer.parseInt(request.getParameter(subject.getName()));
            subject.setScore(score);

        }

        int schoolScore = Integer.parseInt(request.getParameter(PARAMETER_SCHOOL_SCORE));

        facade.createEntry(report_id, abitur, subjects, schoolScore);
    }

    private boolean validateSubmit(HttpServletRequest request, Facade facade) throws IOException, FileNotFoundException, FileNotFoundException, ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException {
        HttpSession session = request.getSession(false);
        if (request.getParameter(PARAM_SUBMIT) == null) {
            return false;
        }
        String attr;
        synchronized (session) {
            attr = (String) session.getAttribute(ATTR_USER_ROLE);
        }
        if (session == null || !ATTR_USER.equals(attr)) {
            request.setAttribute(ERROR_MSG, ERROR_NOT_LOGGED_IN);
            return false;
        }

        if (request.getParameter(PARAM_REPORT) == null) {
            // error msg
            return false;
        }

        int report_id = Integer.parseInt(request.getParameter(PARAM_REPORT));
        if (report_id == 0) {
            request.setAttribute(ERROR_MSG, ERROR_NOT_ENOUGH_INFO);
            return false;
        }
        List<Subject> subjects = facade.getSubjectsForReport(report_id);

        if (subjects == null) {
            request.setAttribute(ERROR_MSG, ERROR_NOT_ENOUGH_INFO);
            return false;
        }

        FacultyReport report = facade.getReportById(report_id);

        if (report == null) {
            //error no such report
            return false;
        }

        int minScore = report.getMinScore();
        int maxScore = report.getMaxScore();
        int score;

        for (Subject subject : subjects) {
            if (request.getParameter(subject.getName()) == null) {
                request.setAttribute(ERROR_MSG, ERROR_SUBJECT_NOT_FOUND);
                return false;
            }

            score = Integer.parseInt(request.getParameter(subject.getName()));

            if (!(minScore <= score && score <= maxScore)) {
                request.setAttribute(ERROR_MSG, ERROR_SUBJECT_SCORE_BAD);
                return false;
            }

        }

        if (request.getParameter(PARAMETER_SCHOOL_SCORE) == null) {
            //error no school score :(
            return false;
        }

        //SUCCESS
        request.setAttribute(ERROR_MSG, SUCCESS);
        return true;
    }
}
