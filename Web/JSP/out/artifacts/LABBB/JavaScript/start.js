






/*

$(document).ready(function () {
    $('#button_id').click(function(){
        //var hotel=$('#all_cities').val();
        var cities_name=document.getElementsByName("start_city");
        var cities_list=[];
        for(var i=0;i<cities_name.length;i++)
        {
            if(cities_name[i].clicked==true){
                var hotel=cities_name[i];
            }
        }
        $.ajax({
          url: 'CityProcessor',
          type: 'POST',
          data: {hotel:hotel},
          success: function(response){
              $('#current_city_space').html(response);
              $('#current_city_space').slideDown(500);
          }
       });
       return false;
    });
});*/
