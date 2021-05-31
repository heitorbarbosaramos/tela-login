package com.heitor.login.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNaoEncontrado extends RuntimeException {

    public UsuarioNaoEncontrado(Integer id){
        super("Não foi possivel localizar o cliente ID: " + id);
    }
}
