//<![CDATA[

$(window).load(function () {
  quickView.initquickView();
});

var quickView = {

  'initquickView' : function () {
    $('body').append('<div class="quickview-container"></div>');
    $('.quickview-container').load('index.php?route=product/quickview/insertcontainer');
  },

  'addCloseButton' : function () {
    $('.quickview-wrapper').prepend("<a href='javascript:void(0);' class='quickview-btn' onclick='quickView.closeButton()'><i class='icon-close'></i></a>");
  },

  'closeButton' : function () {
    $('.quickview-overlay').hide();
    $('.quickview-wrapper').hide().html('');
    $('.quickview-loader').hide();
  },

  ajaxView :function(url){
    const path = window.location.href.split('?')[0] + url

    $.ajax({
      url     : url,
      type    : 'get',
      beforeSend  : function() {
        $('.quickview-overlay').show();
        $('.quickview-loader').show();        
      },
      success   : function(json) {
        if(json['success'] == true) {
          $('.quickview-loader').hide();
          $('.quickview-wrapper').html(json['html']);
          quickView.addCloseButton();

          const additional = $('html').attr('dir'); 
          $('#quick-carousel').each(function () {
            const items = $(this).data('items') || 4;
            const sliderOptions = {
              loop: false,
              nav: true,
              navText: ['<i class="fa fa-caret-left"></i>','<i class="fa fa-caret-right"></i>'],
              dots: false,
              items: items,
              responsiveRefreshRate: 200,
              responsive: {
                0: { items:1 },
                300: { items: ((items - 2) > 1) ? (items - 2) : 1 },
                376: { items: ((items - 1) > 1) ? (items - 1) : 1 },
                481: { items: items },
                768: { items: ((items - 1) > 1) ? (items - 1) : 1 },
                992: { items: ((items - 2) > 1) ? (items - 2) : 1 },
                1200: { items: ((items - 1) > 1) ? (items - 1) : 1 },
                1441: { items:items }
              }
            };
            if (additional == 'rtl') sliderOptions['rtl'] = true;
            $(this).owlCarousel(sliderOptions);
          
          }); 
          
          $('.quickview-wrapper').show();

          $('#datetimepicker').datetimepicker({
            pickTime: false
          });                   
          $('#datetime').datetimepicker({
            pickDate: true,
            pickTime: true
          });
          
          $('#Time').datetimepicker({
            pickDate: false
          });

        }
      }
    });

  }
};
//]]>