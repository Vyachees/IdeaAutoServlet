package com.example.ideaautoservlet;

import java.io.*;

import com.example.ideaautoservlet.model.User;
import com.example.ideaautoservlet.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "users", value = "/users")
public class HelloServlet extends HttpServlet {

    private final ObjectMapper mapper = new ObjectMapper();

    private final UserService userService = new UserService();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        var is = req.getInputStream();
        User user = mapper.readValue(is, User.class);
        userService.createNewUser(user);
        var os = resp.getOutputStream();
        mapper.writeValue(os,user);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

        Long id= Long.valueOf(req.getParameter("id"));
        User user = userService.getUserById(id );

        if (user == null){
            resp.setStatus(404);
            return;
        }

        var os=resp.getOutputStream();
        mapper.writeValue(os,user);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id= Long.valueOf(req.getParameter("id"));
        String company=req.getParameter("company");
        User user = userService.getUserById(id);
        user.setCompany(company);
        var os=resp.getOutputStream();
        mapper.writeValue(os,user);
    }

    //delete
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id= Long.valueOf(req.getParameter("id"));

        String result = userService.deleteUser(id);

        var os=resp.getOutputStream();
        mapper.writeValue(os,result);
    }

    public void destroy() {

    }

}
