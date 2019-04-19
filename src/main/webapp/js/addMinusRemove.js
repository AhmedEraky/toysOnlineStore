var canDecreaseCost = true;
var increasedProductQuantity;
var increasedProductCost;

$(document).ready(function () {
    displayTotalCost();
    $("div[class='entry value-plus active']").click(function () {

        canDecreaseCost = true;
        var amount = $(this).prev().first();
        increasedProductQuantity = amount;
        var operation = "increaseQuantityOperation";
        var productId = $(this).parent().parent().parent().prev().prev();
        var productName = $(this).parent().parent().parent().next();
        var productTotalCost = $(this).parent().parent().parent().next().next();
        increasedProductCost = productTotalCost;

        var oldquantity = parseFloat(amount.text()) - 1;
        var oldCost = parseFloat(productTotalCost.text().substring(1));
        productTotalCost.text("$" + increaseCost(oldquantity, oldCost).toFixed(2));

        increaseAjaxCall(operation, productId.text(), productName.text(), amount.text());

    });
    $("div[class='entry value-minus']").click(function () {
        var amount = $(this).next().first();
        var operation = "decreaseQuantityOperation";
        var productId = $(this).parent().parent().parent().prev().prev();
        var productName = $(this).parent().parent().parent().next();
        var productTotalCost = $(this).parent().parent().parent().next().next();

        if (canDecreaseCost) {
            var currentQuantity = parseFloat(amount.text());
            var oldquantity = parseFloat(amount.text()) + 1;
            var oldCost = parseFloat(productTotalCost.text().substring(1));
            productTotalCost.text("$" + decreaseCost(oldquantity, oldCost).toFixed(2));
            if (currentQuantity == 1) { canDecreaseCost = false; }
        }

        decreaseAndRemoveAjaxCall(operation, productId.text(), productName.text(), amount.text());
    });
    $("div[class='rem']").click(function () {
        var amount = 0;
        var operation = "removeProductOperation";
        var productId = $(this).parent().prev().prev().prev().prev().prev();
        var productName = $(this).parent().prev().prev();

        decreaseAndRemoveAjaxCall(operation, productId.text(), productName.text(), amount);
    });
});

function addCallBack(data){
    if((data=="Sorry Fail to added to Shopping cart")||(data=="You added quantity more than the available quantity")){
        increasedProductQuantity.text(parseInt(increasedProductQuantity.text())-1);
        increasedProductCost.text("$" + decreaseCost(parseFloat(increasedProductQuantity.text()) + 1, parseFloat(increasedProductCost.text().substring(1))).toFixed(2));
    }
    displayTotalCost();
}

function displayTotalCost() {
    $(document).ready(function () {
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
                    totalCost += parseFloat(price.substring(1));
                }
            });
        });
        $('#productsCheck').append("<li>Total <i>-</i> <span>$" + totalCost + "</span></li>");
    });
}

function decreaseCost(oldquantity, oldCost) {
    return oldCost - (oldCost / oldquantity);
}

function increaseCost(oldquantity, oldCost) {
    return oldCost + (oldCost / oldquantity);
}

function increaseAjaxCall(operation, productId, productName, amount) {
    $.post("cartModification",
        {
            operation: operation,
            productId: productId,
            productName: productName,
            amount: amount
        }
        , addCallBack);
    // console.log("Operation : "+operation+" , Amount : "+amount+", Product ID : "+productId+" , Product name : "+productName+" .");
}


function decreaseAndRemoveAjaxCall(operation, productId, productName, amount) {
    $.post("cartModification",
        {
            operation: operation,
            productId: productId,
            productName: productName,
            amount: amount
        }
        , displayTotalCost);
    // console.log("Operation : "+operation+" , Amount : "+amount+", Product ID : "+productId+" , Product name : "+productName+" .");
}

