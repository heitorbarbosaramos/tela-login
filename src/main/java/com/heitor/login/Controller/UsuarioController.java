package com.heitor.login.Controller;

import com.heitor.login.entidades.Contato;
import com.heitor.login.entidades.DTO.UsuarioDTO;
import com.heitor.login.entidades.Endereco;
import com.heitor.login.entidades.Role;
import com.heitor.login.entidades.Usuario;
import com.heitor.login.enums.StatusUsuario;
import com.heitor.login.mapper.UsuarioMapper;
import com.heitor.login.services.ContatoService;
import com.heitor.login.services.EnderecoService;
import com.heitor.login.services.RoleService;
import com.heitor.login.services.UsuarioService;
import com.heitor.login.services.utils.BuscaEnderecoPorCep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService serviceUsuario;
    @Autowired
    private RoleService serviceRole;
    @Autowired
    private ContatoService serviceContato;
    @Autowired
    private EnderecoService serviceEndereco;

    @Autowired
    private UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;


    @GetMapping
    public String findAll(ModelMap map){

        map.addAttribute("roles", serviceRole.findAll());
        map.addAttribute("status", StatusUsuario.listaStatusUsuario());
        map.addAttribute("usuarios", serviceUsuario.findAll());

        return "pg-usuario";
    }

    @GetMapping("/id/{idUsuario}")
    public ResponseEntity<?> findById(@PathVariable(value = "idUsuario") Integer idUsuario){
        return ResponseEntity.ok(serviceUsuario.findById(idUsuario));
    }

    @GetMapping("/meu-usuario/{login}")
    public String meuUsuario(@PathVariable(value = "login") String login, ModelMap map){
        UsuarioDTO usuarioDTO = serviceUsuario.findByUsuario(login);
        map.addAttribute("idUsuario", usuarioDTO.getId());
        map.addAttribute("login", usuarioDTO.getLogin());
        map.addAttribute("statusUsuario", usuarioDTO.getStatus());
        map.addAttribute("contatos",usuarioDTO.getContatos());
        map.addAttribute("enderecos",usuarioDTO.getEnderecos());
        map.addAttribute("nomeCompleto", usuarioDTO.getNomeCompleto());
        map.addAttribute("roles", serviceRole.findAll());
        map.addAttribute("status", StatusUsuario.listaStatusUsuario());
        map.addAttribute("editar", "editar");
        return "pg-usuario-meu-usuario";
    }

    @GetMapping("/edita/busca/{idUsuario}")
    public String editaUsuario(@PathVariable(value = "idUsuario") Integer idUsuario, ModelMap map){
        UsuarioDTO usuarioDTO = serviceUsuario.findById(idUsuario);
        map.addAttribute("id", usuarioDTO.getId());
        map.addAttribute("login", usuarioDTO.getLogin());
        map.addAttribute("statusUsuario", usuarioDTO.getStatus());
        map.addAttribute("contatos",usuarioDTO.getContatos());
        map.addAttribute("enderecos",usuarioDTO.getEnderecos());
        map.addAttribute("nomeCompleto", usuarioDTO.getNomeCompleto());
        map.addAttribute("roles", serviceRole.findAll());
        map.addAttribute("status", StatusUsuario.listaStatusUsuario());
        map.addAttribute("editar", "editar");
        return "pg-usuario-edita";
    }

    @GetMapping("/busca-por-login/{login}")
    public ResponseEntity<?> findByLogin(@PathVariable(value = "login") String login){
        return ResponseEntity.ok(serviceUsuario.findByUsuario(login));
    }

    @PostMapping("/adicionar-usuario")
    public ResponseEntity<?> saveUsuario(String nomeCompleto, String login, String senha, String status, String telefone, String email, String cep, String logradouro, String complemento, String bairro, String localidade, String numero, String uf, String role){

        Usuario usuario = new Usuario(null, nomeCompleto, login, senha, StatusUsuario.findDescricao(status));
        serviceUsuario.save(usuario);
        Arrays.stream(role.split(",")).forEach(l->{
            if(!l.equals("")) {
                usuario.getRoles().add(serviceRole.findById(l.trim()));
            }
        });
        serviceUsuario.save(usuario);
        serviceUsuario.addContato(usuario.getId(), email, telefone);
        serviceUsuario.addEndereco(usuario.getId(), cep, numero, complemento);
        return ResponseEntity.ok(usuarioMapper.fromDtro(usuario));
    }

    @PostMapping("/adciona-contato")
    public ResponseEntity<?> adcionaContatoCliente(Integer idUsuario, String email, String telefone){
        serviceUsuario.addContato(idUsuario, email, telefone);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-contato")
    public ResponseEntity<?> removeContatoCliente(Integer idUsuario, Long idContato){
        serviceUsuario.removeContato(idUsuario, serviceContato.findById(idContato));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/adciona-endereco")
    public ResponseEntity<?> adcionarNovoEndereco(Integer idUsuario, String cep, String logradouro, String complemento, String bairro, String localidade, String numero, String uf){
        serviceUsuario.addEndereco( idUsuario,  cep,  logradouro,  complemento,  bairro,  localidade,  numero,  uf);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-endereco")
    public ResponseEntity<?> removerEndereco(Integer idUsuario, Long idEndereco){
        serviceUsuario.removeEndereco(idUsuario, serviceEndereco.findById(idEndereco));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/atualizar-nome")
    public  ResponseEntity<?> atualizarNome(Integer idUsuario, String nomeCompleto){
        if(!serviceUsuario.atualizarNome(idUsuario, nomeCompleto)){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok().build();
        }
    }

    @PutMapping("/atualizar-senha")
    public ResponseEntity<?> atalizarSenha(Integer idUsuario, String senha){
        if(!serviceUsuario.atualizarSenha(idUsuario, senha)){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok().build();
        }
    }

    @PutMapping("/atualizar-login")
    public ResponseEntity<?> atualizarLogin(Integer idUsuario, String login){
        if(!serviceUsuario.atualizarLogin(idUsuario, login)){
            return ResponseEntity.badRequest().build();
        }else {
            return ResponseEntity.ok().build();
        }
    }

    @PutMapping("/atualizar-status")
    public ResponseEntity<?> atualizarStatus(Integer idUsuario, String status){
        if(!serviceUsuario.atualizarStatus(idUsuario, status)){
            return ResponseEntity.badRequest().build();
        }else {
            return ResponseEntity.ok().build();
        }
    }

    @PutMapping("/atualizar-role")
    public ResponseEntity<?> atualizarRoler(Integer idUsuario, String roleNome){
        serviceUsuario.atualizarRoles(idUsuario, roleNome);
        return ResponseEntity.ok().build();
    }

}
