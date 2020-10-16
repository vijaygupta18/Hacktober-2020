$(document).ready(function(){
    var input = $("#password");
    $(".gen-pass-btn").click(function(){
        var pattern = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*(){}[]:;"'<,>.?/";
        var index = 0;
        var password = "";
        for(var i = 0;i<8;i++){
            index = Math.floor(Math.random()*(pattern.length-1));
            password += pattern[index];
        }

        input.val(password);
    });
});
