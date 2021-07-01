let url = "http://localhost:8080/bootcamp-management/userRole";
let id = $("#id").val();

$(document).ready( function () {
    load();
    $('#btn-add').on('click',function(){
        setForm("","");
        isEnabled(false,false);
        $('#userRoleModal').modal("show");
        $('#btn-save').show();
        $('#form')[0].reset();
        $('#user').select2().val(null).trigger('change');
        $('#role').select2().val(null).trigger('change');
    });
    
    $('#btn-save').on('click',function(e){
        e.preventDefault();
        saveUserRole(url);             
    });
});

saveUserRole = (url) => {
    var data = $('#form').serialize();
    $.ajax({
        url:url + "/save",
        type:"POST",
        data:data,
        dataType: "JSON",
        success: function (response) {
            if(response.message === "User Role created"){
                Swal.fire(
                    'Success!',
                    'UserRole Save...',
                    'success'
                  );
                $('#userRoleModal').modal('hide');
                table.destroy();
                load(); 
            }
    },
        error: function (data) {
            Swal.fire(
                'Failed!',
                'Your UserRole cannot be Added.',
                'error'
            );
        }
    });
};

load = () => {
    table = $('#userRoleTable').DataTable({
        'sAjaxSource': url + "/list",
        'sAjaxDataProp': '',
        'columns': [
            { "defaultContent": "" },
            {'data': 'user.username'},
            {'data': 'role.name'},
            {
                'render': function (data, type, row, meta) {
                    return '<a class="btn btn-sm btn-primary mr-1" onClick="detail('+ row.id + ',true)"><i class="far fa-eye"></i></button>' +
                    '<a class="btn btn-sm btn-warning mr-1" onClick="detail('+ row.id + ')"><i class="fas fa-pencil-alt"></i></button>' +
                    '<a class="btn btn-sm btn-danger mr-1" onClick="deleteUserRole('+ row.id + ')"><i class="far fa-trash-alt"></i></button>';
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
    $('#userRoleModal').modal('show');
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
            setForm(response.id);
       }
    });
};

deleteUserRole = (id) => {
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
                       console.log(response);
                       if(response.message === "User Role deleted"){
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

setForm = (id) => {
     $('#id').val(id);
};

isEnabled = (isEnable) => {
    $('#id').prop("disabled",isEnable);
    $('#user').prop("disabled",isEnable);
    $('#role').prop("disabled",isEnable);
};
