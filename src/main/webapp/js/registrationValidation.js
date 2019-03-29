var form = document.getElementById("registrationForm");
var currentDate = new Date();


function isDate18orMoreYearsOld(day, month, year) 
{
    return true;
}

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
	
	var birthDate = $("#inputBirthDay3").val().split('-');
	var year = birthDate[0];
	var month = birthDate[1];
	var day = birthDate[2];
	var ageCheck = isDate18orMoreYearsOld(day, month, year);
	console.log(year);
	console.log(month);
	console.log(day);
	console.log(ageCheck);
	//event.preventDefault();
	if(!ageCheck)
	{
		$("#inputBirthDay3").addClass("inputError");
		$("#invalidAge").text("You must be at least 18 years old to use our service.");
		event.preventDefault();
	}
}


form.addEventListener('submit', handleForm);