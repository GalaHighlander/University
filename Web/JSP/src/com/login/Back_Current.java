package com.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "Back_Current")
public class Back_Current extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url="jdbc:mysql://localhost:3306/jsp_lab";
        String UserName="root";
        String password="gala";

        HttpSession session=request.getSession();
        int ride_id= (int) session.getAttribute("ride_id");
        String check_back="SELECT * FROM `ride` WHERE ride_id in(SELECT MAX(ride_id) FROM ride)";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, UserName, password);
            PreparedStatement st_check=conn.prepareStatement(check_back);
            ResultSet rs_check=st_check.executeQuery();
            int count_id=0;
            String last_ride_id="";
            while(rs_check.next()){
                count_id=count_id+1;
                last_ride_id=rs_check.getString("ride_id");
            }
            System.out.println(count_id);
            int last_id=Integer.parseInt(last_ride_id);
            System.out.println(last_ride_id);
            String back="";
            if(last_id==ride_id && count_id>1){
                String query="SELECT * FROM ride ORDER BY id DESC LIMIT 1";
                String query_delete="DELETE  FROM ride ORDER BY id DESC LIMIT 1";
                PreparedStatement st= conn.prepareStatement(query);
                PreparedStatement st_delete= conn.prepareStatement(query_delete);
                int rs_delete=st_delete.executeUpdate();
                ResultSet rs=st.executeQuery();

                String prev_city="";
                rs.next();
                String city=rs.getString("city");
                prev_city=prev_city+city;

                //System.out.println(prev_city);

                String curr="SELECT * FROM city WHERE city_name=?";
                PreparedStatement st_curr=conn.prepareStatement(curr);
                st_curr.setString(1,prev_city);
                ResultSet rs_curr=st_curr.executeQuery();

                while(rs_curr.next()){
                    String cityy = rs_curr.getString("city_name");
                    String image = rs_curr.getString("image");
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
                            cityy +
                            "</strong></p>" +
                            " <center> <input  type=\"button\"  name=\"view\"  value=\"Stop Here\" onclick=\"hideFunction()\"  id='" + cityy + "'  class=\"btn btn-info btn-xs  stop_here\"/></center>\n" +
                            "</div></center>"+
                            "<script>\n" +

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
                    back = back + html;
                }


            }
            else if(last_id==ride_id && count_id==1){
                String query_delete="DELETE  FROM ride ORDER BY id DESC LIMIT 1";
                PreparedStatement st_delete= conn.prepareStatement(query_delete);
                int rs_delete=st_delete.executeUpdate();
                back=back+"<center><p>Choose the starting city</p><center>";
                session.removeAttribute("ride_id");

            }
            else {
                back=back+"<script> alert(\"You cannot go back any further...\"); </script>";
            }
            response.setContentType("text/html");
            response.getWriter().write(back);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }




        /*String query="SELECT * FROM ride ORDER BY ride_id DESC LIMIT 1";
        String query_delete="DELETE  FROM ride ORDER BY ride_id DESC LIMIT 1";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, UserName, password);
            PreparedStatement st= conn.prepareStatement(query);
            PreparedStatement st_delete= conn.prepareStatement(query_delete);
            ResultSet rs=st.executeQuery();
            int rs_delete=st_delete.executeUpdate();
            String prev_city="";
            rs.next();
                String city=rs.getString("city");
                prev_city=prev_city+city;

            System.out.println(prev_city);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
