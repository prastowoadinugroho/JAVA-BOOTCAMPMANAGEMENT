let url = "http://localhost:8080/bootcamp-management/interview";
let id = $("#id").val();

$(document).ready( function () {
    load(); 
    $('#btn-save').on('click',function(e){
        e.preventDefault();
            saveQualified(url);
    });
});

load = () => {
    table = $('#interviewTable').DataTable({
        'sAjaxSource': url + "/list",
        'sAjaxDataProp': '',
        "bInfo": false,
        'columns': [
            {"defaultContent": "" },
            {'data': 'employee.name'},
            {'data': 'request.customer.site.name'},
            {'data': 'request.customer.name'},
            {
                "render": function(data, type, row, meta) {
                    return '<a class="btn btn-sm btn-primary mr-1" onClick="qualified('+ row.id + ')"> Qualified </button>' + 
                            '<a class="btn btn-sm btn-danger mr-1" onClick="unqualified('+ row.id + ')"> Unqualified </button>';
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

qualified = (cId) => {
    $('#idInter').val(cId);
    $('#qualifiedModal').modal('show');
};

saveQualified = (url) => {
    var data = $('#form').serialize();
    $.ajax({
        url:url+'/add/qualified',
        type:"POST",
        data:data,
        dataType: "JSON",
        success: function (response) {
            console.log(response);
            if(response.message === "Interview updated"){
                Swal.fire(
                    'Success!',
                    'Interview Save...',
                    'success'
                  );
                $('#qualifiedModal').modal('hide');
                location.reload();
            }
    },
        error: function (data) {
            Swal.fire(
                'Failed!',
                'Your Skill cannot be Added.',
                'error'
            );
        }
    });
}

unqualified = (cId) => {
    id = cId;
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
          confirmButton: 'btn btn-success ml-2',
          cancelButton: 'btn btn-danger'
        },
        buttonsStyling: false
      })

swalWithBootstrapButtons.fire({
  title: 'Are you sure to send Unqualified result?',
  text: "You won't be able to revert this!",
  icon: 'warning',
  showCancelButton: true,
  confirmButtonText: 'Yes, invite it!',
  cancelButtonText: 'No, cancel!',
  reverseButtons: true
}).then((result) => {
  if (result.isConfirmed) {
        $.ajax({
        url:url + "/unqualified/" + id,
        type:"GET",
        dataType: "JSON",
        success: function (response) {
            console.log(response.data);
            if(response.data.message === "Interview updated"){
                Swal.fire(
                    'Success!',
                    'Interview updated...',
                    'success'
                  );
            setTimeout(function(){
                window.location.reload(1);
            }, 900);
        }
    }  
    });
  } else if (
    result.dismiss === Swal.DismissReason.cancel
  ) {
    swalWithBootstrapButtons.fire(
      'Cancelled',
      'Your imaginary file is safe :)',
      'error'
    )
  }
})
};
