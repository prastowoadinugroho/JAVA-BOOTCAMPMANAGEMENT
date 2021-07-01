let url = "http://localhost:8080/bootcamp-management/request";
let id = $("#id").val();

$(document).ready( function () {
    load();
    $('#btn-edit').on('click',function(e){
        e.preventDefault();
            editRejectNote(url);
    }); 
});

load = () => {
    table = $('#myTable').DataTable({
        'sAjaxSource': url + "/req-reject",
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
                            '<a class="btn btn-sm btn-info mr-1" onClick="note('+ row.id + ')"> Reject Note </a>' + 
                                '<a class="btn btn-sm btn-warning mr-1" onClick="editNote('+ row.id + ')"> Edit Note </a>';
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


function dateFormat(d) {
    return ((d.getMonth() + 1) + "").padStart(2, "0")
        + "/" + (d.getDate() + "").padStart(2, "0")
        + "/" + d.getFullYear();
}
