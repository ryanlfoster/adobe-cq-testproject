jQuery(function($){
    $(".removable").on("click", function(event) {
        var itemId = $(this).attr("id").replace("del",'');
        var url = "/services/person?itemId="+itemId;
        $.ajax({
            url: url,
            type: "DELETE",
            dataType: "html",
            success: function(result) {
                location.reload();
            }
        });
    });
});
