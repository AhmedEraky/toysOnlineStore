var increasedProductQuantity;

$(document).ready(function(){
    displayTotalCost();
    $("div[class='entry value-plus active']").click(function(){
        var amount = $(this).prev().first();
        increasedProductQuantity = amount;
        var operation = "increaseQuantityOperation";
        var productId = $(this).parent().parent().parent().prev().prev();
        var productName = $(this).parent().parent().parent().next();
        // console.log("Operation : "+operation+" , Amount : "+amount+", Product ID : "+productId+" , Product name : "+productName+" .");
        $.post("cartModification",
            {
                operation:operation,
                productId:productId.text(),
                productName:productName.text(),
                amount: amount.text()
            }
            , addCallBack);
    });
    $("div[class='entry value-minus']").click(function(){
        var amount = $(this).next().first();
        var operation = "decreaseQuantityOperation";
        var productId = $(this).parent().parent().parent().prev().prev();
        var productName = $(this).parent().parent().parent().next();
        // console.log("Operation : "+operation+" , Amount : "+amount+", Product ID : "+productId+" , Product name : "+productName+" .");
        $.post("cartModification",
            {
                operation:operation,
                productId:productId.text(),
                productName:productName.text(),
                amount: amount.text()
            }
            , displayTotalCost);
    });
    $("div[class='rem']").click(function(){
        var amount = 0;
        var operation = "removeProductOperation";
        var productId = $(this).parent().prev().prev().prev().prev().prev();
        var productName = $(this).parent().prev().prev();
        // console.log("Operation : "+operation+", Product ID : "+productId+" , Product name : "+productName+" .");
        $.post("cartModification",
            {
                operation:operation,
                productId:productId.text(),
                productName:productName.text(),
                amount: amount.text()
            }
            , displayTotalCost);
    });
});

function displayTotalCost(){
    $(document).ready(function(){
        var totalCost = 0;
        var name ;
        var price ;
        $('#productsCheck').empty();
        $('#productsTable').find('tr').each(function (rowIndex, r) {
            $(this).find('td').each(function (colIndex, c) {
                if(colIndex==3){
                    name = c.textContent;
                }
                else if(colIndex==4){
                    price = c.textContent;
                }
                else if(colIndex==5){
                    $('#productsCheck').append("<li>"+name+"<span>"+price+" </span></li>");
                    totalCost+=parseFloat(price.substring(1));
                }
            });
        });
        $('#productsCheck').append("<li>Total <i>-</i> <span>$"+totalCost+"</span></li>");
    });
}

function addCallBack(data){
    if((data=="Sorry Fail to added to Shopping cart")||(data=="You added quantity more than the available quantity")){
        increasedProductQuantity.text(parseInt(increasedProductQuantity.text())-1);
    }
    displayTotalCost();
}