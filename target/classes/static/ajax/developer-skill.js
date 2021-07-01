let url = "http://localhost:8080/bootcamp-management/developer";

$(document).ready( function () {
//    load();
//    $('#btn-add').on('click',function(){
//        setForm("","");
//        isEnabled(false,false);
//        $('#skillModal').modal("show");
//        $('#btn-save').show();
//        $('#form')[0].reset();
//    });
//    
    $('#btn-save').on('click',function(e){
        e.preventDefault();
        saveSkill(url);             
    });
    $('#btn-saves').on('click',function(e){
        e.preventDefault();
        saveSkills(url);             
    });
    $(".js-example-basic-single").select2({
        tags: true
    });
});

saveSkill = (url) => {
    var data = $('#form').serialize();
    $.ajax({
        url:url + "/save",
        type:"POST",
        data:data,
        dataType: "JSON",
        success: function (response) {
            console.log(response);
            if(response.message === "Employee Skill created"){
                Swal.fire(
                    'Success!',
                    'Skill Save...',
                    'success'
                  );
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
};

saveSkills = (url) => {
    var data = $('#forms').serialize();
    $.ajax({
        url:url + "/save",
        type:"POST",
        data:data,
        dataType: "JSON",
        success: function (response) {
            console.log(response);
            if(response.message === "Employee Skill created"){
                Swal.fire(
                    'Success!',
                    'Skill Save...',
                    'success'
                  );
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
};

detail = (id) => {
    console.log(id);
    $.ajax({
       url:url+"/list/"+id,
       method:"GET",
       dataType: "JSON",
       success:function(response){
//            if(status !== true ){
//                $('#btn-save').show();
//                isEnabled(false,true);
//            }
//            else{
//                 $('#btn-save').hide();
//                 isEnabled(true,true);
//            }
            setForm(response.id,response.level,response.desc, response.employee.id,response.skill.id);
console.log(response);
       }
    });
}


setForm = (id,level,desc, empId,skillId) => {
    $('#id').val(id);
    $('#level').val(level);
    $('#desc').val(desc);
    $('#empId').val(empId);
    $('#skillId').val(skillId);
}