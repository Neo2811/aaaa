package am.hitech.jdbc.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class SessionServlet extends HttpServlet {
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession();
        Integer sum = (Integer) session.getAttribute("sum");

        if (sum == null) {
            sum = 1;
            session.setAttribute("sum", sum);
        }else {
            session.setAttribute("sum",sum + 1);
        }


        PrintWriter printWriter = resp.getWriter();
        printWriter.write(sum.toString());
    }
}
