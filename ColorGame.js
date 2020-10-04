var numSquares=6;
var colors=[];
var picked;

var cDisplay= document.getElementById("tag");
var message= document.getElementById("message");
var heading= document.querySelector("h2");
var reset= document.getElementById("reset");
var squares= document.querySelectorAll(".square");
var modeBtn= document.querySelectorAll(".mode");

for(var i=6;i<9;++i)
	squares[i].style.display="none";

init();

function init(){
	// Mode Button Listener
	modeSetUp();
	//Squares Setup
	squareSetUp();
	//Replay
	replay();
}

function modeSetUp(){
	for(var i=0;i<modeBtn.length;++i){
		modeBtn[i].addEventListener("click",function(){

		modeBtn[0].classList.remove("selected");
		modeBtn[1].classList.remove("selected");
		modeBtn[2].classList.remove("selected");

		this.classList.add("selected");

		if(this.textContent==="Easy")
			numSquares=3;
		else if (this.textContent==="Medium")
			numSquares=6;
		else
			numSquares=9;
		
		replay();
		});
	}
}

function squareSetUp(){
	for (var i = 0; i <squares.length; i++) {
		if(colors[i])
		{
			squares[i].style.display="block";
			squares[i].style.backgroundColor=colors[i];
		}
		else
			squares[i].style.display="none";
	}

	cDisplay.textContent=picked; 
	heading.style.backgroundColor="steelblue";
	message.textContent="";
	reset.textContent= "New Colors";
}

function replay(){
	colors=generateRandomColor(numSquares);
	picked=pickColor();
	for (var i = 0; i <squares.length; i++) {
		if(colors[i])
		{
			squares[i].style.display="block";
			squares[i].style.backgroundColor=colors[i];
		}
		else
			squares[i].style.display="none";
	}
	cDisplay.textContent=picked; 
	heading.style.backgroundColor="steelblue";
	message.textContent="";
	reset.textContent= "New Colors";
}

reset.addEventListener("click",function(){
	/*
	colors=generateRandomColor(numSquares);
	picked=pickColor();
	for (var i = 0; i <squares.length; i++) {
		squares[i].style.backgroundColor=colors[i];
	}
	cDisplay.textContent=picked; 
	heading.style.backgroundColor="steelblue";
	message.textContent="";
	reset.textContent= "New Colors";
	*/
	replay();
});

/*
easy.addEventListener("click",function(){
	numSquares=3;
	easy.classList.add("selected");
	medium.classList.remove("selected");
	hard.classList.remove("selected");

	colors=generateRandomColor(3);
	picked=pickColor();
	cDisplay.textContent=picked;
	heading.style.backgroundColor="steelblue";

	for (var i = 0; i <squares.length; i++) {
		if(colors[i])
			squares[i].style.background=colors[i];
		else
			squares[i].style.display="none";
	}
});

medium.addEventListener("click",function(){
	numSquares=6;
	easy.classList.remove("selected");
	medium.classList.add("selected");
	hard.classList.remove("selected");

	colors=generateRandomColor(6);
	picked=pickColor();
	cDisplay.textContent=picked;
	heading.style.backgroundColor="steelblue";

	for (var i = 0; i <squares.length; i++) {
		if(colors[i])
		{
			squares[i].style.background=colors[i];
			squares[i].style.display="block"
		}
		else
			squares[i].style.display="none";
	}
});

hard.addEventListener("click",function(){
	numSquares=9;
	easy.classList.remove("selected");
	medium.classList.remove("selected");
	hard.classList.add("selected");

	colors=generateRandomColor(9);
	picked=pickColor();
	cDisplay.textContent=picked;
	heading.style.backgroundColor="steelblue";

	for (var i = 0; i <squares.length; i++) {
			squares[i].style.background=colors[i];
			squares[i].style.display="block";
	}
});
*/
for(var i = 0; i < squares.length; ++i)
{
	squares[i].style.backgroundColor = colors[i];
	
	squares[i].addEventListener("click",function(){
		var clickedColor=this.style.backgroundColor;
		if(clickedColor===picked)
		{
			message.textContent="Correct";
			changeColor(clickedColor);
			heading.style.backgroundColor=clickedColor;
			reset.textContent= "Play Again?";
		}
		else
		{
			this.style.backgroundColor="#232323";
			message.textContent="Try Again";
		}
	});
}

function changeColor(color){
	for(var i=0;i<squares.length;++i)
	{
		squares[i].style.backgroundColor=color;
	}
}

function pickColor(){
	var random=Math.floor(Math.random() * colors.length);
	return colors[random];
}

function generateRandomColor(count){
	var a=[count];
	for (var i = 0; i < count; i++) {
		a[i]=randomColor();
	}
	return a;
}

function randomColor(){
	var r=Math.floor(Math.random()*256);
	var g=Math.floor(Math.random()*256);
	var b=Math.floor(Math.random()*256);

	return "rgb(" + r + ", " + g + ", "+ b + ")";
}