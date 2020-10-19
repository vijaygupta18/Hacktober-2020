let s = 0;
let m = 1;
var time = setInterval(function(){
  document.querySelector(".minutes").innerHTML=m;
  if(s<10){
    document.querySelector(".seconds").innerHTML="0"+s;
  }else{
  document.querySelector(".seconds").innerHTML=s;
  }

  s--;
  if(s==-1){
    s=59;
    m--;
  }

  if(m==-1){
    document.querySelector(".quizForm").submit();
  }
},1000);
