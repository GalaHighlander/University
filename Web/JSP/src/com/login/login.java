package com.login;

import com.login.dao.LoginDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "LoginServlet")
public class login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=  request.getParameter("username");
        String password=  request.getParameter("password");
        LoginDao dao=new LoginDao();
        try {
            //System.out.println("OK BABEEEEEEEEEEEEEEEE");

            if(dao.check(username, password))
            {
                HttpSession session=request.getSession();
                session.setAttribute("uname",username);
                response.sendRedirect("home.jsp");
            }
            else {
                response.sendRedirect("index.jsp");
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
