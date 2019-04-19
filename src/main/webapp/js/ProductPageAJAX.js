
$('head').append('<link rel="stylesheet" href="css/shop.css" type="text/css" />');

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
                var mark=" <p class=\"bg-success\" id=\'p\'>"+data+"</p>";
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

               // ShowCustomDialog(data);


                console.log(data);
                var mark=" <p class=\"bg-success\" id=\'p\'>"+data+"</p>";
               $("#com").append(mark);


            }


        });


    });
    function ShowCustomDialog(data)
    {

        ShowDialogBox('Warning',data,'Ok','', 'GoToAssetList',null);
    }
    function ShowDialogBox(title, content, btn1text, btn2text, functionText, parameterList) {
        var btn1css;
        var btn2css;

        if (btn1text == '') {
            btn1css = "hidecss";
        } else {
            btn1css = "showcss";
        }

        if (btn2text == '') {
            btn2css = "hidecss";
        } else {
            btn2css = "showcss";
        }
        $("#lblMessage").html(content);

        $("#dialog").dialog({
            resizable: false,
            title: title,
            modal: true,
            width: '400px',
            height: 'auto',
            bgiframe: false,
            hide: { effect: 'scale', duration: 400 },

            buttons: [
                {
                    text: btn1text,
                    "class": btn1css,
                    click: function () {

                        $("#dialog").dialog('close');

                    }
                },
                {
                    text: btn2text,
                    "class": btn2css,
                    click: function () {
                        $("#dialog").dialog('close');
                    }
                }
            ]
        });


    }

    $("#send").click(function () {

        var productid = $('input#productid').val();
        var userEmail = $('[name="userEmail"]').val();
        var reviewDescription=$('[name="reviewDescription"]').val();
        var rate=$("[name='rate']:checked").val();

        $.ajax({url: "ReviewServlet",
            data: {
                "productid": productid,
                "userEmail": userEmail,
                "reviewDescription":reviewDescription,
                "rate":rate

            },
            type: "POST",

            success: function (data) {
                var email = $('input#emailUser').val();
                console.log(data);


                $("#reviewDescription").val("");
                //stars
                $("#stardiv").empty();
                var star="<input type=\"radio\" id=\"star5\" name=\"rate\" value=\"5\" />\n" +
                    "                                            <label for=\"star5\" title=\"text\">5 stars</label>\n" +
                    "                                            <input type=\"radio\" id=\"star4\" name=\"rate\" value=\"4\" />\n" +
                    "                                            <label for=\"star4\" title=\"text\">4 stars</label>\n" +
                    "                                            <input type=\"radio\" id=\"star3\" name=\"rate\" value=\"3\" />\n" +
                    "                                            <label for=\"star3\" title=\"text\">3 stars</label>\n" +
                    "                                            <input type=\"radio\" id=\"star2\" name=\"rate\" value=\"2\" />\n" +
                    "                                            <label for=\"star2\" title=\"text\">2 stars</label>\n" +
                    "                                            <input type=\"radio\" id=\"star1\" name=\"rate\" value=\"1\" />\n" +
                    "                                            <label for=\"star1\" title=\"text\">1 star</label>";
                $("#stardiv").append(star);

                //add new star
                var object=JSON.parse(data);
                var averageRaten=object.averageRate;
                var mark;
                $("#starul").empty();
                if(averageRaten == "0"){
                    for(var c=1;c<=5;c++){
                        mark="<i class=\"fa fa-star fa-2x text-muted\" aria-hidden=\"true\"></i>";
                        $("#starul").append(mark);
                    }

                }
                else{
                    for(var c=1;c<=averageRaten;c++){
                        mark="<i class=\"fa fa-star fa-2x text-primary\" aria-hidden=\"true\"></i>";
                        $("#starul").append(mark);
                    }
                    for(var c=parseInt(averageRaten)+1;c<=5;c++){
                        mark="<i class=\"fa fa-star fa-2x text-muted\" aria-hidden=\"true\"></i>";
                        $("#starul").append(mark);
                    }
                }

                //remove  all reviews
                var objectr=JSON.parse(object.reviews);
                $("#revid").empty();

                $("#revid").append("<div id=\"horizontalTab\">\n" +
                    "            <ul class=\"resp-tabs-list\">\n" +
                    "            </ul>\n" +
                    "            <div class=\"resp-tabs-container\">\n" +
                    "                <div class=\"tab2\">\n" +
                    "                    <div class=\"single_page\">\n" +
                    "                        <div  id=\"idr\" class=\"bootstrap-tab-text-grids\">\n" +
                    "                            <div  class=\"bootstrap-tab-text-grid\">");

                var rev;
                rev="<h4>Reviews</h4>";

                $("#revid").append(rev);
                if(objectr.length =="0"){
                    rev=" <p>"+"No Reviews"+"</p>\n" +
                        "<hr/>";
                    $("#revid").append(rev);
                }
                //add new reviews
                else {
                    var image;
                    for (var count = 0; count < objectr.length; count++) {
                            image=objectr[count].imagePath;
                        rev =
                            "<div class=\"bootstrap-tab-text-grid-left\">" +
                            "<img src="+image+"  class=\"img-fluid\">" +
                            "</div>" +
                            "<div class=\"bootstrap-tab-text-grid-right\">" +

                            "<ul>" +
                            "<li><a href=\"#\">"+objectr[count].userName+"</a></li>" ;

                        rev+=" <li><div class=\"rating1\"><ul class=\"stars\">";
                        for(var c=1;c<=objectr[count].rate;c++){
                            rev+="<i class=\"fa fa-star fa-2x text-primary\" aria-hidden=\"true\"></i>";
                        }
                        for(var d=parseInt(objectr[count].rate)+1;d<=5;d++){
                            rev+="<i class=\"fa fa-star fa-2x text-muted\" aria-hidden=\"true\"></i>";
                        }
                        rev+=" </li></ul>";

                        //review
                        rev+="<div class=\"container  py-lg-5 py-md-5 py-sm-4 py-4\">";

                        rev+=" <p><span>"+objectr[count].reviewDescription+"</p></span>";
                        rev+= "</div>" ;



                        $("#revid").append(rev);
                        $("#revid").append("</div>\n" +
                            "<div class=\"clearfix\"> </div>\n" +
                            " <hr/>");


                    }

                }
                $("#revid").append("<div class=\"add-review\">\n" +
                    "                                <h4>add a review</h4>\n" +
                    "                                <form action=\"\" method=\"post\">\n" +
                    "                                    <div class=\"row\">\n" +

                    "                                        <div class=\"col-md-6\">\n" +
                    "                                            <input type=\"email\" name=\"userEmail\"   id=\"userEmail\" value="+email+" readonly/>\n" +
                    "                                        </div>\n" +
                    "                                        <!--stars-->\n" +
                    "                                        <div class=\"rate\" id=\"stardiv\">\n" +
                    "                                            <input type=\"radio\" id=\"star5\" name=\"rate\" value=\"5\" />\n" +
                    "                                            <label for=\"star5\" title=\"text\">5 stars</label>\n" +
                    "                                            <input type=\"radio\" id=\"star4\" name=\"rate\" value=\"4\" />\n" +
                    "                                            <label for=\"star4\" title=\"text\">4 stars</label>\n" +
                    "                                            <input type=\"radio\" id=\"star3\" name=\"rate\" value=\"3\" />\n" +
                    "                                            <label for=\"star3\" title=\"text\">3 stars</label>\n" +
                    "                                            <input type=\"radio\" id=\"star2\" name=\"rate\" value=\"2\" />\n" +
                    "                                            <label for=\"star2\" title=\"text\">2 stars</label>\n" +
                    "                                            <input type=\"radio\" id=\"star1\" name=\"rate\" value=\"1\" />\n" +
                    "                                            <label for=\"star1\" title=\"text\">1 star</label>\n" +
                    "                                        </div>\n" +
                    "                                        <!--end-->\n" +
                    "                                    </div>\n" +
                    "                                    <textarea name=\"reviewDescription\"  id=\"reviewDescription\" rows=\"2\" cols=\"3\" placeholder=\"Enter your message\" required=\"\"></textarea>\n" +
                    "                                    <input type=\"button\" id=\"send\"  class=\"btn btn-outline-danger btn-lg\" value=\"SEND\"/>\n" +
                    "\n" +
                    "                                    <input  name=\"productid\" id=\"productid\" hidden=\"hidden\" value=\"${requestScope.product.id}\"/>\n" +
                    "                                </form>\n" +
                    "                            </div></div>");



            }


        });


    });
    });
