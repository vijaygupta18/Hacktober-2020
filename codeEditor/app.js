const request = require('request');
const express = require('express');
const bodyParser = require('body-parser');

const app = express();
app.use(express.static('public'))

app.use(bodyParser.urlencoded({extended:false})) 
app.use(bodyParser.json()) 



app.get("/",(req,res)=>{
        
    res.sendFile('/index.html' , { root : __dirname});
    
      }

    
    
)

app.post("/",(req,res)=>{
   
    const program = {
        script : req.body.code,
        stdin:req.body.input,
        language: req.body.language,
        versionIndex: "0",
        clientId: "",
        clientSecret:""
    };
 

    request({
        url: 'https://api.jdoodle.com/v1/execute',
        method: "POST",
        json: program
    },
    function (error, response, body) {
        res.send(response.body.output);
    });

});



app.listen(3001,()=>{
    console.log("server started at port 3001");
})