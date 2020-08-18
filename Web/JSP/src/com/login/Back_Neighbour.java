package com.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Back_Neighbour")
public class Back_Neighbour extends HttpServlet {
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
                String q_more="SELECT * FROM ride WHERE id in(SELECT MAX(id)-1 FROM ride)";
                PreparedStatement st_more=conn.prepareStatement(q_more);
                ResultSet rs_more=st_more.executeQuery();
                String city_name="";
                while(rs_more.next()){
                    city_name=rs_more.getString("city");
                }
                String neighbour="SELECT * FROM relation WHERE city=?";
                PreparedStatement st_neigh=conn.prepareStatement(neighbour);
                st_neigh.setString(1,city_name);
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
            }
            else if(last_id==ride_id && count_id==1){

                String curr="SELECT * FROM city ";
                PreparedStatement st_curr=conn.prepareStatement(curr);
                ResultSet rs_curr=st_curr.executeQuery();

                while(rs_curr.next()){
                    String cityy = rs_curr.getString("city_name");
                    String image = rs_curr.getString("image");
                    String html ="<div class='cell'>" +
                            "    <img src='img/"+
                            image +"'"+
                            " class='img-responsive'>" +
                            " <p align='center'><strong>" +
                            cityy+
                            "</strong></p>" +
                            //" <center> <input  type=\"button\"  name=\"view\"  value=\"Next\"  id='"+city+"'  class=\"btn btn-info btn-xs view_data\"/></center>\n"+
                            "</div>"+
                            " <script>\n" +
                            "                    $(document).ready(function() {\n" +
                            "                        document.querySelector(\".back_button_butt\").style.display=\"none\";\n" +
                            "\n" +
                            "                    });\n" +
                            "                </script>";
                    back = back + html;
                }

            }
            else {
                back=back+"<script> alert(\"You cannot go back any further...\"); </script>";
            }
            response.setContentType("text/html");
            response.getWriter().write(back);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
