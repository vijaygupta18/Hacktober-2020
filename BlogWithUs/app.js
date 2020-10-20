//jshint esversion:6

const express = require("express");
const bodyParser = require("body-parser");
const ejs = require("ejs");
const _ = require("lodash");
const mongoose = require("mongoose");
const homeStartingContent = "Lacus vel facilisis volutpat est velit egestas dui id ornare. Semper auctor neque vitae tempus quam. Sit amet cursus sit amet dictum sit amet justo. Viverra tellus in hac habitasse. Imperdiet proin fermentum leo vel orci porta. Donec ultrices tincidunt arcu non sodales neque sodales ut. Mattis molestie a iaculis at erat pellentesque adipiscing. Magnis dis parturient montes nascetur ridiculus mus mauris vitae ultricies. Adipiscing elit ut aliquam purus sit amet luctus venenatis lectus. Ultrices vitae auctor eu augue ut lectus arcu bibendum at. Odio euismod lacinia at quis risus sed vulputate odio ut. Cursus mattis molestie a iaculis at erat pellentesque adipiscing.";
const aboutContent = "Hac habitasse platea dictumst vestibulum rhoncus est pellentesque. Dictumst vestibulum rhoncus est pellentesque elit ullamcorper. Non diam phasellus vestibulum lorem sed. Platea dictumst quisque sagittis purus sit. Egestas sed sed risus pretium quam vulputate dignissim suspendisse. Mauris in aliquam sem fringilla. Semper risus in hendrerit gravida rutrum quisque non tellus orci. Amet massa vitae tortor condimentum lacinia quis vel eros. Enim ut tellus elementum sagittis vitae. Mauris ultrices eros in cursus turpis massa tincidunt dui.";
const contactContent = "Scelerisque eleifend donec pretium vulputate sapien. Rhoncus urna neque viverra justo nec ultrices. Arcu dui vivamus arcu felis bibendum. Consectetur adipiscing elit duis tristique. Risus viverra adipiscing at in tellus integer feugiat. Sapien nec sagittis aliquam malesuada bibendum arcu vitae. Consequat interdum varius sit amet mattis. Iaculis nunc sed augue lacus. Interdum posuere lorem ipsum dolor sit amet consectetur adipiscing elit. Pulvinar elementum integer enim neque. Ultrices gravida dictum fusce ut placerat orci nulla. Mauris in aliquam sem fringilla ut morbi tincidunt. Tortor posuere ac ut consequat semper viverra nam libero.";

mongoose.connect("mongodb://localhost:27017/blogDB",{useUnifiedTopology:true,useNewUrlParser:true});
const blogSchema = new mongoose.Schema({
  title : String,
  post: String
});

const blog = mongoose.model("blog",blogSchema);
const app = express();
app.set('view engine', 'ejs');

app.use(bodyParser.urlencoded({extended: true}));
app.use(express.static("public"));

function kebab(s){
   s=_.kebabCase(s);
   return s;
}
app.get("/",(req,res)=>{
  blog.find({},(err,posts)=>{
    if(!err){
      res.render("home",
      {
        homeStartingContent: homeStartingContent,
        newpost: posts,
        kebab: kebab
      });
    }
  });

});
app.get("/about",(req,res)=>{
  res.render("about",{about: aboutContent});
});
app.get("/contact",(req,res)=>{
  res.render("contact",{contact: contactContent});
});
app.get("/compose",(req,res)=>{
  res.render("compose");
});
app.get("/posts/:data", (req,res)=>{

 let id = req.params.data;
 blog.findOne({_id:""+id},(err,foundPost)=>{
   if(!err){
     res.render("post",{
       element: foundPost
     })
   }
 })

 });



app.post("/compose",(req,res)=>{
   const newblog = new blog({
     title: req.body.blogTitle,
     post: req.body.blogPost
   });
   newblog.save(err=>{
    if(!err){
      res.redirect("/");
    }
   });

});










app.listen(3000, function() {
  console.log("Server started on port 3000");
});
