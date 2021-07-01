let url = "http://localhost:8080/bootcamp-management/request";
let url2 = "http://localhost:8080/bootcamp-management/interview";
let id = $("#id").val();

$(document).ready( function () { 
    load();
    $('#btn-save').on('click',function(e){
        e.preventDefault();
            invite(url);             
    });   
});

load = () => {
    table = $('#interviewTable').DataTable();
};

detail = (id,skill,emp) =>{
    $('#detailModal').modal('show');
    $.ajax({
       url:url+"/detail/"+id+"/"+skill+"/"+emp,
       method:"GET",
       dataType: "JSON",
       success:function(response){
           $('#email').text(response.data[0].email);
           $('#phone').text(response.data[0].phone);
           $('#address').text(response.data[0].address);
           $('#job').text(response.data[0].job);
           $('#class').text(response.data[0].classes);
       }
    });
};

invite = (req, emp) => {
    $('#req').val(req);
    $('#emp').val(emp);
    var data = $('#form').serialize();
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
          confirmButton: 'btn btn-success ml-2',
          cancelButton: 'btn btn-danger'
        },
        buttonsStyling: false
      })

swalWithBootstrapButtons.fire({
  title: 'Are you sure to invite?',
  text: "You won't be able to revert this!",
  icon: 'warning',
  showCancelButton: true,
  confirmButtonText: 'Yes, invite it!',
  cancelButtonText: 'No, cancel!',
  reverseButtons: true
}).then((result) => {
  if (result.isConfirmed) {
        $.ajax({
        url:url2 + "/save",
        type:"POST",
        data:data,
        dataType: "JSON",
        success: function (response) {
            if(response.message === "Interview created"){
                Swal.fire(
                    'Success!',
                    'Interview Save...',
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

skill = (emp) =>{
    $.ajax({
        url:url+"/candidate/skill/"+emp,
        method:"GET",
        dataType: "JSON",
        success:function(response){
        $('#coba').empty();
        var clearHTML = '<div></div>';
        var trHTML = '';
        $.each(response, function() {
            $.each(this, function(k, v) {
                trHTML += '<div>' + 
                        ' <h6 class="medium font-weight-bold">' + v.skill + '<h6>'  +
                        ' <h6 class="medium font-weight-medium">' + v.description + '<h6>' +
                        '<hr style="width: 70%">'
                        + '</div>';
                console.log(k,v); 
            });
        $('#coba').append(clearHTML);
        $('#coba').append(trHTML);
        });
       }
    });
};

setForm = (id,skill,emp) => {
    $('#id').val(id);
    $('#skill').val(skill);
    $('#emp').val(emp);
};

