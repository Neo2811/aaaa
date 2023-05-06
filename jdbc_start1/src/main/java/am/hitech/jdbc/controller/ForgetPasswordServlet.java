package am.hitech.jdbc.controller;

import am.hitech.jdbc.model.User;
import am.hitech.jdbc.repo.UserRepo;
import am.hitech.jdbc.service.UserService;
import am.hitech.jdbc.util.exceptions.NotFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ForgetPasswordServlet extends HttpServlet {
    int count = 0;
    String email;
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        if (count == 0) {
            email = req.getParameter("username");
            PrintWriter printWriter = resp.getWriter();
            User user = null;
            try {
                user = userService.getByUser(email);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            if (user == null) {
                printWriter.write("email does not exist");

            } else {
                try {
                    userService.updatePasswordToken(email);
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
                resp.sendRedirect("forgetPassword2.html");
                count = 1;
            }
        }else if (count == 1) {

            String username = req.getParameter("username");
            String passwordToken = req.getParameter("passwordToken");
            String password = req.getParameter("password");
            String confirmPassword = req.getParameter("confirmPassword");
            PrintWriter printWriter = resp.getWriter();

            if (username.equals(email) && password.equals(confirmPassword) && passwordToken.equals(userService.getPasswordToken(username))) {

                try {
                    userService.updateUserPassword(email,Integer.parseInt(password));
                } catch (NotFoundException e) {
                    throw new RuntimeException(e);
                }
                printWriter.write("password changed");
                count = 0;
            }else {
                printWriter.write("try again something went wrong");
            }
        }

    }
}
