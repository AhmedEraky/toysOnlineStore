birthDate = document.getElementById("birthDate");
address = document.getElementById("address");
job = document.getElementById("job");
credit = document.getElementById("credit");

document.getElementById("edit").addEventListener('click',function() {
    birthDate.disabled = false;
    address.disabled = false;
    job.disabled = false;
    credit.disabled = false;
    if(this.value == "Edit Profile"){
        this.value="Save";
    }
    else {
        document.getElementById("profileForm").submit();
        this.value="Edit Profile";

    }
});