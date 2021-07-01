let url = "http://localhost:8080/bootcamp-management/employee-site";
let id = $("#id").val();

$(document).ready( function () {
    load(); 
});

load = () => {
    table = $('#employeeTable').DataTable({
        'sAjaxSource': url + "/list",
        'sAjaxDataProp': '',
        "bInfo": false,
        'columns': [
            {"defaultContent": "" },
            {'data': 'employee.name'},
            {
                "render": function(data, type, row, meta) {
                    return '<a class="btn btn-sm btn-primary mr-1" onClick="detail('+ row.employee.id + ')">Detail</button>';
                }
            }
        ]
    });
    table.on( 'order.dt search.dt', function () {
        table.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
};

detail = (id) => {
    $('#employeeModal').modal('show');
    $.ajax({
       url:url+"/list/"+id,
       method:"GET",
       dataType: "JSON",
       success:function(response){
           var from ="From: ";
           var now ="Now: ";
           var currentdate = new Date(); 
           var datetime = currentdate.getDate() + "/"  
                + (currentdate.getMonth()+1)  + "/" 
                + currentdate.getFullYear();

           $('#email').text(response.employee.email);
           $('#class').text(response.employee.classes.name);
           $('#job').text(response.employee.jobStatus + ' in ' + response.request.customer.site.name);
           $('#date').html('<b>'+from+'</b>' + dateFormat(new Date(response.startDate)) + ' Until <b>'+now+'</b>' + datetime);
       }
    });
}

function dateFormat(d) {
    return (
            (d.getDate() + "").padStart(2)
        + "/" + (d.getMonth() + 1) + "").padStart(2, "0")
        + "/" + d.getFullYear();
}
