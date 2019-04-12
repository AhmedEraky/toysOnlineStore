
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


    $("#cart").click(function () {
        var productid = $('input#productid').val();
        var quantity=$("#selection").val();
        $.ajax({url: "ShoppingCartServlet",
            data: {
                "productid": productid,
                "quantity":quantity
            },
            type: "POST",

            success: function (data) {
                console.log(data);
                var mark=" <p class=\"bg-success\">"+data+"</p>";
                $("#com").append(mark);

            }


        });


    });

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

    });