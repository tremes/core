package org.jboss.weld.tests.accessibility;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tomas Remes
 */

@WebServlet(value = "/test", loadOnStartup = 1)
public class TestServlet extends HttpServlet {

    @EJB
    TestRemoteInterface testRemoteInterface;

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.getWriter().print(testRemoteInterface.getMessage());
    }
}
