package am.hitech.jdbc.controller;

import am.hitech.jdbc.model.Address;
import am.hitech.jdbc.model.User;
import am.hitech.jdbc.service.AddressService;
import am.hitech.jdbc.service.UserService;
import am.hitech.jdbc.util.exceptions.InternalServerError;
import am.hitech.jdbc.util.exceptions.NotFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        PrintWriter printWriter = resp.getWriter();
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String ageStr = req.getParameter("age");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        String homeStr = req.getParameter("home");
        String passwordStr = req.getParameter("password");
        String confirmPasswordStr = req.getParameter("confirmPassword");

        if (name == "" || surname == "" || ageStr == "" || email == "" || country == "" || city == ""
                || city == "" || street == "" || homeStr == "" || passwordStr == "" || confirmPasswordStr == "") {
            printWriter.write("empty row");
        } else if (userService.isAllSpace(name) || userService.isAllSpace(surname) || userService.isAllSpace(ageStr)
                || userService.isAllSpace(email) || userService.isAllSpace(country) || userService.isAllSpace(city)
                || userService.isAllSpace(street) || userService.isAllSpace(homeStr) || userService.isAllSpace(passwordStr)
                || userService.isAllSpace(confirmPasswordStr)) {
            printWriter.write("empty row");
        } else if (!passwordStr.equals(confirmPasswordStr)) {
            printWriter.write("password and confirm password is not equal");
        } else {
            int age = Integer.parseInt(req.getParameter(ageStr));
            int home = Integer.parseInt(req.getParameter(homeStr));
            int password = Integer.parseInt(passwordStr);
            int confirmPassword = Integer.parseInt(confirmPasswordStr);

            User user = new User();
            Address address = new Address();

            user.setId(0);
            user.setFirstName(name);
            user.setLastName(surname);
            user.setEmail(email);
            user.setAge(age);
            user.setPassword(password);
            user.setPasswordToken("0");

            try {
                userService.createUserV2(user);
            } catch (InternalServerError e) {
                throw new RuntimeException(e);
            }
            User user1 = new User();
            try {
                user1 = userService.getByUser(email);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            address.setCountry(country);
            address.setCity(city);
            address.setStreet(street);
            address.setHome(home);
            address.setUserId(user1.getId());

            AddressService addressService = new AddressService();
            try {
                addressService.createAddress(address);
            } catch (InternalServerError e) {
                throw new RuntimeException(e);
            }

        }
    }
}
