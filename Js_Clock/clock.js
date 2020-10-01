var wakeuptime = 7;
var noon = 12;
var lunchtime = 12;
var naptime = lunchtime + 2;
var eveningtime = 18;
var partytime;

// Get the current time

var ShowCurrentTime = function () {
  var currentTime = new Date();
  var clock = document.getElementById("clock");
  var hour = currentTime.getHours();
  var min = currentTime.getMinutes();
  var sec = currentTime.getSeconds();
  var meridian = "AM";
  if (hour > noon) {
    meridian = "PM";
  }
  if (hour > noon) {
    hour = hour - 12;
  }
  if (min < 10) {
    min = "0" + min;
  }
  if (sec < 10) {
    sec = "0" + sec;
  }
  var clockTime = hour + ":" + min + ":" + sec + " " + meridian + " !";
  clock.innerHTML = clockTime;
};

//Set the Updated time + catimage + imgage msg

var updatedTime = function () {
  var time = new Date().getHours();
  var image =
    "https://s3.amazonaws.com/media.skillcrush.com/skillcrush/wp-content/uploads/2016/08/normalTime.jpg";
  var messageText;
  var imgEvent = document.getElementById("catImg");
  var msgEvent = document.getElementById("imgText");

  if (time == partytime) {
    image =
      "https://s3.amazonaws.com/media.skillcrush.com/skillcrush/wp-content/uploads/2016/08/partyTime.jpg";
    messageText = "Let's party!";
  } else if (time == wakeuptime) {
    image =
      "https://s3.amazonaws.com/media.skillcrush.com/skillcrush/wp-content/uploads/2016/09/cat1.jpg";
    messageText = "Wake up!";
  } else if (time == lunchtime) {
    image =
      "https://s3.amazonaws.com/media.skillcrush.com/skillcrush/wp-content/uploads/2016/09/cat2.jpg";
    messageText = "Let's have some lunch!";
  } else if (time == naptime) {
    image =
      "https://s3.amazonaws.com/media.skillcrush.com/skillcrush/wp-content/uploads/2016/09/cat3.jpg";
    messageText = "Sleep tight!";
  } else if (time < noon) {
    image =
      "https://pbs.twimg.com/profile_images/378800000532546226/dbe5f0727b69487016ffd67a6689e75a.jpeg";
    messageText = "Good morning!";
  } else if (time >= eveningtime) {
    image = "https://upload.wikimedia.org/wikipedia/commons/8/8c/Cat_sleep.jpg";
    messageText = "Good evening!";
  } else {
    image =
      "https://s3.amazonaws.com/media.skillcrush.com/skillcrush/wp-content/uploads/2016/08/normalTime.jpg";
    messageText = "Good afternoon!";
  }
  catImg.src = image;
  msgEvent.innerHTML = messageText;
  ShowCurrentTime();
};
updatedTime();

// Update time after each second
setInterval(updatedTime, 1000);

//Party time Button working
var but = document.getElementById("partyTimeButton");
var partyEvent = function () {
  if (partytime < 0) {
    partytime = new Date().getHours();
    partyTimeButton.innerHTML = "Party Over!";
    partyTimeButton.style.backgroundColor = "#0A8DAB";
  } else {
    partytime = -1;
    partyTimeButton.innerHTML = "Party Time!";
    partyTimeButton.style.backgroundColor = "#222";
  }
};
but.addEventListener("click", partyEvent);
partyEvent();

//Activate selectors
//1.wakeup
var wakeupSelector = document.getElementById("wakeupTimeSelector");
var ActiveWakeup = function () {
  wakeuptime = wakeupSelector.value;
};
wakeupSelector.addEventListener("change", ActiveWakeup);
//1.lunch
var lunchSelector = document.getElementById("lunchTimeSelector");
var ActiveLunch = function () {
  lunchtime = lunchSelector.value;
};
lunchSelector.addEventListener("change", ActiveLunch);
//1.nap
var napSelector = document.getElementById("napTimeSelector");
var ActiveNap = function () {
  naptime = napSelector.value;
};
napSelector.addEventListener("change", ActiveNap);
