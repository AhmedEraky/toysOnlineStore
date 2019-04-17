birthDate = document.getElementById("birthDate");
address = document.getElementById("address");
job = document.getElementById("job");
credit = document.getElementById("credit");
email = document.getElementById("email");

document.getElementById("edit").addEventListener('click',function() {
    birthDate.disabled = false;
    address.disabled = false;
    job.disabled = false;
    credit.disabled = false;
    email.disabled = false;
    email.readOnly = true;

    if(this.value == "Edit Profile"){
        this.value="Save";
    }
    else {
        if (credit.value < 0){
            credit.style="border-color: #E34234;";
            document.getElementById("invalidCredit").innerText = "invalid entry";
        }
        else {
            document.getElementById("profileForm").submit();
            this.value = "Edit Profile";
        }
    }
});