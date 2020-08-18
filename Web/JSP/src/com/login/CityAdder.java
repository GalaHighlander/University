package com.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "CityAdder")
public class CityAdder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        int index=str.indexOf("=")+1;
        String city_final_name=str.substring(index);

        String url="jdbc:mysql://localhost:3306/jsp_lab";
        String UserName="root";
        String password="gala";
        String neighbour="SELECT * FROM relation WHERE city=?";


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, UserName, password);
            PreparedStatement st_neigh=conn.prepareStatement(neighbour);
            st_neigh.setString(1,city_final_name);
            ResultSet rs_neigh=st_neigh.executeQuery();
            String neigh_result="";
            int idx=0;
            List<String> neigh_name=new ArrayList<String>();
            String list="(";
            while(rs_neigh.next())
            {
                neigh_name.add(rs_neigh.getString("neighbour"));
                list=list+"'"+ rs_neigh.getString("neighbour")+"'";
                list=list+",";
            }
            //System.out.println(neigh_name.get(0));
            String query_neigh="Select * from city Where city_name IN "+list+"'ok')";
            PreparedStatement st=conn.prepareStatement(query_neigh);
            ResultSet rs=st.executeQuery();
            String back = "";

            while (rs.next())
            {
                String city = rs.getString("city_name");
                String image = rs.getString("image");
                String html = "<div class='cell'>" +
                        " <center>   <img src='img/" +
                        image + "'" +
                        " class='img-responsive'></center>" +
                        " <p align='center'><strong>" +
                        city +
                        "</strong></p>" +
                        " <center> <input  type=\"button\"  name=\"view\"  value=\"Next\"  id='" + city + "'  class=\"btn btn-info btn-xs  next_data\"/></center>\n" +

                        "</div>";
                back = back + html;
            }
            back=back+"<script>\n" +
                    "    $(\".next_data\").click(function(){\n" +
                    "        var next_city = $(this).attr(\"id\");\n" +
                    "        if(confirm(\"Are you sure that you want to visit \"+next_city+\"?\")){\n" +
                    "            $.ajax({\n" +
                    "               url:'Next',\n" +
                    "               type:'POST',\n" +
                    "               data: {\"Cities\":next_city},\n" +
                    "               encode:true,\n" +
                    "               success: function (res) {\n" +
                    "                   $('#current_city_space').html(res);\n" +
                    "               }\n" +
                    "            });\n" +
                    "            $.ajax({\n" +
                    "                url: 'CityAdder',\n" +
                    "                type: 'POST',\n" +
                    "                data: {\"Cities\": next_city},\n" +
                    "                encode: true,\n" +
                    "                success: function (response) {\n" +
                    "                    $('#result').html(response);\n" +
                    "\n" +
                    "                }\n" +
                    "\n" +
                    "            });\n" +
                    "        }\n" +
                    "    });\n" +
                    "\n" +
                    "\n" +
                    "</script>";
            response.setContentType("text/html");
            response.getWriter().write(back);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
