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
            console.log(address.value=="");
            console.log(job.value=="");
            console.log(birthDate.value=="");
            console.log(credit.value=="");

        }
        else if (birthDate.value=="" || job.value=="" || address.value=="" || credit.value==""){
            console.log("///////////////////");
            document.getElementById("empty").innerText = "There's some Empty Data must be filled";
        }
        else {
            document.getElementById("profileForm").submit();
            this.value = "Edit Profile";
        }
    }
});