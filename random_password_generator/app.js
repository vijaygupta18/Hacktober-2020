$(document).ready(function(){
    var input = $("#password");
    $(".gen-pass-btn").click(function(){
        var pattern = "n@lfl$s^ufAfnlafegkn7nlbai%jqpe&p!u5+1hm|LNdfnlF{NI5}git5HWPNV5hfaon&kv*op?eqthb34kjgf1ur";
        var index = 0;
        var password = "";
        for(var i = 0;i<8;i++){
            index = Math.floor(Math.random()*(pattern.length-1));
            password += pattern[index];
        }

        input.val(password);
    });
});