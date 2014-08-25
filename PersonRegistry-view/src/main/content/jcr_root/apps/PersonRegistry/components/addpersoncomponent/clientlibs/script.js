jQuery(function($){
    $("#submit").on("click", function(event) {
        var firstName = $('#first').val();
        var lastName = $('#last').val();
        var description = $('#description').val();
//        var url = "/resource/person/add";
        var url = "/services/person";

        $.ajax({
            url: url,
            type: "POST",
            data: JSON.stringify({
                  "name" : firstName,
                  "lastName": lastName,
                  "description": description
            }),
            dataType: "html",
            contentType: "application/json; charset=utf-8",
            success: function(result) {
                $("#signup").each(function(){
                    this.reset();
                });
                $("#status").html("Status: "+result);
                $("#status").fadeIn("slow");
                $("#status").fadeOut("slow");
            }
        });
    });
});
