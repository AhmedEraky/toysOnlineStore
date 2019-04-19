$(document).ready(function () {
    $("#searhUser").click(function () {
        var userName = $('#name').val();

        $.ajax({
            url: "retrieveUser",
            data: {
                "name": userName
            },
            type: "POST",

            success: function (data) {
                console.log(data);
                var user=JSON.parse(data);

                $("#usersTableList").html("");
                for (i=0;i<user.length;i++){
                    $("#usersTableList").append(
                        "<tr>\n" +
                        "                        <td>"+user[i].email+"</td>\n" +
                        "                        <td>"+user[i].name+"</td>\n" +
                        "                        <td>"+user[i].creditLimit+"</td>\n" +
                        "                        <td><a class=\"btn btn-primary\" href=\"home?email="+user[i].email+"\">view Information</a></td>\n" +
                        "                    </tr>"
                    );
                }
            }


        })
    });
});