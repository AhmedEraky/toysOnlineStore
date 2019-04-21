var increasedProductQuantity;
var increasedProductCost;
var callBackMessage = "";

$(document).ready(function () {

    setInterval(displayTotalCost, 2000);

    $("div[class='entry value-plus active']").click(function () {
        var amount = $(this).prev().first();
        increasedProductQuantity = amount;
        var operation = "increaseQuantityOperation";
        var productId = $(this).parent().parent().parent().prev().prev();
        var productName = $(this).parent().parent().parent().next();
        var productTotalCost = $(this).parent().parent().parent().next().next();
        increasedProductCost = productTotalCost;
        ajaxCall(operation, productId.text(), productName.text(), amount.text());
        if(callBackMessage=="Successfully added to Shopping cart"){
            console.log("increaseing quantity");
            console.log(increaseCost(parseInt(amount.text()),parseFloat(productTotalCost.text())).toFixed(1));
            productTotalCost.text(increaseCost(parseInt(amount.text()),parseFloat(productTotalCost.text())).toFixed(1));
            amount.text(parseInt(amount.text())+1);
        }
        displayTotalCost();
    });

    $("div[class='entry value-minus']").click(function () {
        var amount = $(this).next().first();
        if(parseInt(amount.text())>1){
            var operation = "decreaseQuantityOperation";
            var productId = $(this).parent().parent().parent().prev().prev();
            var productName = $(this).parent().parent().parent().next();
            var productTotalCost = $(this).parent().parent().parent().next().next();
            ajaxCall(operation, productId.text(), productName.text(), amount.text());
            if(callBackMessage=="Successfully decreased product quantity from Shopping cart"){
                productTotalCost.text(decreaseCost(parseInt(amount.text()),parseFloat(productTotalCost.text())).toFixed(1));
                amount.text(parseInt(amount.text())-1);
            }
            displayTotalCost();
        }
    });

    $("div[class='rem']").click(function () {
        var amount = 0;
        var operation = "removeProductOperation";
        var productId = $(this).parent().prev().prev().prev().prev().prev();
        var productName = $(this).parent().prev().prev();
        var productRow  = $(this).parent().parent();
        ajaxCall(operation, productId.text(), productName.text(), amount);
        if(callBackMessage=="Successfully removed product from Shopping cart"){
            productRow.fadeOut('slow', function (c) {
                productRow.remove();
            });
        }
        displayTotalCost();
    });

});

function ajaxCallBack(data){
    callBackMessage = data;
    console.log(callBackMessage);
}

function displayTotalCost() {
    var totalCost = 0;
    var name;
    var price;

    $('#productsCheck').empty();
    $('#productsTable').find('tr').each(function (rowIndex, r) {
        $(this).find('td').each(function (colIndex, c) {
            if (colIndex == 3) {
                name = c.textContent;
            }
            else if (colIndex == 4) {
                price = c.textContent;
            }
            else if (colIndex == 5) {
                $('#productsCheck').append("<li>" + name + "<span>" + price + " </span></li>");
                totalCost += parseFloat(price);
            }
        });
    });
    $('#productsCheck').append("<li>Total <i>-</i> <span>" + totalCost + "</span></li>");
    updateNumberOfShoppingCartItems();

}

function decreaseCost(oldquantity, oldCost) {
    return oldCost - (oldCost / parseFloat(oldquantity));
}

function increaseCost(oldquantity, oldCost) {
    return oldCost + (oldCost / parseFloat(oldquantity));
}

function ajaxCall(operation, productId, productName, amount) {
    console.log("Operation : "+operation+" , Amount : "+amount+", Product ID : "+productId+" , Product name : "+productName+" .");
    $.ajaxSetup({async: false});
    $.post("cartModification",
        {
            operation: operation,
            productId: productId,
            productName: productName,
            amount: amount
        }
        , ajaxCallBack);
    // console.log("Operation : "+operation+" , Amount : "+amount+", Product ID : "+productId+" , Product name : "+productName+" .");
}

function updateNumberOfShoppingCartItems (){
    $('#numberOfShoppingCartItems').text($('#productsTable tbody tr').length);
}
