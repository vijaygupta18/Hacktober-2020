$(document).ready(function () {

    $("#list-items").html(localStorage.getItem("listItems"));

    $(".add-items").submit(function (event) {
        event.preventDefault();

        var item = $("#todo-list-item").val();
        if (item) {
            $("#list-items").append("<li><input class= 'checkbox' type='checkbox'/>" + item + "<a class='remove'> x </a><hr></li>");
            localStorage.setItem("listItems", $("#list-items").html());
            $("#todo-list-item").val("");
        }

    });
    $(document).on("change", ".checkbox", function () {

        if ($(this).attr("checked")) {
            $(this).removeAttr("checked");
        } else {
            $(this).attr("checked", "checked");
        }

        $(this).parent().toggleClass("completed");
        localStorage.setItem("listItems", $("#list-items").html());
    })

    $(document).on("click", ".remove", function () {
        $(this).parent().fadeOut("slow", function () { remove() });
        localStorage.setItem("listItems", $("#list-items").html());
    })

    getCurrentDate();

});



//Functon to get current date, time and to display in the top page using moment js
function getCurrentDate() {

    var currentDate = moment().format('dddd, MMMM Do YYYY');
    $("#todayDate").text(currentDate);
    return (currentDate);

}