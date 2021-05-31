$(document).ready( function () {
    $('#tabela-todos-usuarios').DataTable();

    $('#telefone').mask('(00) 00000-0000');
    $('#cep').mask('00000-000');

} );

$(document).on("click","button[id*='editar-usuario-']", function(){
    var id = $(this).attr("id").split("-")[2];
    console.log("USUARIO ID EDITAR " + id);

})

$("#login").change(function(){

    $("#email").val($(this).val());

    var login = $(this).val();

    $.ajax({
        method:"GET",
        url:"/usuario/busca-por-login/" + login,
        success:function(){

            document.getElementById("form-new-usuario").reset();
            alert("Usuario já cadastrado");

            $("#mensagem-alerta-erro").text("Usuario já cadastrado");

        }
    })
})

$("#cep").change(function(){
    var cep = $(this).val();

    console.log("BUSCA ENDERECO PELO CEP" + cep);

    $.ajax({
        method:"GET",
        url:"/servicos/busca-endereco-por-cep/" + cep,
        beforeSend:function(){
            $("#spinnerCep").removeClass("spinner-grow text-danger");
            $("#spinnerCep").addClass("spinner-border");
            $("#statusCep").text("Buscando endereço atravé do CEP");
        },
        success:function(data){
            console.log(data);
            $("#spinnerCep").removeClass("spinner-border");
            $("#spinnerCep").addClass("spinner-grow text-success");
            $("#statusCep").addClass("form-text text-muted");
            $("#statusCep").text("Endereço preenchido através do site");

            $("#logradouro").val(data.logradouro);
            $("#complemento").val(data.complemento);
            $("#bairro").val(data.bairro);
            $("#localidade").val(data.localidade);
            $("#numero").val(data.numero);
            $("#uf").val(data.uf);

        },
        error:function(xhr){
            console.log("ERROR AO BUSCAR ENDERECO PELO SITE");
            $("#spinnerCep").removeClass("spinner-border");
            $("#spinnerCep").addClass("spinner-grow text-danger");
            $("#statusCep").addClass("form-text text-muted");
            $("#statusCep").text("Erro ao Buscar o endereço, preencha os campos manualmente");

            $("#logradouro").val("");
            $("#complemento").val("");
            $("#bairro").val("");
            $("#localidade").val("");
            $("#numero").val("");
            $("#uf").val("");
        }
    })

});

$("#form-new-usuario").submit(function(evt){
    console.log("CADASTRO NOVO USUARIO");
    evt.preventDefault();
    var nomeCompleto = $("#nomeCompleto").val();
    var login = $("#login").val();
    var senha = $("#senha").val();
    var status = $('input[name="status"]:checked').val();
    var telefone = $("#telefone").val();
    var email = $("#email").val();
    var cep = $("#cep").val();
    var logradouro = $("#logradouro").val();
    var complemento = $("#complemento").val();
    var bairro = $("#bairro").val();
    var localidade = $("#localidade").val();
    var numero = $("#numero").val();
    var uf = $("#uf").val();


    var inputs = $('input:checkbox:checked');
    var role = '';
    for (var i = 0; i < inputs.length; i++) {
            role += inputs[i].value.split("-")[1] +",";
        }

    $.ajax({
        method:"POST",
        url:"/usuario/adicionar-usuario",
        data:{nomeCompleto, login, senha, status, telefone, email, cep, logradouro, complemento, bairro, localidade, numero, uf, role},
         beforeSend:function(){
            $("#spinerSalvar").addClass("spinner-border spinner-border-sm");
            console.log("PREPARANDO UM NOVO REGISTRO");
         },
        success:function(data){
            console.log(data);

            document.getElementById("form-new-usuario").reset();
            alert("Usuario cadastrado com sucesso");

            window.location.href = "/usuario/edita/busca/" + data.id;

        },
        error:function(xhr){
            console.log(xhr);
             $("#spinerSalvar").removeClass("spinner-border spinner-border-sm");
            alert("Erro ao cadastrar o usuário");
        }

    })
})
