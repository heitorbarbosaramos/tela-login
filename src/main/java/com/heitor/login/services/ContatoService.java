package com.heitor.login.services;

import com.heitor.login.entidades.Contato;
import com.heitor.login.exceptions.ContatoNaoEncontrado;
import com.heitor.login.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContatoService {

    private ContatoRepository repo;

    @Autowired
    public ContatoService(ContatoRepository repo){
        this.repo = repo;
    }

    public Contato save(Contato contato){
        return repo.save(contato);
    }

    public Contato save(String email, String telefone){
        Contato contato = new Contato();
        contato.setEmail(email);
        contato.setTelefone(telefone);
        return  repo.save(contato);
    }

    public Contato findById(Long id){
        return repo.findById(id).orElseThrow(() -> new ContatoNaoEncontrado(id));
    }

    public void delete(Contato contato){
        repo.delete(contato);
    }
}
