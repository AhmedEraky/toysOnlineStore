$(document).ready(function(){
    showDialog();
    link=window.location.pathname.split("/")[1];
    searchParams = new URLSearchParams(window.location.search);

    if(window.location.search==""){
        $('#clearFilter').hide();
    }

    $('#clearFilter').click(function () {
        window.location="shop";
    });

    $('#filterButton').click(function () {
        var cout=0;
        link="filters?";
        for(var pair of searchParams.entries()) {
            if (pair[0] != "pageNo") {
                if (cout > 0) {
                    link += "&";
                }
                link += pair[0] + "=" + pair[1];
                cout++;
            }
        }
        window.location.href=link;
    });

});

function showDialog() {
    $( "#dialog" ).dialog();
}