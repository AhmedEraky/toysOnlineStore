$(document).ready(function(){
    $("div[class='entry value-plus active']").click(function(){
        var amount = $(this).prev().first().text();
        var operation = "increaseQuantityOperation";
        var productId = $(this).parent().parent().parent().prev().prev().text();
        var productName = $(this).parent().parent().parent().next().text();
        // console.log("Operation : "+operation+" , Amount : "+amount+", Product ID : "+productId+" , Product name : "+productName+" .");
        $.post("cartModification",
                    {
                        operation:operation,
                        productId:productId,
                        productName:productName,
                        amount: amount
                    }
            , ajaxCallBack);
    });
    $("div[class='entry value-minus']").click(function(){
        var amount = $(this).next().first().text();
        var operation = "decreaseQuantityOperation";
        var productId = $(this).parent().parent().parent().prev().prev().text();
        var productName = $(this).parent().parent().parent().next().text();
        // console.log("Operation : "+operation+" , Amount : "+amount+", Product ID : "+productId+" , Product name : "+productName+" .");
        $.post("cartModification",
                    {
                        operation:operation,
                        productId:productId,
                        productName:productName,
                        amount: amount
                    }
            , ajaxCallBack);
    });
    $("div[class='rem']").click(function(){
        var amount = 0;
        var operation = "removeProductOperation";
        var productId = $(this).parent().prev().prev().prev().prev().prev().text();
        var productName = $(this).parent().prev().prev().text();
        // console.log("Operation : "+operation+", Product ID : "+productId+" , Product name : "+productName+" .");
        $.post("cartModification",
                    {
                        operation:operation,
                        productId:productId,
                        productName:productName,
                        amount: amount
                    }
            , ajaxCallBack);
    });
});

function ajaxCallBack(){
    console.log("Operation in progress.....");
}