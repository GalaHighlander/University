<%--
  Created by IntelliJ IDEA.
  User: Gala
  Date: 5/10/2020
  Time: 3:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AboutUs</title>
    <link rel="stylesheet" href="css/home.css">

</head>
<body>
About Us .... Bla bla
<a href="home.jsp"><input name="booking_button" type="button" id="booking_button" value="About Us"/></a>
<script>
    $(".next_data").click(function(){
        var next_city = $(this).attr("id");
        if(confirm("Are you sure that you want to visit "+next_city+"?")){
            $.ajax({
               url:'Next',
               type:'POST',
               data: {"Cities":next_city},
               encode:true,
               success: function (res) {
                   $('#current_city_space').html(res);
               }
            });
            $.ajax({
                url: 'CityAdder',
                type: 'POST',
                data: {"Cities": next_city},
                encode: true,
                success: function (response) {
                    $('#result').html(response);

                }

            });
        }
    });


</script>
</body>
</html>
