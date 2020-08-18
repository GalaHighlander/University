package com.login;

import com.login.dao.LoginDao;
import com.login.dao.RegisterDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "register")
public class register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=  request.getParameter("username");
        String password=  request.getParameter("password");
        String cpassword=  request.getParameter("cpassword");
        String email=request.getParameter("email");

        RegisterDao regDao=new RegisterDao();
        LoginDao dao=new LoginDao();
        if(dao.valid_password(password,cpassword)) {


            try {
                if (dao.check_register(username, password)) {

                    response.sendRedirect("index.jsp");

                } else {

                    regDao.register(username, password, email);

                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            response.sendRedirect("register.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
