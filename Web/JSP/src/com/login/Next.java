package com.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.stream.Collectors;

@WebServlet(name = "Next")
public class Next extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String str = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        int index=str.indexOf("=")+1;
        String city_final_name=str.substring(index);
        System.out.println(city_final_name);

        String url="jdbc:mysql://localhost:3306/jsp_lab";
        String UserName="root";
        String password="gala";
        String query="Select * from city Where city_name=?";
        String insert_check="SELECT DISTINCT ride_id FROM ride";
        String insert="INSERT INTO `ride`(`ride_id`, `username`, `city`) VALUES(?,?,?)";

        HttpSession session=request.getSession();
        String username= (String) session.getAttribute("uname");
        if(city_final_name.length()>2) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, UserName, password);
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, city_final_name);
/*
            CHECK FOR THE NUMBER OF RIDES
*/

                int next_ride= (int) session.getAttribute("ride_id");

                PreparedStatement st_insert = conn.prepareStatement(insert);
                st_insert.setString(1, String.valueOf(next_ride));
                st_insert.setString(2, username);
                st_insert.setString(3, city_final_name);
                int rs_insert = st_insert.executeUpdate();

/*
            GET AND DISPLAY THE CURRENT CITY
*/
                ResultSet rs = st.executeQuery();
                response.setContentType("text/html");
                String back = "";
                while (rs.next()) {
                    String city = rs.getString("city_name");
                    String image = rs.getString("image");
                    String html = "<script>\n" +
                            "    function hideFunction() {\n" +
                            "        document.querySelector(\".current_city_space\").style.display=\"none\";\n" +
                            "\n" +
                            "    }\n" +
                            "</script><center><div class='cell'>" +
                            " <center>  <img src='img/" +
                            image + "'" +
                            " class='img-responsive'></center>" +
                            " <p align='center'><strong>" +
                            city +
                            "</strong></p>" +
                            " <center> <input  type=\"button\"  name=\"view\"  value=\"Stop Here\" onclick=\"hideFunction()\"  id='" + city + "'  class=\"btn btn-info btn-xs  stop_here\"/></center>\n" +
                            "</div></center>";
                    back = back + html;
                }
                back=back+"<script>\n" +

                        "                            $(\".stop_here\").click(function () {\n" +
                        "                                 if(confirm(\"Are sure that you want to stop here?\")){\n"+
                        "                                var cities=\"caca\";\n" +
                        "                                $.ajax({\n" +
                        "                                    url: 'Stop',\n" +
                        "                                    type: 'POST',\n" +
                        "                                    data: {\"Cities\":cities},\n" +
                        "                                    encode:true,\n" +
                        "                                    success:function(response){\n" +
                        "                                        $('#result').html(response);\n" +
                        "                                        $('.').style.display=\"flex\";\n" +

                        "                                    }\n" +
                        "\n" +
                        "                                });\n" +
                        "                               }\n"+
                        "                            });\n" +

                        "                    </script>";
                response.setContentType("text/html");
                response.getWriter().write(back);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
