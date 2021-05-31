package com.heitor.login.services;

import com.heitor.login.entidades.Endereco;
import com.heitor.login.exceptions.EnderecoNaoEncontrado;
import com.heitor.login.repository.EnderecoRepository;
import com.heitor.login.services.utils.BuscaEnderecoPorCep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    private EnderecoRepository repo;

    @Autowired
    public EnderecoService(EnderecoRepository repo){
        this.repo = repo;
    }

    public Endereco save(Endereco endereco){
        return  repo.save(endereco);
    }

    public Endereco findById(Long idEndereco) {
        return repo.findById(idEndereco).orElseThrow(() -> new EnderecoNaoEncontrado());
    }

    public void remove(Endereco endereco){
        repo.delete(endereco);
    }

    public Endereco save(String cep, String numero, String complemento){
        Endereco endereco = BuscaEnderecoPorCep.buscarCep(cep);
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        repo.save(endereco);
        return endereco;
    }

    public Endereco save(String cep, String logradouro, String complemento, String bairro, String localidade, String numero, String uf){
        Endereco endereco = BuscaEnderecoPorCep.buscarCep(cep);
        endereco.setCep(cep);
        endereco.setLocalidade(logradouro);
        endereco.setComplemento(complemento);
        endereco.setBairro(bairro);
        endereco.setLocalidade(localidade);
        endereco.setNumero(numero);
        endereco.setUf(uf);
        repo.save(endereco);
        return endereco;
    }


}
