

let dt = 9;
let ht = 0;
let mt = 0;
let st =0;

var timet = setInterval(function(){
  document.querySelector(".days-telegu").innerHTML = dt+" d ";
  document.querySelector(".hours-telegu").innerHTML = ht+ " hrs ";
  document.querySelector(".min-telegu").innerHTML = mt +" mins ";
  if(st<10){
    document.querySelector(".sec-telegu").innerHTML="0"+st+" secs left";
  }else{
  document.querySelector(".sec-telegu").innerHTML=st+" secs left";
  }

  st--;

  if(st==-1){
    st=59;
    mt--;
  }
  if(mt==-1){
    mt = 59;
    ht--;
  }
  if(ht==-1){
    ht = 23;
    dt--;
  }
  if(dt==-1){
    closeInterval(timet);
  }

  if(dt<4){
    document.querySelector(".telegu").classList.add("l-red");
  }else{
    document.querySelector(".telegu").classList.add("l-blue");
  }

},1000)

let ds = 4;
let hs = 0;
let ms = 0;
let ss =30;

var times = setInterval(function(){
  document.querySelector(".days-science").innerHTML = ds+" d ";
  document.querySelector(".hours-science").innerHTML = hs+ " hrs ";
  document.querySelector(".min-science").innerHTML = ms +" mins ";
  if(ss<10){
    document.querySelector(".sec-science").innerHTML="0"+ss+" secs left";
  }else{
  document.querySelector(".sec-science").innerHTML=ss+" secs left";
  }

  ss--;

  if(ss==-1){
    ss=59;
    ms--;
  }
  if(ms==-1){
    ms = 59;
    hs--;
  }
  if(hs==-1){
    hs = 23;
    ds--;
  }
  if(ds==-1){
    closeInterval(times);
  }

  if(ds<4){
    document.querySelector(".science").classList.add("l-red");
  }else{
    document.querySelector(".science").classList.add("l-blue");
  }

},1000)

let db = 2;
let hb = 0;
let mb = 0;
let sb =0;

var timeb = setInterval(function(){
  document.querySelector(".days-biology").innerHTML = db+" d ";
  document.querySelector(".hours-biology").innerHTML = hb+ " hrs ";
  document.querySelector(".min-biology").innerHTML = mb +" mins ";
  if(sb<10){
    document.querySelector(".sec-biology").innerHTML="0"+sb+" secs left";
  }else{
  document.querySelector(".sec-biology").innerHTML=sb+" secs left";
  }

  sb--;

  if(sb==-1){
    sb=59;
    mb--;
  }
  if(mb==-1){
    mb = 59;
    hb--;
  }
  if(hb==-1){
    hb = 23;
    db--;
  }
  if(db==-1){
    closeInterval(timeb);
  }

  if(db<4){
    document.querySelector(".biology").classList.add("l-red");
  }else{
    document.querySelector(".biology").classList.add("l-blue");
  }
},1000)

let dh = 10;
let hh = 0;
let mh = 0;
let sh =0;

var timeh = setInterval(function(){
  document.querySelector(".days-hindi").innerHTML = dh+" d ";
  document.querySelector(".hours-hindi").innerHTML = hh+ " hrs ";
  document.querySelector(".min-hindi").innerHTML = mh +" mins ";
  if(sh<10){
    document.querySelector(".sec-hindi").innerHTML="0"+sh+" secs left";
  }else{
  document.querySelector(".sec-hindi").innerHTML=sh+" secs left";
  }

  sh--;

  if(sh==-1){
    sh=59;
    mh--;
  }
  if(mh==-1){
    mh = 59;
    hh--;
  }
  if(hh==-1){
    hh = 23;
    dh--;
  }
  if(dh==-1){
    closeInterval(timeh);
  }

  if(dh<4){
    document.querySelector(".hindi").classList.add("l-red");
  }else{
    document.querySelector(".hindi").classList.add("l-blue");
  }

},1000)
