package com.login.service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "Stop")
public class Stop extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        session.removeAttribute("ride_id");
        //System.out.println("Merge Stop");
        String back="";
        String url="jdbc:mysql://localhost:3306/jsp_lab";
        String UserName="root";
        String password="gala";
        String query="SELECT * FROM ride WHERE ride_id IN(SELECT MAX(ride_id) FROM ride)";


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, UserName, password);
            PreparedStatement st=conn.prepareStatement(query);
            ResultSet rs=st.executeQuery();
            String list_cities="";
            while(rs.next()){
                String city=rs.getString("city");
                list_cities=list_cities+city+", ";
            }
            list_cities=list_cities.substring(0,list_cities.length()-2);
            System.out.println(list_cities);
            back="<script>\n" +
            "    $(document).ready(function() {\n" +
                    "        document.querySelector(\".back_button_butt\").style.display=\"none\";\n" +
                    "\n" +
                    "    });\n" +
                    "</script><center><p class='p_center'>Your journey with us ends here!<br>You've visited the following cities: "+list_cities+".</p></center> ";


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


        response.setContentType("text/html");
        response.getWriter().write(back);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
