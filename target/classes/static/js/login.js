$(document).ready(function () {
    var url  = window.location.href;
    var page = url.split('/');
    var page = page[page.length-1];
    if(page == 'login?error'){
        $("#mensagemLogin").show();
        alert("Usuario ou senha estão errados");
    }
})

$("#senha").on("click", function(){
    var email = $("#username").val();
    alert("senha ");
    if(email == ""){
        alert("Preencha o campo de login com o seu email");
        return null;
    }
    $.ajax({
        method:"GET",
        url:"/email/esqueceu-senha/" + email,
        success:function(){
            alert("Email enviado para "+ email +" \n entre em contato com seu administrador");
        },
        error:function(xhr){
         alert("Email não enviado entre em contato com seu administrador");
        }
    })

})