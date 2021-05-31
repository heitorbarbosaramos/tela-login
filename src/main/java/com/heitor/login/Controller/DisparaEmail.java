package com.heitor.login.Controller;

import com.heitor.login.entidades.Usuario;
import com.heitor.login.services.email.CorpoEmail;
import com.heitor.login.services.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/email")
public class DisparaEmail {

    @Autowired
    private EmailService serviceEmail;

    @GetMapping("/esqueceu-senha/{email}")
    public ResponseEntity<?> esqueceuSenha(@PathVariable(value = "email") String email){
        String miolo = CorpoEmail.corpoEmail("Prezado, \n\n Favor entrar em contato com o administrador do sistema para resetar a senha", "Recupera Senha");
        serviceEmail.sendMail(email, "Esqueceu a senha", miolo);
        return ResponseEntity.ok().build();
    }
}
