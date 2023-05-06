package am.hitech.jdbc.controller;

import am.hitech.jdbc.model.User;
import am.hitech.jdbc.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WelcomeServlet extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        PrintWriter printWriter = resp.getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (password == "" || username == "") {
            printWriter.write("Wrong login or password");
        } else if (userService.isAllSpace(username) || userService.isAllSpace(password)) {
            printWriter.write("Wrong login or password");
        } else {
            User user = userService.checkUser(username, Integer.parseInt(password));
            if (user == null) {
                printWriter.write("Wrong login or password");
            } else {
                resp.sendRedirect("homePage.html");
            }
        }
    }
}
