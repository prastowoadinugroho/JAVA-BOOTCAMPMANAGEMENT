let url = "http://localhost:8080/bootcamp-management/customer";
let id = $("#id").val();

$(document).ready( function () {
    load();
    $('#btn-add').on('click',function(){
        setForm("","","");
        isEnabled(false,false);
        $('#customerModal').modal("show");
        $('#btn-save').show();
        $('#form')[0].reset();
        $('#site').select2().val(null).trigger('change');
    });
    
    $('#btn-save').on('click',function(e){
        e.preventDefault();
        saveCustomer(url);             
    });
});

saveCustomer = (url) => {
    var data = $('#form').serialize();
    $.ajax({
        url:url + "/save",
        type:"POST",
        data:data,
        dataType: "JSON",
        success: function (response) {
            if(response.message === "Customer created"){
                Swal.fire(
                    'Success!',
                    'Customer Save...',
                    'success'
                  );
                $('#customerModal').modal('hide');
                table.destroy();
                load(); 
            }
    },
        error: function (data) {
            Swal.fire(
                'Failed!',
                'Your Customer cannot be Added.',
                'error'
            );
        }
    });
};

load = () => {
    table = $('#customerTable').DataTable({
        'sAjaxSource': url + "/list",
        'sAjaxDataProp': '',
        'columns': [
            { "defaultContent": "" },
            {'data': 'name'},
            {
                'render': function (data, type, row, meta) {
                    return '<a class="btn btn-sm btn-primary mr-1" onClick="detailSite('+ row.id + ',true)"><i class="far fa-eye"></i></button>' +
                    '<a class="btn btn-sm btn-warning mr-1" onClick="detail('+ row.id + ')"><i class="fas fa-pencil-alt"></i></button>' +
                    '<a class="btn btn-sm btn-danger mr-1" onClick="deleteCustomer('+ row.id + ')"><i class="far fa-trash-alt"></i></button>';
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
    $('#customerModal').modal('show');
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

            setForm(response.id,response.name,response.position,response.site.id);
            
       }
    });
};

detailSite = (id) =>{
    $('#siteDetailModal').modal('show');
    
    $.ajax({
       url:url+"/"+id,
       method:"GET",
       dataType: "JSON",
       success:function(response){
            console.log(response);
            $('#address').text(response.site.address);
            $('#positions').text(response.position);
            $('#sites').text(response.site.name);
       }
    });
};

deleteCustomer = (id) => {
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
                       if(response.message === "Customer deleted"){
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

setForm = (id,name, position,site) => {
     $('#id').val(id);
     $('#name').val(name);
     $('#position').val(position);
     $('#site').val(site).trigger('change');
};

isEnabled = (isEnable) => {
    $('#id').prop("disabled",isEnable);
    $('#name').prop("disabled",isEnable);
    $('#position').prop("disabled",isEnable);
    $('#site').prop("disabled",isEnable);
};
