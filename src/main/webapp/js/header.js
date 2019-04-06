$( window ).on( "load",
    function () {
        if (window.location.href.substring(window.location.href.length-4, window.location.href.length)=="home"){
            $("#nav-home").addClass("active")
        }else if (window.location.href.substring(window.location.href.length-4, window.location.href.length)=="shop") {
            $("#nav-shop").addClass("active");
        }else if (window.location.href.substring(window.location.href.length-5, window.location.href.length)=="about") {
            $("#nav-about").addClass("active");
        }else if (window.location.href.substring(window.location.href.length-5, window.location.href.length)=="login") {
            $("#nav-login").addClass("active");
        }else if (window.location.href.substring(window.location.href.length-12, window.location.href.length)=="registration") {
            $("#nav-registration").addClass("active");
        }
        $("#search-input").css({'color':'red'});
    });