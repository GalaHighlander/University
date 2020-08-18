<%--
<jsp:useBean id="uname" scope="request" type=""/>
--%>
<%@ page import="java.sql.*" %>
<%@ page import="com.mysql.cj.x.protobuf.MysqlxDatatypes" %>
<%@ page import="com.mysql.cj.Session" %><%--
  Created by IntelliJ IDEA.
  User: Gala
  Date: 5/10/2020
  Time: 3:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/home_6.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <title>Home Page</title>
    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script> src="JavaScript/start.js"</script>
    <script> src="JavaScript/jsLib.js"</script>

</head>
<body>

<%

    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");

    if(session.getAttribute("uname")==null)
    {
        response.sendRedirect("index.jsp");
    }

%>

<div class="head_container">
    <div class="user_container">
        <div class="user_text">
            <h2><b>Home page</b></h2>
            <h3 class="uText">Hi ${uname},<br /> Are you ready to travel with us?</h3>
        </div>
        <div class="image_user">
            <img src="img/ava.png" class="avabro"/>
        </div>
    </div>
    <div class="buttons_container">
        <form action=logout>
            <input type="submit" id="back_button" value="Logout">
        </form>
            <a href="aboutus.jsp"><input name="booking_button" type="button" id="booking_button" value="About Us"/></a>
    </div>
</div>





<div class="conttt">
    <div class="filter_column">
        <center><p class="text"><b> Current City</b></p></center>
        <center><input type="button" class="back_button_butt"  value="BACK" <%--onclick="back_function()"--%>></center>
        <script>
            $('.back_button_butt').click(function (){
                if(confirm("Do you really mind to go back to the previous station?")){
                    $.ajax({
                        url:'Back_Current',
                        type:'POST',
                        encode:true,
                        success: function(res){
                            $('#current_city_space').html(res)
                        }
                    });
                    $.ajax({
                        url:'Back_Neighbour',
                        type:'POST',
                        encode:true,
                        success: function(response){
                            $('#result').html(response)
                        }
                    })
                }
            });
        </script>
        <center><button type="button" class="TOT btn btn-info btn-lg" data-toggle="modal" data-target="#myModal" <%--onclick="myFunction()"--%>>Select Starting City</button></center>
        <div id="current_city_space" class="current_city_space">

        </div>
    </div>

    <div class="menu_columnX">
        <center> <p class="text"><b> Surrounding Cities </b></p> </center>
        <div class="filter_data" id="result">
            <%
                String url="jdbc:mysql://localhost:3306/jsp_lab";
                String UserName="root";
                String password="gala";
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn= DriverManager.getConnection(url, UserName, password);
                String query="SELECT * FROM city";
                PreparedStatement st=conn.prepareStatement(query);
                ResultSet rs=st.executeQuery();
                while(rs.next()){
                    String city=rs.getString("city_name");
                    String image=rs.getString("image");
                    String html="<div class='cell'>" +
                                "    <img src='img/"+
                            image +"'"+
                            " class='img-responsive'>" +
                                    " <p align='center'><strong>" +
                            city+
                            "</strong></p>" +
                            //" <center> <input  type=\"button\"  name=\"view\"  value=\"Next\"  id='"+city+"'  class=\"btn btn-info btn-xs view_data\"/></center>\n"+
                                "</div>";
                    out.print(html);
                }
            %>

        </div>
    </div>
</div>


<script>
    function showFunction() {
        document.querySelector(".back_button_butt").style.display="block";

    }
</script>




<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"  id="red_butt" data-dismiss="modal" onclick="showFunction()">&times;</button>
                <h4 class="modal-title">Choose the starting city</h4>
            </div>
            <div class="modal-body filter_data">

                    <%
                        rs.beforeFirst();
                        while(rs.next()){
                            String city=rs.getString("city_name");
                            String image=rs.getString("image");
                            String html="<div class='cell_start'>" +
                                    "    <img src='img/"+
                                    image +"'"+
                                    " class='img-responsive_start'>" +
                                    " <p align='center'><strong>" +
                                    city+
                                    "</strong></p>" +

                                    "<center><form id=\"formstart\" >"+
                                    "<input type=hidden id='passed'>"+
                                    "<input type='hidden' id=\"all_cities\" name='start_city' value='"+city+"'>"+
/*
                                    " <center> <input  type=\"submit\"  name=\""start_city'  value='"+city+"' onclick=\"myFunction()\" id='button_id'  class=\"btn btn-info btn-xs view_data\"/></center>\n"+
*/
                                    "<center><input type=\"checkbox\" class=\"product_check\" value=\""+city+"\" id='cities'></center>\n"+
                                    /*
                                    "<input type=\"submit\""+
*/
                                    "</form></center>"+

                                    "</div>";
                            out.print(html);
                        }
                    %>

                    <script>
                        $(document).ready(function(){


                            $(".product_check").click(function () {
                                //$('#cities').prop("checked",false);
                                var cities = get_filter_text('cities');
                                var conf=confirm("Are you sure that you want to start from "+cities);
                                if(conf==true) {

                                    $.ajax({
                                        url: 'CityProcessor',
                                        type: 'POST',
                                        data: {"Cities": cities},
                                        encode: true,
                                        success: function (response) {
                                            $('#current_city_space').html(response);
                                            $('#current_city_space').slideDown(500);

                                        }

                                    });
                                    $.ajax({
                                        url: 'CityAdder',
                                        type: 'POST',
                                        data: {"Cities": cities},
                                        encode: true,
                                        success: function (response) {
                                            $('#result').html(response);

                                        }

                                    });
                                }
                            });
                        });

                            function get_filter_text(text_id) {
                                var filterData = [];
                                $('#' + text_id + ':checked').each(function () {
                                    filterData.push($(this).val());
                                });
                                return filterData;
                            }

                    </script>

            </div>
            <div class="modal-footer">
                <button type="button" id="close_red" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>

</body>
</html>
<script>

</script>