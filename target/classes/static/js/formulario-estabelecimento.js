$(document).ready( function () {
    $('#cnpj').mask('00.000.000/0000-00');
    $('#cpf').mask('000.000.000-00');
    $("#telefone").mask('(00) 00000-0000');
    $("#cep").mask('00000-000');
    $('#tabela-todas-lojas').DataTable();
} );

$("#cpf").change(function(){
    console.log("VALIDA CPF");
    var cpf = $('#cpf').val();

    $.ajax({
        method:"GET",
        url:"/servicos/valida-cpf/" + cpf,
        beforeSend:function(){
            $("#cpf").removeClass("is-invalid");
        },
        error:function(xhr){
            alert("Error de CPF invalido");
            $("#cpf").addClass("is-invalid");
            $("#cpf").val("");
        }
    })

})

$("#cnpj").change(function(){

    var cnpj = $('#cnpj').val().replaceAll(".","").replaceAll("/","").replaceAll("-","");
    console.log("VALIDA CNPJ: " + cnpj);
    $.ajax({
        method:"GET",
        url:"/servicos/valida-cnpj/" + cnpj,
        beforeSend:function(){
            $("#cnpj").removeClass("is-invalid");
        },
        error:function(xhr){
            alert("Error de CNPJ invalido");
            $("#cnpj").addClass("is-invalid");
            $("#cnpj").val("");
        }
    })

})

$("#form-new-estabelecimento").submit(function(evt){
     console.log("CADASTRO NOVO ESTABELECIMENTO");
     evt.preventDefault();

     var idEstabelecimento = $("#idEstabelecimento").val();
     var nomeProprietario = $("#nomeProprietario").val();
     var nomeFantasia = $("#nomeFantasia").val();
     var cnpj = $("#cnpf").val();
     var cpf = $("#cpf").val();
     var cep = $("#cep").val();
     var logradouro = $("#logradouro").val()
     var complemento = $("#complemento").val()
     var bairro = $("#bairro").val();
     var localidade = $("#localidade").val();
     var numero = $("#numero").val();
     var uf = $("#uf").val();
     var email = $("#email").val();
     var telefone = $("#telefone").val();
     var statusEstabelecimento = $("#statusEstabelecimento").val();

     $.ajax({
        method:"POST",
        url:"/config/estabelecimento/salvar",
        data:{idEstabelecimento, nomeProprietario, nomeFantasia, cnpj, cpf, cep, logradouro, complemento, bairro, localidade, numero, uf, email, telefone, statusEstabelecimento},
        beforeSend:function(){
                    $("#spinerSalvar").addClass("spinner-border spinner-border-sm");
                    console.log("PREPARANDO UM NOVO REGISTRO");
                 },
                success:function(data){
                    console.log(data);

                    document.getElementById("form-new-estabelecimento").reset();
                    alert("Estabelecimento cadastrado com sucesso");
                     $("#spinerSalvar").removeClass("spinner-border spinner-border-sm");

                     location.reload();


                },
                error:function(xhr){
                    console.log(xhr);
                     $("#spinerSalvar").removeClass("spinner-border spinner-border-sm");
                    alert("Erro ao cadastrar novo estabelecimento");
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
