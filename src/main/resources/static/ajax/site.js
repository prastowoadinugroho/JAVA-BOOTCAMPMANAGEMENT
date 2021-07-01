let url = "http://localhost:8080/bootcamp-management/site";
let id = $("#id").val();

$(document).ready( function () {
    load();
    $('#btn-add').on('click',function(){
        setForm("","");
        isEnabled(false,false);
        $('#siteModal').modal("show");
        $('#btn-save').show();
        $('#form')[0].reset();
    });
    
    $('#btn-save').on('click',function(e){
        e.preventDefault();
        saveSite(url);             
    });
});

saveSite = (url) => {
    var data = $('#form').serialize();
    $.ajax({
        url:url,
        type:"POST",
        data:data,
        dataType: "JSON",
        success: function (response) {
            if(response.message === "Site created"){
                Swal.fire(
                    'Success!',
                    'Site Save...',
                    'success'
                  );
                $('#siteModal').modal('hide');
                table.destroy();
                load(); 
            }
    },
        error: function (data) {
            Swal.fire(
                'Failed!',
                'Your Class cannot be Added.',
                'error'
            );
        }
    });
};

load = () => {
    table = $('#siteTable').DataTable({
        'sAjaxSource': url + "/list",
        'sAjaxDataProp': '',
        'columns': [
            { "defaultContent": "" },
            {'data': 'name'},
            {
                'render': function (data, type, row, meta) {
                    return '<a class="btn btn-sm btn-primary mr-1" onClick="detail('+ row.id + ',true)"><i class="far fa-eye"></i></button>' +
                    '<a class="btn btn-sm btn-warning mr-1" onClick="detail('+ row.id + ')"><i class="fas fa-pencil-alt"></i></button>' +
                    '<a class="btn btn-sm btn-danger mr-1" onClick="deleteSite('+ row.id + ')"><i class="far fa-trash-alt"></i></button>';
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

detail = (cId,status) =>{
    $('#siteModal').modal('show');
    id = cId;
    
    $.ajax({
       url:url+"/"+cId,
       method:"GET",
       dataType: "JSON",
       success:function(response){
            if(status !== true ){
                $('#btn-save').show();
                isEnabled(false,true);
            }
            else{
                 $('#btn-save').hide();
                 isEnabled(true,true);
            }
            setForm(response.id,response.name, response.address);
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


