
$(document).ready(function () {
    $("#wishes").click(function () {
        var productid = $('input#productid').val();

        $.ajax({url: "ProductHandling",
            data: {
                "productid": productid
            },
            type: "POST",

            success: function (data) {
                console.log(data);
                var mark=" <p class=\"bg-success\">"+data+"</p>";
                $("#com").append(mark);

            }


        });


    });


});