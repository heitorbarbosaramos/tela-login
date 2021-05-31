package com.heitor.login.services;

import com.heitor.login.entidades.Contato;
import com.heitor.login.entidades.Endereco;
import com.heitor.login.entidades.Estabelecimento;
import com.heitor.login.enums.StatusEstabelecimento;
import com.heitor.login.exceptions.EstabelecimentoNaoEncontrado;
import com.heitor.login.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository repo;
    @Autowired
    private EnderecoService serviceEndereco;
    @Autowired
    private ContatoService serviceContato;

    public Estabelecimento save(Estabelecimento estabelecimento){
        return repo.save(estabelecimento);
    }

    public Estabelecimento save(String nomeProprietario, String nomeFantasia, String cnpj, String cpf, String cep, String logradouro, String complemento, String bairro, String localidade, String numero, String uf, String email, String telefone, String descricaoStatus){
        Endereco endereco = serviceEndereco.save(cep, logradouro, complemento, bairro, localidade, numero, uf);
        Contato contato = serviceContato.save(email, telefone);
        StatusEstabelecimento status = StatusEstabelecimento.findByDescricao(descricaoStatus);

        Estabelecimento estabelecimento = new Estabelecimento(null, nomeProprietario, nomeFantasia, cnpj, cpf, endereco, contato, status.getDecricao());

        return repo.save(estabelecimento);
    }

    public Estabelecimento save(Integer id, String nomeProprietario, String nomeFantasia, String cnpj, String cpf, String cep, String logradouro, String complemento, String bairro, String localidade, String numero, String uf, String email, String telefone, String descricaoStatus){

        Estabelecimento estabelecimento = findById(id);

        Endereco endereco = estabelecimento.getEndereco();
        endereco.setUf(uf);
        endereco.setBairro(bairro);
        endereco.setLocalidade(localidade);
        endereco.setComplemento(complemento);
        endereco.setNumero(numero);
        endereco.setCep(cep);

        serviceEndereco.save(endereco);

        Contato contato = estabelecimento.getContato();
        contato.setTelefone(telefone);
        contato.setEmail(email);

        serviceContato.save(contato);

        StatusEstabelecimento status = StatusEstabelecimento.findByDescricao(descricaoStatus);

        estabelecimento.setEndereco(endereco);
        estabelecimento.setContato(contato);
        estabelecimento.setNomeFantasia(nomeFantasia);
        estabelecimento.setNomeProprietario(nomeProprietario);
        estabelecimento.setStatus(status.getDecricao());


        return repo.save(estabelecimento);
    }

    public Estabelecimento findById(Integer idEstabelecimento){
        return repo.findById(idEstabelecimento).orElseThrow(() -> new EstabelecimentoNaoEncontrado(idEstabelecimento));
    }

    public List<Estabelecimento> findAll() {
        return repo.findAll();
    }

    public void delete(Estabelecimento estabelecimento){
        repo.delete(estabelecimento);
    }


}
