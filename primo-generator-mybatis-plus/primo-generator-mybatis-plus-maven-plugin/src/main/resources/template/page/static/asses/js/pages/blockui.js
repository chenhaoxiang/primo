$(document).ready(function() {
    
    "use strict";
    $('#blockui-1').on('click', function() { 
        $('#blockui-card-1').block({ 
            message: '<div class="spinner-grow text-primary" role="status"><span class="visually-hidden">Loading...</span></div>',
            timeout: 2000 
        }); 

    }); 

    $('#blockui-2').on('click', function() { 
        $.blockUI({ 
            message: '<div class="spinner-grow text-primary" role="status"><span class="visually-hidden">Loading...</span></div>',
            timeout: 2000 
        }); 
    }); 

});