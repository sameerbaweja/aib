$(document).ready(function() {
    $( ".popup" ).dialog({
      autoOpen: false,
      width: 540,
      modal: true
    });

    /*$( ".add_product" ).click(function( event ) {
      $( ".popup" ).dialog( "open" );
      event.preventDefault();
    });*/

    $( ".popup_request" ).dialog({
      autoOpen: false,
      width: 540,
      modal: true
    });

    /*$( ".new_request" ).click(function( event ) {
      $( ".popup_request" ).dialog( "open" );
      event.preventDefault();
    });*/

    $( ".product_add_icon" ).click(function( event ) {
      $( ".popup" ).dialog( "open" );
      $( ".popup_request" ).dialog( "close" );
      event.preventDefault();
    });

    $( "#datepicker" ).datepicker({
      showOn: "both",
      buttonImage: "images/icon_calender.png",
      buttonImageOnly: true,
      buttonText: "Select date",
      dateFormat: "d MM, yy",
      onSelect: function(dateText, inst)
      {
          var date = $(this).datepicker('getDate');
          console.log("date:" + dateText);
      }      
    });


    //profile pgae make textboxes editable.
    $(document).on('click', '.editable span', function() {
      $(this).hide();
      var input = $(this).parents('.editable').find('input')
      $(input).val($(this).text());
      $(input).show();
      $(input).focus();
    })

    $(document).on('blur change', '.editable input', function() {
      var val = $(this).val();
      $(this).hide();
      $(this).parents('.editable').find('span').text(val);
      $(this).parents('.editable').find('span').show();
    })

    var $this;
    $(document).on("click", ".DateTxtEditable", function () {
      var currElmModelAttr = $(this).attr('data-model-attr');
      $this = $(this);
      var input = $('<input />', {
              'type': 'text',
              'name': currElmModelAttr,
              'class': 'text-field',
              'value': $(this).text()
      });
      $(this).replaceWith(input);
      input.datepicker({
          dateFormat: "d M, yy",
          autoclose: true,
          onSelect: function (date) {
              $this.text(date);
              input.replaceWith($this);
              //input.blur();
          }
      }).focus()
      $(document).on("blur change", "input", function () {
          setTimeout(function () {
              var value = input.val();

              $this.text(value);
              input.replaceWith(date);
          }, 100);

      });
    });


    //expand / cpllapse details
    $('.title_area').on('click', function() {
    	alert('aaaaaaaa');
      $(this).parents('ul').find('li').not($(this).parents('li')).removeClass('active');
      $(this).parents('li').toggleClass('active');
    })

    //warranty period numbers only.
    //$('#warranty_period').on('keydown', function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)||35<=e.keyCode&&40>=e.keyCode||(e.shiftKey||48>e.keyCode||57<e.keyCode)&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
});
