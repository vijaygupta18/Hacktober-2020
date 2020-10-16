var nav = document.querySelector(".nav-button");
var navOpen = document.querySelector(".nav-open");

var today = document.querySelector(".cover-date");
var date = new Date();
today.innerHTML =
     date.getDate() + "/" + date.getMonth() + "/" + date.getFullYear();

const tl = gsap.timeline({ paused: false, reversed: true });

tl.to(".cover", 1, {
     width: "60%",
     ease: "Power2.inOut",
})
     .to(
          "nav",
          1,
          {
               height: "100%",
               ease: "Power2.inOut",
          },
          "-=0.5"
     )
     .fromTo(
          navOpen,
          1,
          {
               opacity: 0,
               x: -50,
               ease: "Power2.inOut",
          },
          {
               opacity: 1,
               x: 0,
               ease: "Power2.inOut",
               onComplete: function () {
                    navOpen.style.pointerEvents = "all";
                    console.log("done");
               },
          },
          "-=0.5"
     );
nav.addEventListener("click", () => {
     // so that we cannot change the animation in the middle of our animation
     /* if (tl.isActive()) {
       e.preventDefault();
       e.stopImmediatePropagation();
       return false;
     } */

     tweenToggle(tl);
});

function tweenToggle(tween) {
     tween.reversed() ? tween.play() : tween.reverse();
}
