package com.heitor.login.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContatoNaoEncontrado extends RuntimeException {

    public ContatoNaoEncontrado(Long id){
        super("Não foi possivel localizar o contato ID: " + id);
    }
}
