var playerOneTurn = true;
var isClicked = false;

var p1Image = document.getElementById("p1Profile");
var p2Image = document.getElementById("p2Profile");
var p1Name = document.getElementById("p1Name");
var p2Name = document.getElementById("p2Name");

var p1DisplayScore = document.getElementById("p1PointsTotal");
var p2DisplayScore = document.getElementById("p2PointsTotal");
var p1TotalScore = 0;
var p2TotalScore = 0;

var p1TurnScore;
var p2TurnScore;
var p1TurnWord;
var p2TurnWord;

var wordInput = document.getElementById("wordInput");
var hasWordInput = false;
var maxWordLength = 15;
var wordScoreInput = document.getElementById("wordScoreInput");
var hasWordScoreInput = false;

var minWordScore = -999;
var maxWordScore = 999;

var submitButton = document.getElementById("submitButton");
var skipButton = document.getElementById("skipButton");

var iTurn = 0;
var maxTurn = 15;

p1Image.classList.toggle("isTurn");

var profilePics = [
	["p1Profile1", "p1Profile2", "p1Profile3"],
	["p2Profile1", "p2Profile2", "p2Profile3"]
]

var p1ImgNum = 0;
var p2ImgNum = 0;
var p1ImgOff;
var p2ImgOff;
p1Image.classList.toggle(profilePics[0][p1ImgNum]);
p2Image.classList.toggle(profilePics[1][p2ImgNum]);

var profileNames = [
	["Cat", "Fox", "Elephant"],
	["Dog", "Bird", "Horse"]
]
var p1NameNum = 0;
var p2NameNum = 0;
p1Name.textContent = profileNames[0][p1NameNum];
p2Name.textContent = profileNames[1][p2NameNum];

// Update profile for Player 1
p1Image.addEventListener("click", function() {
	
	// Remove last image
	p1ImgOff = p1ImgNum;
	p1Image.classList.remove(profilePics[0][p1ImgOff]);

	// Add new image from list
	if (p1ImgNum === 2) {
		p1ImgNum = Number(0);
	} else {
		p1ImgNum++;
	}
	p1Image.classList.add(profilePics[0][p1ImgNum]);

	// Add new name from list
	p1NameNum = p1ImgNum;
	p1Name.textContent = profileNames[0][p1NameNum];
})

// Update profile for Player 2
p2Image.addEventListener("click", function() {
	
	// Remove last image
	p2ImgOff = p2ImgNum;
	p2Image.classList.remove(profilePics[1][p2ImgOff]);

	// Add new image from list
	if (p2ImgNum === 2) {
		p2ImgNum = Number(0);
	} else {
		p2ImgNum++;
	}
	p2Image.classList.add(profilePics[1][p2ImgNum]);

	// Add new name from list
	p2NameNum = p2ImgNum;
	p2Name.textContent = profileNames[1][p2NameNum];
})

// Submits a valid entry for the turn
submitButton.addEventListener("click", function() {

	// If button is clicked, start timer
	beginTimer();

	// If word is less than or equal to the max allowable length
	if (wordInput.value !== "" && wordInput.value.length <= maxWordLength) {
		hasWordInput = true;
	} else {
		errorAlert(wordInput, 4, 150);
	}

	// If a valid number is entered
	if (wordScoreInput.value !== "" && wordScoreInput.value >= minWordScore && wordScoreInput.value <= maxWordScore) {
		hasWordScoreInput = true;
	} else {
		errorAlert(wordScoreInput, 4, 150);
	}

	// If both inputs are correct, process the word
	if (hasWordInput && hasWordScoreInput) {

		// Define player 1 and player 2 move cells
		defineCells();

		// Update corresponding player depending on whos turn it is
		if (playerOneTurn === true) {

			// Input player 1 data into the table
			if (iTurn < maxTurn) {
				p1TurnWord.textContent = wordInput.value;
				p1TurnScore.textContent = wordScoreInput.value;
			}

			// Update player 1's score
			p1TotalScore += Number(wordScoreInput.value);
			p1DisplayScore.textContent = p1TotalScore;

		} else {

			// Input player 2 data into the table
			if (iTurn < maxTurn) {
				p2TurnWord.textContent = wordInput.value;
				p2TurnScore.textContent = wordScoreInput.value;
			}

			// Update player 2's score
			p2TotalScore += Number(wordScoreInput.value);
			p2DisplayScore.textContent = p2TotalScore;

			iTurn++;
		}

		// Update player
		nextPlayer();

		// Reset turn data field
		resetInputFields();
	}
	
	hasWordInput = false;
	hasWordScoreInput = false;
})

