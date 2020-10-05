const btn = document.querySelector(".talk");
const content = document.querySelector(".content");

//custom messages
const greetings = [
  "Im fine you little piece of shit",
  "go to hell",
  "dont you have anything else to do",
];

const weather = ["whatca gonna do huh", "like you care"];

// enabel about:config media.web speech
const SpeechRecognition =
  window.SpeechRecognition || window.webkitSpeechRecognition;
const recognition = new SpeechRecognition(); //var SpeechGrammarList = SpeechGrammarList || webkitSpeechGrammarList

recognition.onstart = function () {
  console.log("voice is activated, start speaking");
};

recognition.onresult = function (event) {
  const current = event.resultIndex;

  const transcript = event.results[current][0].transcript; // array for the event
  content.textContent = transcript;
  readOutLoud(transcript);
};

//listener button function

btn.addEventListener("click", () => {
  recognition.start();
});

function readOutLoud(message) {
  const speech = new SpeechSynthesisUtterance();
  speech.text = "whatever";

  if (message.includes("how are you")) {
    const reply = greetings[Math.floor(Math.random() * greetings.length)];
    speech.text = reply;
  }

  if (message.includes("weather")) {
    const reply = weather[Math.floor(Math.random() * weather.length)];
    speech.text = reply;
  }

  speech.volume = 1;
  speech.rate = 1;
  speech.pitch = 1;

  window.speechSynthesis.speak(speech);
}
