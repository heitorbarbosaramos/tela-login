$(document).ready( function () {
    $('#telefone').mask('(00) 00000-0000');
    $('#cep').mask('00000-000');

    console.log($("#idUsuario").val());
    var idUsuario = $("#idUsuario").val();
    $.ajax({
        method:"GET",
        url:"/usuario/id/" + $("#idUsuario").val(),
        success:function(data){

         $("#status").attr("checked", data.status);

         for (var i = 0; i < data.roles.length; i++) {
                $("#roleUsuario-" + data.roles[i]['descricao']).attr("checked", true);
         }

        }
    })

} );

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

$("#botaoSalvarNovoContato").on("click", function(){
    var telefone = $("#telefone").val();
    var email = $("#email").val();
    var idUsuario = $("#idUsuario").val();

    if(telefone == '' & email == ''){
        $("#telefone").addClass("form-control is-invalid");
        $("#email").addClass("form-control is-invalid");
        return false;
    }

    $.ajax({
        method:"POST",
        url:"/usuario/adciona-contato",
        data:{idUsuario, email, telefone},
        beforeSend:function(){
            $("#spinerNovoContato").addClass("spinner-border spinner-border-sm");
            $("#telefone").removeClass("form-control is-invalid");
            $("#email").removeClass("form-control is-invalid");
        },
        success:function(){
            location.reload();
        },
        error:function(){
            alert("Erro ao inserir novo contato");
            $("#spinerNovoContato").addClass("spinner-grow text-danger");
        }
    })

})

$("button[id*='removerContato-']").on("click", function(){
    var idContato = $(this).attr("id").split("-")[1];
    var idUsuario = $("#idUsuario").val();
    console.log("REMOVE CONTATO ID " + idUsuario + " DO CLIENTE ID: " + idContato);

    $.ajax({
        method:"DELETE",
        url:"/usuario/delete-contato",
        data:{idUsuario, idContato},
        beforeSend:function(){
            $("#spinerContato-" + idContato).addClass("spinner-border spinner-border-sm");

        },
        success:function(){
            location.reload();
        },
        error:function(xhr){
            alert("ERROR AO EXCLUIR CONTATO");
            $("#spinerContato-" + idContato).removeClass("spinner-border spinner-border-sm");
        }
    })


    $("#botaoSalvarNovoContato").on("click", function(){
        var telefone = $("#telefone").val();
        var email = $("#email").val();
        var idUsuario = $("#idUsuario").val();
        console.log("ADD CONTATO NO USUARIO: " + idUsuario);
        $.ajax({
            method:"POST",
            url:"/usuario/adciona-contato",
            data:{idUsuario, email,telefone},

        })
    })

})

$("#botaoSalvarNovoEndereco").on("click", function(){

    var cep = $("#cep").val();
    var logradouro = $("#logradouro").val();
    var complemento = $("#complemento").val();
    var bairro = $("#bairro").val();
    var localidade = $("#localidade").val();
    var numero = $("#numero").val();
    var uf = $("#uf").val();
    var idUsuario = $("#idUsuario").val();

    if(logradouro == '' |  bairro == '' |  localidade == '' | numero == '' | uf == ''){
       (logradouro == '' )  ?  $("#logradouro").addClass("is-invalid")    : $("#logradouro").removeClass("is-invalid");
       (bairro == '' )      ?  $("#bairro").addClass("is-invalid")        : $("#bairro").removeClass("is-invalid");
       (localidade == '' )  ?  $("#localidade").addClass("is-invalid")    : $("#localidade").removeClass("is-invalid");
       (numero == '' )      ?  $("#numero").addClass("is-invalid")        : $("#numero").removeClass("is-invalid");
       (uf == '' )          ?  $("#uf").addClass("is-invalid")            : $("#uf").removeClass("is-invalid");
        return null;
    }

    $.ajax({
        method:"POST",
        url:"/usuario/adciona-endereco",
        data:{idUsuario, cep, logradouro, complemento, bairro, localidade, numero, uf },
        beforeSend:function(){
            $("#spinerAddEndereco").addClass("spinner-border spinner-border-sm");
        },
        success:function(){
            $("#spinerAddEndereco").removeClass("spinner-border spinner-border-sm");
            location.reload();
        },
        error:function(xhr){
            console.log(xhr);
            alert("Erro ao adcionar novo endereço!")
        }
    })


})

