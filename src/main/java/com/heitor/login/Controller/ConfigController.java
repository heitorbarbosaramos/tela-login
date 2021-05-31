package com.heitor.login.Controller;

import com.heitor.login.entidades.Estabelecimento;
import com.heitor.login.enums.StatusEstabelecimento;
import com.heitor.login.services.EstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/config")
public class ConfigController {

    @Autowired
    private EstabelecimentoService serviceEstalecimento;

    @GetMapping("/estabelecimento")
    public String estabelecimento(ModelMap map){
        List<StatusEstabelecimento> lista = StatusEstabelecimento.findAll();
        map.addAttribute("status", lista);
        map.addAttribute("lojas", serviceEstalecimento.findAll());
        return "pg-config";
    }

    @PostMapping("/estabelecimento/salvar")
    public ResponseEntity<?> save(Integer idEstabelecimento, String nomeProprietario, String nomeFantasia, String cnpj, String cpf, String cep, String logradouro, String complemento, String bairro, String localidade, String numero, String uf, String email, String telefone, String statusEstabelecimento){
        Estabelecimento estabelecimento;
        if(idEstabelecimento!=null){
            estabelecimento = serviceEstalecimento.save(idEstabelecimento, nomeProprietario, nomeFantasia, cnpj, cpf, cep, logradouro, complemento, bairro, localidade, numero, uf, email, telefone, statusEstabelecimento);
        }else {
            estabelecimento = serviceEstalecimento.save(nomeProprietario, nomeFantasia, cnpj, cpf, cep, logradouro, complemento, bairro, localidade, numero, uf, email, telefone, statusEstabelecimento);
        }
        return ResponseEntity.ok(estabelecimento);
    }

    @GetMapping("/estabelecimento/{idEstabelecimento}")
    public String editarEstabelecimento(@PathVariable(value = "idEstabelecimento") Integer idEstabelecimento, ModelMap map){
        List<StatusEstabelecimento> lista = StatusEstabelecimento.findAll();
        map.addAttribute("status", lista);

        Estabelecimento estabelecimento = serviceEstalecimento.findById(idEstabelecimento);
        map.addAttribute("idEstabelecimento", estabelecimento.getId());
        map.addAttribute("cnpj", estabelecimento.getCnpj());
        map.addAttribute("cpf", estabelecimento.getCpf());
        map.addAttribute("nomeProprietario", estabelecimento.getNomeProprietario());
        map.addAttribute("nomeFantasia", estabelecimento.getNomeFantasia());

        map.addAttribute("email", estabelecimento.getContato().getEmail());
        map.addAttribute("telefone", estabelecimento.getContato().getTelefone());
        map.addAttribute("principal", estabelecimento.getStatus());

        map.addAttribute("cep", estabelecimento.getEndereco().getCep());
        map.addAttribute("logradouro", estabelecimento.getEndereco().getLogradouro());
        map.addAttribute("complemento", estabelecimento.getEndereco().getComplemento());
        map.addAttribute("localidade", estabelecimento.getEndereco().getLocalidade());
        map.addAttribute("bairro", estabelecimento.getEndereco().getBairro());
        map.addAttribute("uf", estabelecimento.getEndereco().getUf());
        map.addAttribute("numero", estabelecimento.getEndereco().getNumero());

        return"pg-edit-estabelecimento";
    }
}
