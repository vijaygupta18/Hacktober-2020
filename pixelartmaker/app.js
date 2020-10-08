$(document).ready(() => {
    //varibles needed for the app
    const btn = $("#get-grid")
    const output = $("#output")
    const clr = $("#clr-grid")
    //input elements
    const num1 = $("#num1")
    const num2 = $("#num2")
    //color chooser
    let color;
    //get the grid
    btn.on("click", () => {
        if(num2.val() > 33){
            alert("Please select columns less than 33")
            return false;
        }
        for (let i = 0; i < num1.val(); i++) {
            for (let k = 0; k < num2.val(); k++) {
                output.prepend("<div class='box'></div>")
            }
            output.prepend("<br>")
        }
        //draw by dragging click
        let mouseLeft = false;
        
        output.find("div").on("mousemove", function () {
            if (mouseLeft) {
             color = $("#color-picker").val()
            $(this).css({ "background": color })
            }
        })
    
        output.find("div").on('mousedown', function () {
            mouseLeft = true;
        });
    
        output.find("div").on('mouseup', function () {
            mouseLeft = false;
        });
        //Eraser
        output.find("div").contextmenu(function (e) {
            e.preventDefault()
            $(this).css({ "background": 'none' })
        })
      
    })
    //remove the grid
    clr.on('click', () => {
        output.html(" ")
        $("#num1").val("")
        $("#num2").val("")
    })


})





