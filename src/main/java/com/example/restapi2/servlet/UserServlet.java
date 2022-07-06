package com.example.restapi2.servlet;

import com.example.restapi2.DB.UserDB;
import com.example.restapi2.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    private UserDB userDB;

    public void init() {
        userDB = new UserDB();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       PrintWriter printWriter = response.getWriter();
       List<User> getAllUser = userDB.getInformationAboutUsers();
       for (User user : getAllUser) {
           printWriter.println(user.toString());
       }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        Integer age = Integer.valueOf(request.getParameter("age"));
        PrintWriter printWriter = response.getWriter();

        if (age > 0 && age < 100 && firstname.length() > 0 && firstname.length() < 30) {
            User user = new User(firstname, lastname, age);
            userDB.saveUser(user);
            printWriter.println(user.getFirstname() + " " + user.getLastname() + " you registered successfully");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        userDB.deleteUser(id);
    }
}
