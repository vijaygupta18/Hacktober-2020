//jshint esversion:6

const express = require("express");
const bodyParser = require("body-parser");
const ejs = require("ejs");
const path = require("path");
const _ = require("lodash");

const homeStartingContent = "Lacus vel facilisis volutpat est velit egestas dui id ornare. Semper auctor neque vitae tempus quam. Sit amet cursus sit amet dictum sit amet justo. Viverra tellus in hac habitasse. Imperdiet proin fermentum leo vel orci porta. Donec ultrices tincidunt arcu non sodales neque sodales ut. Mattis molestie a iaculis at erat pellentesque adipiscing. Magnis dis parturient montes nascetur ridiculus mus mauris vitae ultricies. Adipiscing elit ut aliquam purus sit amet luctus venenatis lectus. Ultrices vitae auctor eu augue ut lectus arcu bibendum at. Odio euismod lacinia at quis risus sed vulputate odio ut. Cursus mattis molestie a iaculis at erat pellentesque adipiscing.";
const aboutContent = "Hac habitasse platea dictumst vestibulum rhoncus est pellentesque. Dictumst vestibulum rhoncus est pellentesque elit ullamcorper. Non diam phasellus vestibulum lorem sed. Platea dictumst quisque sagittis purus sit. Egestas sed sed risus pretium quam vulputate dignissim suspendisse. Mauris in aliquam sem fringilla. Semper risus in hendrerit gravida rutrum quisque non tellus orci. Amet massa vitae tortor condimentum lacinia quis vel eros. Enim ut tellus elementum sagittis vitae. Mauris ultrices eros in cursus turpis massa tincidunt dui.";
const contactContent = "Scelerisque eleifend donec pretium vulputate sapien. Rhoncus urna neque viverra justo nec ultrices. Arcu dui vivamus arcu felis bibendum. Consectetur adipiscing elit duis tristique. Risus viverra adipiscing at in tellus integer feugiat. Sapien nec sagittis aliquam malesuada bibendum arcu vitae. Consequat interdum varius sit amet mattis. Iaculis nunc sed augue lacus. Interdum posuere lorem ipsum dolor sit amet consectetur adipiscing elit. Pulvinar elementum integer enim neque. Ultrices gravida dictum fusce ut placerat orci nulla. Mauris in aliquam sem fringilla ut morbi tincidunt. Tortor posuere ac ut consequat semper viverra nam libero.";

const posts = [];

const app = express();

app.set('view engine', 'ejs');

app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static("public"));


app.get("/", function (req, res) {
  res.render("home", { para: homeStartingContent, blogPost: posts });

})

app.get("/about", (req, res) => {
  res.render("about", { para2: aboutContent });
})

app.get("/contact", (req, res) => {
  res.render("contact", { para3: contactContent });
})

app.get("/compose", (req, res) => {
  res.render("compose")
})


app.post("/compose", (req, res) => {
  const post = {
    title: req.body.title,
    content: req.body.content
  }
  posts.push(post);
  res.redirect("/");
})


// for matching blogPost title with id prarams (with kabab case url params)
app.get("/post/:id", (req, res) => {
  const requestTitle = _.lowerCase(req.params.id);
  let found = false;
  let post;

  for (let i = 0; i < posts.length; i++) {
    const storedTitle = _.lowerCase(posts[i].title);

    if (requestTitle === storedTitle) {
      found = true;
      post = posts[i];
      break;
    }
    else {
      found = false;
    }
  }

  if (found) {
    console.log("Match found");
    res.render("post", { post: post });

  } else {
    console.log("Match not found");
    res.redirect("/");
  }

})


app.listen(3000, function () {
  console.log("Server started on port 3000");
});


// NEW THINGS:-

//1. Lodash
    // A modern JavaScript utility library delivering
    // modularity, performance & extras.

    // => Lodash makes JavaScript easier by taking the hassle out of working with arrays, numbers, objects, strings, etc. Lodash’s modular methods are great for:
    // i.   Iterating arrays, objects, & strings
    // ii.  Manipulating & testing values
    // iii. Creating composite functions

    // => Installation:- 

    // install: npm i --save lodash

    // => Usage:-

    // // Load the full build.
    // var _ = require('lodash');


    // => Methods:-

    // _.lowerCase([string=''])
    // Converts string, as space separated words,
    // to lower case.

    // _.lowerFirst([string=''])
    // Converts the first character of string 
    // to lower case.

    //_.toLower([string=''])
    // Converts string, as a whole, to lower case 
    // just like String#toLowerCase.


// 2. To truncate a string
    // - use str.substring(start, end) + "...";
    // - give anchor tag Read more functionality by adding route in href as "/post/<%= blog.title %>"



// C:\altera\81\modelsim_ae\win32aloem;"C:\Program Files (x86)\Python38-32\python.exe";C:\Users\Mainak\AppData\Local\hyper\app-3.0.2\resources\bin;C:\Program Files\heroku\bin;C:\Users\Mainak\AppData\Roaming\npm;C:\MinGW\bin;C:\Users\Mainak\AppData\Local\Programs\Microsoft VS Code\bin

// C:\Windows\System32\WindowsPowerShell\v1.0;C:\MinGW\bin;C:\Program Files\nodejs\;C:\Program Files (x86)\Python38-32
