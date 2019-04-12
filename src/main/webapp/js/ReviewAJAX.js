
$(document).ready(function () {
    $("#send").click(function () {
        var productid = $('input#productid').val();
        var userEmail = $('[name="userEmail"]').val();
        var userName=$('[name="userName"]').val();
        var reviewDescription=$('[name="reviewDescription"]').val();
        var rate=$("[name='rate']:checked").val();

        $.ajax({url: "ReviewServlet",
            data: {
                "productid": productid,
                "userEmail": userEmail,
                "userName":userName,
                "reviewDescription":reviewDescription,
                "rate":rate

            },
            type: "POST",

            success: function (data) {
                console.log(data);
                alert("dddd");
                 $('#userEmail').val("");
               $("#userName").val();
                $("#reviewDescription").val("");
                $("#rate").val("");

            }


        });


    });
   // setInterval(getReviews,1000);

});
function getReviews(){
    //get reviews

    $.ajax({url: "ReviewServlet",
        data:{
            "productid":productid
        },

        type: "GET",

        success: function (data) {
        alert(data);
            var object = JSON.parse(data);
            //rate
            var mark;
            var rate=parseInt(object[0]);
            if(rate==0){

                mark="<p>"+NoReview+"</p>";
                $("#starul").append(mark);
            }
            else{
                for(var c=1;c<=rate;c++){
                    mark+= "<i class=\"fa fa-star fa-2x text-primary\" aria-hidden=\"true\"></i>";
                }
                for(var j=(rate+1);j<=5;j++){
                    mark+=" <i class=\"fa fa-star fa-2x text-muted\" aria-hidden=\"true\"></i>";
                }
                $("#starul").append(mark);
            }
            var count;
            if(object[1].size()!=0){
                for (count = 0; count < object[1].size(); count++)
                {

                }
            }
        }


    });

}