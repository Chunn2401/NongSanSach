// the selector will match all input controls of type :checkbox
// and attach a click event handler
$("input:checkbox").on("click", function () {
  // in the handler, 'this' refers to the box clicked on
  var $box = $(this);
  if ($box.is(":checked")) {
    // the name of the box is retrieved using the .attr() method
    // as it is assumed and expected to be immutable
    var group = "input:checkbox[name='" + $box.attr("name") + "']";
    // the checked state of the group/box on the other hand will change
    // and the current value is retrieved using .prop() method
    $(group).prop("checked", false);
    $box.prop("checked", true);
  } else {
    $box.prop("checked", false);
  }
});

function lastPage(page) {
  var test = $("#last").hasClass("disabled");
  if (test == false) {
    $("#indexPage").val(page);
    $("#formPage").submit();
  }
}

function nextPage(page) {
  var test = $("#next").hasClass("disabled");
  if (test == false) {
    $("#indexPage").val(page);
    $("#formPage").submit();
  }
}

function prevPage(page) {
  var test = $("#prev").hasClass("disabled");
  if (test == false) {
    $("#indexPage").val(page);
    $("#formPage").submit();
  }
}

function firstPage(page) {
  var test = $("#first").hasClass("disabled");
  if (test == false) {
    $("#indexPage").val(page);
    $("#formPage").submit();
  }
}
