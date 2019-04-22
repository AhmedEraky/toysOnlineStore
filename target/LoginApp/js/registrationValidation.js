var form = document.getElementById("registrationForm");
var currentDate = new Date();
var urlParams = new URLSearchParams(window.location.search);
var signupParam = urlParams.get('signup');
if(signupParam !== null)
{
	document.getElementById("inputEmail3").style.borderColor = "#E34234";
	document.getElementById("invalidEmail").style.color = "#E34234";
	$("#invalidEmail").text("A user with this email is already registered.");
}

function isDate18orMoreYearsOld(day, month, year)
{
	return new Date(year+18, month-1, day) <= new Date();
}

const getAge = birthDate => Math.floor((new Date() - new Date(birthDate).getTime()) / 3.15576e+10);

function handleForm(event)
{

	var passwordValue = document.getElementById("inputPassword3").value;
	var passwordConfirmationValue = document.getElementById("confirmPassword3").value;
	if(passwordValue != passwordConfirmationValue)
	{
		document.getElementById("inputPassword3").style.borderColor = "#E34234";
		document.getElementById("confirmPassword3").style.borderColor = "#E34234";
		document.getElementById("invalidConfirmation").innerText = "Password and confirmation must match.";
		event.preventDefault();
	}

	var birthDate = $("#inputBirthDay3").val();
	var age = getAge(birthDate);
	if(age < 18)
	{
		document.getElementById("inputBirthDay3").style.borderColor = "#E34234";
		$("#invalidAge").text("You must be at least 18 years old to use our service.");
		event.preventDefault();
	}
}


form.addEventListener('submit', handleForm);