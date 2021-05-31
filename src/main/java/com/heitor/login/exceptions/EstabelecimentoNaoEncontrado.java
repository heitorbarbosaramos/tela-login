package com.heitor.login.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EstabelecimentoNaoEncontrado extends RuntimeException{

    public EstabelecimentoNaoEncontrado(Integer id){
        super("Estabelecimento não encontrado ID: " + id);
    }
}
