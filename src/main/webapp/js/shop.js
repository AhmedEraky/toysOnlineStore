$(document).ready(function(){
    if(window.location.search==""){
        $('#clearFilter').hide();
    }
    $('#clearFilter').click(function () {
        window.location="shop";
    })
    $('#filterButton').click(function () {
        window.location="filters";
    })

})