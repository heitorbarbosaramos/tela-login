package com.heitor.login.Controller;

import com.heitor.login.entidades.Endereco;
import com.heitor.login.services.utils.BuscaEnderecoPorCep;
import com.heitor.login.services.utils.ValidaDocumentos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/servicos")
public class ServicosRestController {

    @RequestMapping(value = "/busca-endereco-por-cep/{cep}", method = RequestMethod.GET)
    public ResponseEntity<?> buscaEnderecoPorCep(@PathVariable(value = "cep") String cep){
        Endereco endereco = BuscaEnderecoPorCep.buscarCep(cep);
        return ResponseEntity.ok(endereco);
    }

    @RequestMapping(value = "/valida-cpf/{cpf}", method = RequestMethod.GET)
    public ResponseEntity<?> validaCPF(@PathVariable String cpf){
        if(ValidaDocumentos.validaCPF(cpf)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/valida-cnpj/{cnpj}", method = RequestMethod.GET)
    public ResponseEntity<?> validaCNPJ(@PathVariable String cnpj){
        if(ValidaDocumentos.validaCNPJ(cnpj)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }



}
