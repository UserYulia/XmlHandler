$("document").ready(function () {
    $("#add_form").hide();
    $("#rm_form").hide();
    $("#add").click(function () {
        $("#add_form").show();
        $("#rm_form").hide();
    });
    $("#delete").click(function () {
        $("#rm_form").show();
        $("#add_form").hide();
    });
});
