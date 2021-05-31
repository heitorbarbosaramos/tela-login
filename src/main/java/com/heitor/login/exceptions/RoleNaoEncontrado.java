package com.heitor.login.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleNaoEncontrado extends RuntimeException{

    public RoleNaoEncontrado(String nomeRole){
        super("Regra Role não encontrada: " + nomeRole);
    }
}
