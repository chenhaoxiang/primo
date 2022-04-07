$(document).ready(function() {
    
    "use strict";
    $(".flatpickr1").flatpickr();

    $(".flatpickr2").flatpickr({
        enableTime: true,
        dateFormat: "Y-m-d H:i",
    });
    
});