$("button[id*='botaoRemoverEndereco-']").on("click", function(){

    var idEndereco = $(this).attr("id").split("-")[1];
    var idUsuario = $("#idUsuario").val();

    $.ajax({
        method:"DELETE",
        url:"/usuario/delete-endereco",
        data:{idUsuario, idEndereco},
        beforeSend:function(){
            $("#botaoRemoverEnderecoSpinner-" + idEndereco).addClass("spinner-border spinner-border-sm");
        },
        success:function(){
            $("#botaoRemoverEnderecoSpinner-" + idEndereco).removeClass("spinner-border spinner-border-sm");
            location.reload();
        },
        error:function(xhr){
            console.log(xhr);
            alert("Erro ao excluir endereço!")
        }
    })


})

$("#login").on("change", function(){
    var login = $(this).val();
    var idUsuario = $("#idUsuario").val();

    $.ajax({
        method:"PUT",
        url:"/usuario/atualizar-login",
        data:{idUsuario, login},
        beforeSend:function(){
            $("#login").removeClass("is-invalid");
            $("#login").removeClass("is-valid");
        },
        success:function(data){
            $("#login").addClass("is-valid");
            $("#loginCompara").val(login);
        },
        error:function(data){
            console.log(data);
            $("#login").addClass("is-invalid");
            $("#login").val($("#loginCompara").val());
            alert("Não é possivel trocar o login, possivelmente está em uso");
        }
    })

 })

$("#senha").on("change", function(){
     var senha = $(this).val();
     var idUsuario = $("#idUsuario").val();

     $.ajax({
          method:"PUT",
          url:"/usuario/atualizar-senha",
          data:{idUsuario, senha},
          beforeSend:function(){
              $("#senha").removeClass("is-valid");
              $("#senha").removeClass("is-invalid");
          },
          success:function(){
               $("#senha").addClass("is-valid");
          },
          error:function(){
              $("#senha").addClass("is-invalid");
          }
     })
})

$("#nomeCompleto").on("change", function(){
    var nomeCompleto = $(this).val();
    var idUsuario = $("#idUsuario").val();

    console.log(nomeCompleto);
    console.log(idUsuario);

    $.ajax({
        method:"PUT",
        url:"/usuario/atualizar-nome",
        data:{idUsuario, nomeCompleto},
        beforeSend:function(){
            $("#nomeCompleto").removeClass("is-valid");
            $("#nomeCompleto").removeClass("is-invalid");
        },
        success:function(){
            $("#nomeCompleto").addClass("is-valid");
        },
        error:function(xhr){
            $("#nomeCompleto").addClass("is-invalid");
        }
    })

})

$("#roleUsuario-Administrador").on("change",function(){
    var roleNome = "ROLE_ADMIN";
    var idUsuario = $("#idUsuario").val();
    $.ajax({
        method:"PUT",
        url:"/usuario/atualizar-role",
        data:{idUsuario, roleNome},
        success:function(){
            console.log("Acesso atualizado");
        },
        error:function(xhr){
            console.log(xhr);
            alert("Error ao atualizar nivel de acesso");
        }
    })
})

$("#roleUsuario-Gerente").on("change",function(){
    var roleNome = "ROLE_GERENTE";
    var idUsuario = $("#idUsuario").val();
    $.ajax({
        method:"PUT",
        url:"/usuario/atualizar-role",
        data:{idUsuario, roleNome},
        success:function(){
            console.log("Acesso atualizado");
        },
        error:function(xhr){
            console.log(xhr);
            alert("Error ao atualizar nivel de acesso");
        }
    })
})

$("#roleUsuario-Caixa").on("change",function(){
    var roleNome = "ROLE_CAIXA";
    var idUsuario = $("#idUsuario").val();
    $.ajax({
        method:"PUT",
        url:"/usuario/atualizar-role",
        data:{idUsuario, roleNome},
        success:function(){
            console.log("Acesso atualizado");
        },
        error:function(xhr){
            console.log(xhr);
            alert("Error ao atualizar nivel de acesso");
        }
    })
})

$("#roleUsuario-Atendente").on("change",function(){
    var roleNome = "ROLE_ATENDENTE";
    var idUsuario = $("#idUsuario").val();
    $.ajax({
        method:"PUT",
        url:"/usuario/atualizar-role",
        data:{idUsuario, roleNome},
        success:function(){
            console.log("Acesso atualizado");
        },
        error:function(xhr){
            console.log(xhr);
            alert("Error ao atualizar nivel de acesso");
        }
    })
})

$("input[name='status']").on("click",function(){

    var status = $(this).val();
    var idUsuario = $("#idUsuario").val();

    $.ajax({
        method:"PUT",
        url:"/usuario/atualizar-status",
        data:{idUsuario, status},
        success:function(){
            console.log("STATUS ALTERADO!")
        },
        error:function(xhr){
            console.log(xhr);
            alert("Error em alterar status");
        }
    })
})