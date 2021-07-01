let url = "http://localhost:8080/bootcamp-management/request";
let id = $("#id").val();

$(document).ready( function () {
    $.ajax({
       url:url+"/"+cId,
       method:"GET",
       dataType: "JSON",
       success:function(response){
           $('#date').text(dateFormat(new Date(response.interviewDate)));
           $('#candidate').text(response.candidateNeeded);
           $('#customer').text(response.customer.name + " - " + response.customer.position);
           $('#skill').text(response.skill.name);
           $('#coba').attr('href','candidate/' +response.skill.name+"/"+response.id);
       }
    });
});


