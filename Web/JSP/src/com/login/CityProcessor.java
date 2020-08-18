package com.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.stream.Collectors;

@WebServlet(name = "CityProcessor")
public class CityProcessor extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*
        EXTRACT THE CITY NAME
*/
        PrintWriter out = response.getWriter();
        String str = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        int index=str.indexOf("=")+1;
        String city_final_name=str.substring(index);
        System.out.println(city_final_name);

/*
        QUERY PART
*/
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
                PreparedStatement st_check = conn.prepareStatement(insert_check);
                ResultSet rs_check = st_check.executeQuery();
                int nr_rides = 0;
                int next_ride = 0;
                while (rs_check.next()) {
                    nr_rides += 1;
                }
                next_ride = nr_rides + 1;
                session.setAttribute("ride_id", next_ride);
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
                response.setContentType("text/html");
                response.getWriter().write(back);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cities_var=request.getParameter("Cities");

        String url="jdbc:mysql://localhost:3306/jsp_lab";
        String UserName="root";
        String password="gala";
        String query="Select * from city Where city_name=?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn= DriverManager.getConnection(url, UserName, password);
            PreparedStatement st=conn.prepareStatement(query);
            st.setString(1,cities_var);

            ResultSet rs=st.executeQuery();
            PrintWriter out=response.getWriter();
            response.setContentType("text/html");
            String back="sjgksdflnfdsnhnfglnkfgdnblnfglhnfd";

            while(rs.next()) {
                String city = rs.getString("city_name");
                String image = rs.getString("image");
                String html = "<div class='cell'>" +
                        "    <img src='img/" +
                        image + "'" +
                        " class='img-responsive'>" +
                        " <p align='center'><strong>" +
                        city +
                        "</strong></p>" +
                        " <center> <input  type=\"button\"  name=\"view\"  value=\"Next\"  id='" + city + "'  class=\"btn btn-info btn-xs view_data\"/></center>\n" +
                        "</div>"+
                        "<script>\n" +
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
                back=back+html;
            }
            System.out.println(back);

            response.setContentType("text/html");
            response.getWriter().write(back);
            /*response.sendRedirect("home.jsp");*/

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        /*out.println(cities1);*/

        /*Map<String, Object> map = new HashMap<String, Object>();
        String city = request.getParameter("caca");
        map.put("city",city);
        write(response,map);*/

    }
}
