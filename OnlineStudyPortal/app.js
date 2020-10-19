const express = require("express");
const bodyParser = require("body-parser");
const app = express();
const ejs = require("ejs");
const admin = {
  username: "admin",
  password: "admin"
}
app.set("view engine","ejs");
app.use(express.static("public"));
app.use(bodyParser.urlencoded({extended:true}));

app.get("/",(req,res)=>{
  res.render("login");
});

app.post("/",(req,res)=>{
  if(req.body.username === admin.username&&req.body.password === admin.password){
     res.redirect("/lessons");
  }else{
    res.send("<h1>Invalid username or password, Go back to try again</h1>");
  }
})

app.get("/lessons",(req,res)=>{
  res.render("lessons");
});

app.get("/assignments",(req,res)=>{
  res.render("assignments");
});

app.get("/lessons/mathematics",(req,res)=>{
  res.render("subject");
});
const quesArr =[
   {  _id:"ques1",
      ques:"Lorem ipsum dolor sit amet, consectetur adipiscing elit",
      a:"1eiusmod tempor incididunt ut labore et dolore magna aliqua",
      b:"2eiusmod tempor incididunt ut labore et dolore magna aliqua",
      c:"3eiusmod tempor incididunt ut labore et dolore magna aliqua",
      d:"4eiusmod tempor incididunt ut labore et dolore magna aliqua",
      correct:"1eiusmod tempor incididunt ut labore et dolore magna aliqua"
   },
   {  _id:"ques2",
      ques:"Lorem ipsum dolor sit amet, consectetur adipiscing elit",
      a:"1eiusmod tempor incididunt ut labore et dolore magna aliqua",
      b:"2eiusmod tempor incididunt ut labore et dolore magna aliqua",
      c:"3eiusmod tempor incididunt ut labore et dolore magna aliqua",
      d:"4iusmod tempor incididunt ut labore et dolore magna aliqua",
      correct:"2eiusmod tempor incididunt ut labore et dolore magna aliqua"
   },
   {  _id:"ques3",
      ques:"Lorem ipsum dolor sit amet, consectetur adipiscing elit",
      a:"1eiusmod tempor incididunt ut labore et dolore magna aliqua",
      b:"2eiusmod tempor incididunt ut labore et dolore magna aliqua",
      c:"3eiusmod tempor incididunt ut labore et dolore magna aliqua",
      d:"4eiusmod tempor incididunt ut labore et dolore magna aliqua",
      correct:"3eiusmod tempor incididunt ut labore et dolore magna aliqua"
   },
   {  _id:"ques4",
      ques:"Lorem ipsum dolor sit amet, consectetur adipiscing elit",
      a:"1eiusmod tempor incididunt ut labore et dolore magna aliqua",
      b:"2eiusmod tempor incididunt ut labore et dolore magna aliqua",
      c:"3eiusmod tempor incididunt ut labore et dolore magna aliqua",
      d:"4eiusmod tempor incididunt ut labore et dolore magna aliqua",
      correct:"4eiusmod tempor incididunt ut labore et dolore magna aliqua"
   }
]
app.get("/lessons/mathematics/test",(req,res)=>{
  res.render("test",{
    Questions: quesArr
  });


});

app.post("/lessons/mathematics/test",(req,res)=>{
  let result =0;

  if(quesArr[0].correct === req.body.ques1){
    result++;
  }
  if(quesArr[1].correct === req.body.ques2){
    result++;
  }
  if(quesArr[2].correct === req.body.ques3){
    result++;
  }
  if(quesArr[3].correct === req.body.ques4){
    result++;
  }


  res.render("result",{
    result: result
  });





});


app.get("/lessons/mathematics/video1",(req,res)=>{

  res.render("yvideo");

});

app.get("/lessons/mathematics/video2",(req,res)=>{

  res.render("uvideo");

});

app.get("/lessons/mathematics/pdf",(req,res)=>{

  res.render("pdf");

});

let port = process.env.PORT;
if(port == null|| port == ""){
  port = 3000;
}
app.listen(port,()=>{
  console.log("The server is running at port 3000");
});
