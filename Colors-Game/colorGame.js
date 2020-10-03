var colors = [];
var backgroundColor = "#00141a";

var message = document.querySelector("#message");
var squares = document.querySelectorAll(".square");
var colorDisplay = document.getElementById("colorDisplay");
var stripe = document.querySelector("#stripe");
var resetButton = document.querySelector("#reset");
var easyBtn = document.querySelector("#easyBtn");
var hardBtn = document.querySelector("#hardBtn");
var header = document.querySelector("h1");

colorTable(6);

var pickedColor = colorPickerRandom();

colorDisplay.textContent = pickedColor;

for (var i = 0; i < squares.length; i++) {
    //loop thro all squares to add colors and add click listener
    squares[i].style.backgroundColor = colors[i];
    squares[i].addEventListener("click", function () {
        if (this.style.backgroundColor === pickedColor) {
            //if the color is right
            colorDisplay.textContent = pickedColor;
            message.textContent = "Correct";
            message.style.color = "steelblue";
            header.style.backgroundColor = pickedColor;
            resetButton.textContent = "Play Again?";
            changeColors(pickedColor);
        } else {
            //if the color is wrong
            this.style.backgroundColor = backgroundColor;
            message.textContent = "Try Again";
            message.style.color = backgroundColor;
        }
    });
}

//function to change all background colors when we have a winner

function changeColors(correctCollor) {
    for (var j = 0; j < squares.length; j++) {
        if (squares[j].style.backgroundColor !== correctCollor) {
            squares[j].style.backgroundColor = correctCollor;
        }
    }
}

//function to pick a random number to choose from colors table

function colorPickerRandom() {
    var randomNumber = Math.floor(Math.random() * colors.length);
    return colors[randomNumber];
}

//function to pick random color

function colorTable(colomnN) {
    for (var x = 0; x < colomnN; x++) {
        colors.push("rgb(" + Math.floor(Math.random() * 256) + ", " + Math.floor(Math.random() * 256) + ", " + Math.floor(Math.random() * 256) + ")");
    }
}

//reset Button
resetButton.addEventListener("click", function () {
    this.textContent = "New Colors?";
    if (easyBtn.classList.contains("selected")) {
        message.textContent = "";
        message.style.color = backgroundColor;
        header.style.backgroundColor = backgroundColor;
        header.style.backgroundColor = "steelblue";
        colors = [];
        colorTable(3);
        pickedColor = colorPickerRandom();
        colorDisplay.textContent = pickedColor;
        for (var i = 0; i < squares.length; i++) {
            if (i < colors.length) {
                squares[i].style.backgroundColor = colors[i];
                squares[i].addEventListener("click", function () {
                    if (this.style.backgroundColor === pickedColor) {
                        //if the color is right
                        colorDisplay.textContent = pickedColor;
                        message.textContent = "Correct";
                        message.style.color = "steelblue";
                        header.style.backgroundColor = pickedColor;
                        changeColors(pickedColor);
                        resetButton.textContent = "Play Again?";
                    } else {
                        //if the color is wrong
                        this.style.backgroundColor = backgroundColor;
                        message.textContent = "Try Again";
                        message.style.color = backgroundColor;
                    }
                });
            } else {
                squares[i].style.display = "none";
            }
        }
    } else {
        message.textContent = "";
        colors = [];
        colorTable(6);
        pickedColor = colorPickerRandom();
        colorDisplay.textContent = pickedColor;
        header.style.backgroundColor = "steelblue";
        message.style.color = backgroundColor;
        for (var i = 0; i < squares.length; i++) {
            //loop thro all squares to add colors and add click listener
            squares[i].style.backgroundColor = colors[i];
            squares[i].addEventListener("click", function () {
                if (this.style.backgroundColor === pickedColor) {
                    //if the color is right
                    colorDisplay.textContent = pickedColor;
                    message.textContent = "Correct";
                    message.style.color = "steelblue";
                    header.style.backgroundColor = pickedColor;
                    changeColors(pickedColor);
                    resetButton.textContent = "Play Again?";
                } else {
                    //if the color is wrong
                    this.style.backgroundColor = backgroundColor;
                    message.textContent = "Try Again";
                    message.style.color = backgroundColor;
                }
            });
        }
    }
});

easyBtn.addEventListener("click", function () {
    hardBtn.classList.remove("selected");
    easyBtn.classList.add("selected");
    message.textContent = "";
    header.style.backgroundColor = "steelblue";
    colors = [];
    colorTable(3);
    pickedColor = colorPickerRandom();
    message.style.color = backgroundColor;
    colorDisplay.textContent = pickedColor;
    for (var i = 0; i < squares.length; i++) {
        if (i < colors.length) {
            squares[i].style.backgroundColor = colors[i];
            squares[i].addEventListener("click", function () {
                if (this.style.backgroundColor === pickedColor) {
                    //if the color is right
                    colorDisplay.textContent = pickedColor;
                    message.textContent = "Correct";
                    message.style.color = "steelblue";
                    header.style.backgroundColor = pickedColor;
                    changeColors(pickedColor);
                    resetButton.textContent = "Play Again?";
                } else {
                    //if the color is wrong
                    this.style.backgroundColor = backgroundColor;
                    message.textContent = "Try Again";
                    message.style.color = backgroundColor;
                }
            });
        } else {
            squares[i].style.display = "none";
        }
    }
});
hardBtn.addEventListener("click", function () {
    easyBtn.classList.remove("selected");
    hardBtn.classList.add("selected");
    message.textContent = "";
    message.style.color = backgroundColor;
    header.style.backgroundColor = "steelblue";
    colors = [];
    colorTable(6);
    pickedColor = colorPickerRandom();
    colorDisplay.textContent = pickedColor;
    for (var i = 0; i < squares.length; i++) {
        squares[i].style.display = "block";
        squares[i].style.backgroundColor = colors[i];
        squares[i].addEventListener("click", function () {
            if (this.style.backgroundColor === pickedColor) {
                //if the color is right
                colorDisplay.textContent = pickedColor;
                message.textContent = "Correct";
                message.style.color = "steelblue";
                header.style.backgroundColor = pickedColor;
                changeColors(pickedColor);
                resetButton.textContent = "Play Again?";
            } else {
                //if the color is wrong
                this.style.backgroundColor = backgroundColor;
                message.textContent = "Try Again";
                message.style.color = backgroundColor;
            }
        });
    }
});
