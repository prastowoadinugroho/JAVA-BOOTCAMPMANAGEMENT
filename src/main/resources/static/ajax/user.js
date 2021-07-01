let url = "http://localhost:8080/bootcamp-management/user";
let id = $("#id").val();

$(document).ready( function () {
    load();
    
    $('#btn-add').on('click',function(){
        setForm("","");
        $('#userModal').modal("show");
        $('#form')[0].reset();
        $('.userId').select2().val(null).trigger('change');
        $('.username').select2().val(null).trigger('change');
    });

    $('#btn-save').on('click',function(e){
        e.preventDefault();
            saveUser(url);
    }); 
    
});

saveUser = (url) => {
    var data = $('#form').serialize();
      const swalWithBootstrapButtons = Swal.mixin({
  customClass: {
    confirmButton: 'btn btn-success ml-3',
    cancelButton: 'btn btn-danger'
  },
  buttonsStyling: false
})

swalWithBootstrapButtons.fire({
  title: 'Are you sure?',
  text: "You won't be accept this request!",
  icon: 'warning',
  showCancelButton: true,
  confirmButtonText: 'Yes, accept it!',
  cancelButtonText: 'No, cancel!',
  reverseButtons: true
}).then((result) => {
  if (result.isConfirmed) {
     $.ajax({
     url:url + "/add",
     method:"POST",
     data:data,
     dataType: "JSON",
     success:function(response){
        if(response.message === "User created"){
          swalWithBootstrapButtons.fire(
              'Save Success!',
                    response.message,
                    'success'
          );
            $('#userModal').modal('hide');
            table.destroy();
            load(); 
        }
     }
  });
  } else if (
    result.dismiss === Swal.DismissReason.cancel
  ) {
    swalWithBootstrapButtons.fire(
      'Cancelled',
      'Request is safe :)',
      'error'
    );
  }
});
};

load = () => {
    table = $('#userTable').DataTable({
        'sAjaxSource': url + "/list",
        'sAjaxDataProp': '',
        "bInfo": false,
        'columns': [
            {"defaultContent": "" },
            {'data': 'username'},
            {
                "render": function(data, type, row, meta) {
                    return '<a class="btn btn-sm btn-danger" onClick="deleteUser('+ row.id + ')"><i class="far fa-trash-alt"></i></a>';
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

deleteUser = (id) => {
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
                       if(response.message === "User deleted"){
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

setForm = (interviewDate,candidateNeeded) => {
     $('#id').val(interviewDate);
     $('#name').val(candidateNeeded);
};
