$(document).ready(function() {

    
    
    /* For the sticky navigation */
    $('.js--section-features').waypoint(function(direction) {
        if (direction == "down") {
            $('nav').addClass('sticky');
        } else {
            $('nav').removeClass('sticky');
        }
    }, {
        offset:'60px;'

    });
    $('.js--scroll-to-start').click(function () {
        $('html, body').animate({scrollTop: $('.js--section-features').offset().top}, 1000); 
     });
     


      /* Navigation scroll */
    $(function() {
        $('a[href*=#]:not([href=#])').click(function() {
          if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') && location.hostname == this.hostname) {
            var target = $(this.hash);
            target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
            if (target.length) {
              $('html,body').animate({
                scrollTop: target.offset().top
              }, 1000);
              return false;
            }
          }
        });
      });

       /* Animations on scroll */


      $('.js--up-1').waypoint(function(direction) {
        $('.js--up-1').addClass('animated fadeIn');
    }, {
        offset: '50%'
    });
    
    
    $('.js--up-2').waypoint(function(direction) {
        $('.js--up-2').addClass('animated fadeIn');
    }, {
        offset: '50%'
    });
    
   

       /* Mobile navigation */
    $('.js--nav-icon').click(function() {
      var nav=$('.js--main-nav');
      
      
      nav.slideToggle(200);
      
           
  });
});
      



   
   