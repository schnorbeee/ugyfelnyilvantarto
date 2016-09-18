$(document).ready(function () {

    var $j = jQuery.noConflict();
    
//    $j("#eventStartDate").datepicker({
//        showOn: "button",
//        buttonImage: "../img/calendar.gif",
//        buttonImageOnly: true,
//        buttonText: "Select date"
//    }).mask("99/99/9999");

    $j("#eventEndDate").datepicker();

});