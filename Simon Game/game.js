var userClickedPattern=[];

var gamePattern=[];

var buttonColours=["red", "blue", "green", "yellow"];


  $(".btn").click(function(){
    var userChosenColour=$(this).attr("id");
    userClickedPattern.push(userChosenColour);
    playSound(userChosenColour);
    animatePress(userChosenColour);
    checkAnswer(userClickedPattern.length-1);
    // console.log(userClickedPattern);
  })

function nextSequence(){
  userClickedPattern=[];
  level++;
  $("#level-title").text("Level "+level);
  var randomNumber=Math.floor(Math.random()*4);
  var  randomChosenColour=buttonColours[randomNumber];
  gamePattern.push(randomChosenColour);
  $("#"+randomChosenColour).fadeIn(100).fadeOut(100).fadeIn(100);
playSound(randomChosenColour);
}
function playSound(name){
  var audio = new Audio("sounds/" + name + ".mp3");
  audio.play();
}
function animatePress(currentColour){
  $("#"+currentColour).addClass("pressed");
  setTimeout(function () {
   $("#" + currentColour).removeClass("pressed");
 }, 100);

}

var started=false;
var level=0;
$(document).keydown(function(){
  if(!started){
    $("#level-title").text("Level "+level);
    nextSequence();
    started=true;
  }
})
function checkAnswer(currentLevel){
  if(userClickedPattern[currentLevel]===gamePattern[currentLevel]){
   // console.log("success");
   if (userClickedPattern.length === gamePattern.length){
      setTimeout(function () {
        nextSequence();
      }, 1000);

    }
  }
    else{
      // console.log("wrong");
      var audio = new Audio("sounds/wrong.mp3");
      audio.play();
      $("body").addClass("game-over");
      setTimeout(function () {
        $("body").removeClass("game-over");
      }, 200);
      $("h1").text("Game Over, Press Any Key to Restart");
      startOver();
    }

}

function startOver(){
  gamePattern=[];
  level=0;
  started=false;
}
// console.log(nextSequence());