function errorAlert(element, count, interval) {

	var isError = true;
	var i = 0;
	var errorAlertCount = Number(count * 2);

	functionBlink();

	function functionBlink() {
	    
	    if (isError) {
	    	element.classList.add("blinkyAlert");
	    	element.classList.remove("blinkyAlert2");

	    } else {
	    	element.classList.remove("blinkyAlert");
	    	element.classList.add("blinkyAlert2");
	    }
	    isError = !isError;
		i++;

		if(i === errorAlertCount) {
			element.classList.remove("blinkyAlert2");
		}

	    if(i < errorAlertCount) {
	        setTimeout(functionBlink, interval);
	    }
	}
}

skipButton.addEventListener("click", function() {

	// If button is clicked, start timer
	beginTimer();

	// Define player 1 and player 2 move cells
	defineCells();

	if (playerOneTurn === true) {

		// Input player 1 data into the table
		if (iTurn < maxTurn) {
			p1TurnWord.textContent = "--- SKIPPED ---";
			p1TurnWord.classList.toggle("skipped");
			p1TurnScore.textContent = "---";
			p1TurnScore.classList.toggle("skipped");
		}

	} else {

		// Input player 2 data into the table
		if (iTurn < maxTurn) {
			p2TurnWord.textContent = "--- SKIPPED ---";
			p2TurnWord.classList.toggle("skipped");
			p2TurnScore.textContent = "---";
			p2TurnScore.classList.toggle("skipped");
		}

		iTurn++;
	}

	// Reset turn data field
	resetInputFields();

	// Update player
	nextPlayer();

})

function defineCells() {
	if (iTurn <= 15) {
		p1TurnWord = document.querySelectorAll("#playerScoreboard1 tr td:nth-child(1)")[iTurn];
		p1TurnScore = document.querySelectorAll("#playerScoreboard1 tr td:nth-child(2)")[iTurn];
		p2TurnWord = document.querySelectorAll("#playerScoreboard2 tr td:nth-child(1)")[iTurn];
		p2TurnScore = document.querySelectorAll("#playerScoreboard2 tr td:nth-child(2)")[iTurn];
	}
}

function resetInputFields() {
	wordInput.value = "";
	wordScoreInput.value = "";
}

function nextPlayer() {
	p1Image.classList.toggle("isTurn");
	p2Image.classList.toggle("isTurn");
	playerOneTurn = !playerOneTurn;
}

function beginTimer() {
	if (!isClicked) {
		timer();
		isClicked = !isClicked;
	}
}

function timer() {
    window.markDate = new Date();
    $(document).ready(function() {
        $("div.absent").toggleClass("present");
    });
    updateClock();
}

function updateClock() {  
    var currDate = new Date();
    var diff = currDate - markDate;
    document.getElementById("timer").innerHTML = format(diff/1000);
    setTimeout(function() {updateClock()}, 1000);
}

function format(seconds)
{
var numhours = parseInt(Math.floor(((seconds % 31536000) % 86400) / 3600),10);
var numminutes = parseInt(Math.floor((((seconds % 31536000) % 86400) % 3600) / 60),10);
var numseconds = parseInt((((seconds % 31536000) % 86400) % 3600) % 60,10);
    return ((numhours<10) ? "0" + numhours : numhours)
    + ":" + ((numminutes<10) ? "0" + numminutes : numminutes)
    + ":" + ((numseconds<10) ? "0" + numseconds : numseconds);
}