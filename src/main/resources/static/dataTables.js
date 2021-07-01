/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

  
$(document).ready( function () {
    $('#dataTable').DataTable();
});

$(document).ready(function() {
    $('.js-example-basic-single').select2({
        width: '100%',
        theme: "classic"
    }).val(null).trigger('change');
});


//  border-radius: 10rem;
//  padding: 1.5rem 1rem;