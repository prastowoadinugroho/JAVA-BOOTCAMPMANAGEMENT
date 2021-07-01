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
});

load = () => {
    table = $('#requestTable').DataTable({
        'sAjaxSource': url + "/accept",
        'sAjaxDataProp': '',
        "bInfo": false,
        'columns': [
            {"defaultContent": "" },
            {'data': 'customer.site.name'},
            {
                "render": function(data, type, row, meta) {
                    if(row.status === 'DONE'){
                        return '';
                    }else {
                        return '<a class="btn btn-sm btn-primary mr-1" onClick="detail('+ row.id + ')"> Detail </button>';
                    }
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
    $('#requestModal').modal('show');
    id = cId;
    $.ajax({
       url:url+"/"+cId,
       method:"GET",
       dataType: "JSON",
       success:function(response){
           $('#address').text(response.customer.site.address);
           $('#date').text(dateFormat(new Date(response.interviewDate)));
           $('#candidate').text(response.candidateNeeded);
           $('#customer').text(response.customer.name + " - " + response.customer.position);
           $('#skill').text(response.skill.name);
           $('#coba').attr('href','candidate/' +response.skill.name+"/"+response.id);
       }
    });
};

setForm = (id) => {
    $('#id').val(id);
};

function dateFormat(d) {
    return ((d.getMonth() + 1) + "").padStart(2, "0")
        + "/" + (d.getDate() + "").padStart(2, "0")
        + "/" + d.getFullYear();
}