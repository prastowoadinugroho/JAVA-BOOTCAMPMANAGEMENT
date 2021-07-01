let url = "http://localhost:8080/bootcamp-management/employee";
let id = $("#id").val();

$(document).ready( function () {
    load();    
});

load = () => {
    table = $('#employeeTable').DataTable({
        'sAjaxSource': url + "/list",
        'sAjaxDataProp': '',
        'columns': [
            { "defaultContent": "" },
            {'data': 'name'},
            {'data': 'email'},
            {'data': 'job.name'},
            {
                'render': function (data, type, row, meta) {
                    return '<a class="btn btn-sm btn-primary mr-1" onClick="detail('+ row.id + ')"><i class="far fa-eye"></i></button>' +
                    '<a href="employee/'+row.id+'/edit" class="btn btn-sm btn-warning mr-1"><i class="fas fa-pencil-alt"></i></button>' ;
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

detail = (cId) =>{
    $('#detailModal').modal('show');
    id = cId;

    $.ajax({
       url:url+"/"+cId,
       method:"GET",
       dataType: "JSON",
       success:function(response){
            $('#address').text(response.address);
            $('#phone').text(response.phoneNumber);
            $('#status').text(response.jobStatus);
            $('#job').text(response.job.name);
            $('#class').text(response.classes.name);
       }
    });
};

deleteSite = (id) => {
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!',
        timer:"2000"
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                   url:url+"/delete/"+id,
                   method:"GET",
                   dataType: "JSON",
                   success:function(response){
                       if(response.message === "Site deleted"){
                           Swal.fire(
                            'Delete Success!',
                             response.message,
                            'success'
                          );
                          table.destroy();
                          load();
                       }
                   }
                });
            }
        });
};

setForm = (id,name,address) => {
     $('#id').val(id);
     $('#name').val(name);
     $('#address').val(address);
};

isEnabled = (isEnable) => {
    $('#id').prop("disabled",isEnable);
    $('#name').prop("disabled",isEnable);
    $('#address').prop("disabled",isEnable);
};


