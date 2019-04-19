var urlParams = new URLSearchParams(window.location.search);
var signupParam = urlParams.get('signup');
var message = urlParams.get('message');
if(signupParam !== null)
{
    document.getElementById("info").style.color = "#2fe35f";
    $("#info").text("You have been registered successfully. Please login to your account.");
}

if(message === 'Wrong Email Or Password')
{
    document.getElementById("info").style.color = "#E34234";
    $("#info").text("Incorrect password or email.");
}

var myCookie = document.cookie;
console.log(myCookie);
if(myCookie != null)
{
    var cookieParts = myCookie.split("=");
    var cookieName = cookieParts[0];
    var cookieValue = unescape(cookieParts[1]);
    if (cookieName == "email" && cookieValue != "")
    {
        console.log(cookieValue);
        document.getElementById("inputEmail3").value=cookieValue ;
    }
}

function handleCookies()
{
    if (document.getElementById("gridCheck").checked)
    {
        var email = document.getElementById("inputEmail3").value;
        var myDate = new Date(2020,03,14,13,15,00);
        document.cookie = "email=" + escape(email) + ";expires=" + myDate. toUTCString();
    }
    else
    {
        var newDate = new Date();
        newDate.setTime(newDate.getTime() - 86400000);
        document.cookie = "email=;expires=" + newDate.toGMTString();
    }
    return true;
}