
Vue.component('vue', {
  data: function () {
    return {
      footer_txt: '© Copyright 2020 DikshaWadhwa. All rights reserved.'
    }
  },
  template: '<div> <p class="footer-text"> {{footer_txt}} </p></div>'
});



new Vue({
    el: '#vue-app'


});

$(function () {
  $(document).scroll(function () {
    var $nav = $(".navbar-fixed-top");
    $nav.toggleClass('scrolled', $(this).scrollTop() > $nav.height());
  });
});
