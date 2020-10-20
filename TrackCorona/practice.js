console.log("hello world!");
var a="1";
//a="hello anuj";
//a=1.2;
//a=true;
var myfunction2 = function(i){

  console.log("done");
}
if( a === 1){
console.log("hello");
//do something

}


else{
console.log("no hello");
  //do something
}

for(var i=0;i<5;i++){
  output = myfunction(i);
  console.log(output);
}
function myfunction(i){
  console.log(i);
  return "executed";
}

//array



var array = [1,"hello",6.7,true,myfunction2];
for(var i=0;i<array.length;i++){
  console.log(array[i]);
}



//another way of defining function


array[4](1);


//you can add function to an array


//objects can be used for key value pairs

var obj={
  "key1": "Hello",
  "key2": 1,
  "key3": [1,2,3,4],
  "key4": {
    "anuj":"anujarora"
  },
  "functionkey": function(){
    console.log("inside obj function");
    return 1;
  }
}

console.log(obj.key1);
console.log(obj.key2);
console.log(obj.key3);
console.log(obj.key4.anuj);
console.log(obj.functionkey());



console.log(1);
setTimeout(function(){
console.log(2);
},2000);

console.log(3);



function argument(){

  console.log("inside the argument")
}

function test(argumentFunction){
argumentFunction()

}


test(argument);
