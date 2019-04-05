address = document.getElementById("address");
job = document.getElementById("job");
email = document.getElementById("email");
credit = document.getElementById("credit");

document.getElementById("edit").addEventListener('click',function() {
    document.getElementById("birthDate").disabled = false;
    address.disabled = false;
    job.disabled = false;
    email.disabled = false;
    credit.disabled = false;
    document.getElementById("edit").innerHTML = "Save";}
    );