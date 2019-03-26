$(window).load(function() {
    $("#flexiselDemo1").flexisel({
        visibleItems: 3,
        animationSpeed: 3000,
        autoPlay:true,
        autoPlaySpeed: 2000,
        pauseOnHover: true,
        enableResponsiveBreakpoints: true,
        responsiveBreakpoints: {
            portrait: {
                changePoint:480,
                visibleItems: 1
            },
            landscape: {
                changePoint:640,
                visibleItems:2
            },
            tablet: {
                changePoint:768,
                visibleItems: 2
            }
        }
    });

});