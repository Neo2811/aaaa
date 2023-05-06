package am.hitech.jdbc.controller;

import am.hitech.jdbc.model.Number;
import am.hitech.jdbc.model.User;
import am.hitech.jdbc.model.UserAddress;
import am.hitech.jdbc.service.NumberService;
import am.hitech.jdbc.service.UserAddressService;
import am.hitech.jdbc.service.UserService;
import am.hitech.jdbc.util.exceptions.NotFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class HelloServlet extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String name = req.getParameter("name");
//        String s = req.getParameter("surname");
//        String id = req.getParameter("id");
//
//        PrintWriter printWriter = resp.getWriter();
//        printWriter.write("Hello " + name + " " + s);
//
//        UserService userService = new UserService();
//        User user = new User();
//        try {
//           user =  userService.getById((Integer.parseInt(id)));
//           printWriter.write(user.toString());
//
//        } catch (NotFoundException e) {
//            throw new RuntimeException(e);
//        }





//        NumberService numberService = new NumberService();
//        List<Number> list = new ArrayList<>();
//        try {
//            list = numberService.info();
//        } catch (NotFoundException e) {
//            e.printStackTrace();
//        }
//
//        PrintWriter printWriter = resp.getWriter();
//        for (int i = 0; i < list.size(); i++) {
//            printWriter.write(list.get(i).getNumber()+" "+list.get(i).getFirstname()+" "+list.get(i).getLastname()+"\n");
//        }



//        UserAddressService userAddressService = new UserAddressService();
//        List<UserAddress> list2;
//        try {
//            list2 = userAddressService.getAllUserAddress();
//        } catch (NotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        PrintWriter printWriter = resp.getWriter();
//        for (int i = 0; i < list2.size(); i++) {
//            if (list2.get(i).getCountry() == null) {
//                printWriter.write(list2.get(i).getFirstname() + " " + list2.get(i).getLastname() + " No address\n");
//            }else {
//                printWriter.write(list2.get(i).getFirstname() + " " + list2.get(i).getLastname() + " "
//                        + list2.get(i).getCountry() + " " + list2.get(i).getCity() + " "
//                        + list2.get(i).getStreet() + " " + list2.get(i).getHome() + "\n");
//            }
//        }

    }
}
