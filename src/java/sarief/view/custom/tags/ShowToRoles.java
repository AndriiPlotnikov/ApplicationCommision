/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sarief.view.custom.tags;

import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * custom tag to hide text inside tag from all, except provided roles
 *
 * @author andre
 */
public class ShowToRoles extends SimpleTagSupport {

    private static final String ROLES_SPLITTER = ",";
    private static final String GUEST_ALTERNATIVE = "guest";
    private static final String ATTR_USER_ROLE = "userRole";
    final private static boolean debug = false;

    private String roles;

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public void doTag() throws JspException, IOException {
        String[] rolesArray = roles.split(ROLES_SPLITTER);
        StringWriter sw = new StringWriter();

        PageContext pc = (PageContext) getJspContext();
        HttpServletRequest request = (HttpServletRequest) pc.getRequest();
        HttpSession session = request.getSession(false);

        String roleOfUser = session.getAttribute(ATTR_USER_ROLE) != null
                ? (String) session.getAttribute(ATTR_USER_ROLE)
                : GUEST_ALTERNATIVE;
        if (debug) {
            System.out.println(roleOfUser + "is current role!");
            System.out.println(rolesArray);
            for (String role : rolesArray) {
                System.out.println(role);
            }
        }

        for (String role : rolesArray) {
            if (role.equals(roleOfUser)) {
                getJspBody().invoke(sw);
                getJspContext().getOut().print(sw);
                return;
            }
        }
    }
}
