let url = "http://localhost:8080/bootcamp-management/request";
let id = $("#id").val();

$(document).ready( function () {
    load();
    $('#btn-add').on('click',function(){
        setForm("","");
        $('#requestModal').modal("show");
        $('#form')[0].reset();
        $('#skill').select2().val(null).trigger('change');
        $('#customer').select2().val(null).trigger('change');
    });

    $('#btn-save').on('click',function(e){
        e.preventDefault();
            saveClass(url);
    }); 
        
    $('#btn-saves').on('click',function(e){
        e.preventDefault();
            saveReject(url);
    }); 
    
    $('#btn-edit').on('click',function(e){
        e.preventDefault();
            editRejectNote(url);
    }); 
    
    $('#btn-edit-request').on('click',function(e){
        e.preventDefault();
            saveEdit(url);
    }); 
    
});

saveClass = (url) => {
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
     url:url+ "/add",
     method:"POST",
     data:data,
     dataType: "JSON",
     success:function(response){
        if(response.message === "Request created"){
          swalWithBootstrapButtons.fire(
              'Save Success!',
                    response.message,
                    'success'
          );
        timer: 10000;
        location.reload(true);
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
    table = $('#myTable').DataTable({
        'sAjaxSource': url + "/null",
        'sAjaxDataProp': '',
        "bInfo": false,
        'columns': [
            {"defaultContent": "" },
            {'data': 'customer.site.name'},
            {'data': 'interviewDate',
                "render": function (data) {
                    var date = new Date(data);
                    var month = date.getMonth() + 1;
                    return (month.toString().length > 1 ? month : "0" + month) + "/" + date.getDate() + "/" + date.getFullYear();
                }
            },
            {
                "render": function(data, type, row, meta) {
                    return '<a class="btn btn-sm btn-primary mr-1" onClick="detailReq('+ row.id + ')"> Detail </a>' +
                            '<a class="btn btn-sm btn-warning mr-1" onClick="editReq('+ row.id + ')"> Edit </a>' +
                        '<a class="btn btn-sm btn-success mr-1" onClick="accept('+ row.id + ')"> Accept </a>' +
                    '<a class="btn btn-sm btn-danger" onClick="reject('+ row.id + ')"  data-toggle="modal" data-target="#rejectModal"> Reject </a>';
                   
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

detailReq = (id) => {
    $('#detailRequest').modal('show');
    $.ajax({
       url:url+"/list/"+id,
       method:"GET",
       dataType: "JSON",
       success:function(response){
           $('#address').text(response.customer.site.address);
           $('#reqdate').text(dateFormat(new Date(response.interviewDate)));
           $('#reqcandidate').text(response.candidateNeeded);
           $('#reqcustomer').text(response.customer.name + " - " + response.customer.position);
           $('#reqskill').text(response.skill.name);
       }
    });
}

editReq = (id) => {
    $('#editRequestModal').modal('show');
     $.ajax({
       url:url+"/list/"+id,
       method:"GET",
       dataType: "JSON",
       success:function(response){
           console.log(response);
           setForm(response.id,response.candidateNeeded,response.customer.id,response.skill.id);
       }
    });
}

accept = (id) => {
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
     url:url+"/accept/"+id,
     method:"GET",
     dataType: "JSON",
     success:function(response){
        if(response.message === "Request updated"){
          swalWithBootstrapButtons.fire(
              'Accepted!',
              'Request has been accepted.',
              'success'
          );
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
    )
  }
})
};
    
reject = (cid) => {
    $('#reqId').val(cid);
};

note = (id) => {
    $('#noteModal').modal('show');
    $.ajax({
       url:url+"/list/"+id,
       method:"GET",
       dataType: "JSON",
       success:function(response){
           $('#note').text(' " ' + response.rejectNote + ' " ');
           $('#date').text('Rejected At - ' + dateFormat(new Date(response.statusDate)));
       }
    });
}

editNote = (id) => {
    $('#editNoteModal').modal('show');
    $.ajax({
       url:url+"/list/"+id,
       method:"GET",
       dataType: "JSON",
       success:function(response){
           $('input#reqId').val(response.id);
           $('textarea#reject').val(response.rejectNote);
       }
    });
}

saveReject = (url) => {
    var data = $('#forms').serialize();
    $.ajax({
        url:url+'/reject',
        type:"POST",
        data:data,
        dataType: "JSON",
        success: function (response) {
            if(response.message === "Request updated"){
                Swal.fire(
                    'Success!',
                    'Skill Save...',
                    'success'
                  );
                $('#rejectModal').modal('hide');
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

saveEdit = (url) => {
    var data = $('#form-editRequest').serialize();
    $.ajax({
        url:url+'/edit',
        type:"POST",
        data:data,
        dataType: "JSON",
        success: function (response) {
            if(response.message === "Request created"){
                Swal.fire(
                    'Success!',
                    'Request Save...',
                    'success'
                  );
                $('#editRequestModal').modal('hide');
                location.reload();
            }
    },
        error: function (data) {
            Swal.fire(
                'Failed!',
                'Your Request cannot be Added.',
                'error'
            );
        }
    });
}

editRejectNote = (url) => {
    var data = $('#form-edit').serialize();
    $.ajax({
        url:url+'/reject',
        type:"POST",
        data:data,
        dataType: "JSON",
        success: function (response) {
            if(response.message === "Request updated"){
                Swal.fire(
                    'Success!',
                    'Skill Save...',
                    'success'
                  );
                $('#editNoteModal').modal('hide');
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

setForm = (idReqEdit,candidateNeededReqEdit,customerReqEdit,skillReqEdit) => {
     $('#idReqEdit').val(idReqEdit);
     $('#candidateNeededReqEdit').val(candidateNeededReqEdit);
     $('#customerReqEdit').val(customerReqEdit).trigger('change');
     $('#skillReqEdit').val(skillReqEdit).trigger('change');
};

function dateFormat(d) {
    return ((d.getMonth() + 1) + "").padStart(2, "0")
        + "/" + (d.getDate() + "").padStart(2, "0")
        + "/" + d.getFullYear();
}
