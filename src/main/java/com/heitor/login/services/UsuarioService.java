package com.heitor.login.services;

import com.heitor.login.entidades.Contato;
import com.heitor.login.entidades.DTO.UsuarioDTO;
import com.heitor.login.entidades.Endereco;
import com.heitor.login.entidades.Role;
import com.heitor.login.entidades.Usuario;
import com.heitor.login.exceptions.UsuarioNaoEncontrado;
import com.heitor.login.mapper.UsuarioMapper;
import com.heitor.login.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioService {

    private UsuarioRepository repo;

    private RoleService rolesSerice;

    private EnderecoService enderecoService;

    private ContatoService contatoService;

    private UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;

    private final Logger LOG = LoggerFactory.getLogger(UsuarioService.class);



    public Usuario save(Usuario usuario) {
        try{
            repo.save(usuario);
        }catch (RuntimeException e){
            LOG.error(e.getMessage());
            return null;
        }
        return usuario;
    }

    public List<UsuarioDTO> findAll(){
        return repo.findAll().stream().map(usuarioMapper::fromDtro).collect(Collectors.toList());
    }

    public UsuarioDTO findById(Integer id){
        return usuarioMapper.fromDtro(repo.findById(id).orElseThrow(() -> new UsuarioNaoEncontrado(id)));
    }

    public UsuarioDTO findByUsuario(String login){
        Usuario usuario = repo.findByLogin(login);
        if(usuario == null){throw new UsuarioNaoEncontrado(null);}
        return usuarioMapper.fromDtro(usuario);
    }

    public UsuarioDTO findByStatusAtivo(String login){

        return usuarioMapper.fromDtro(repo.findByStatusAtivo(login));
    }

    public void delete(Usuario usuario){
        repo.delete(usuario);
    }

    public UsuarioDTO addRoles(Integer idUsuario, String nomeRole ){
        Usuario usuario = findByIdUsoInterno(idUsuario);
        Role role = rolesSerice.findById(nomeRole);
        usuario.getRoles().add(role);
        save(usuario);
        UsuarioDTO usuarioDTO = usuarioMapper.fromDtro(usuario);
        return usuarioDTO;
    }

    public UsuarioDTO removeRoles(Integer idUsuario, String nomeRole ){
        Usuario usuario = findByIdUsoInterno(idUsuario);
        Role role = rolesSerice.findById(nomeRole);
        usuario.getRoles().remove(role);
        save(usuario);
        return usuarioMapper.fromDtro(usuario);
    }

    public UsuarioDTO addEndereco(Integer idUsuario, String cep, String numero, String complemento){
        Usuario usuario = findByIdUsoInterno(idUsuario);
        Endereco endereco = enderecoService.save(cep, numero, complemento);
        usuario.getEnderecos().add(endereco);
        repo.save(usuario);
        return usuarioMapper.fromDtro(usuario);
    }

    public UsuarioDTO addEndereco(Integer idUsuario, String cep, String logradouro, String complemento, String bairro, String localidade, String numero, String uf){
        Usuario usuario = findByIdUsoInterno(idUsuario);
        Endereco endereco = enderecoService.save(cep, numero, complemento);
        endereco.setCep(cep);
        endereco.setLogradouro(logradouro);
        endereco.setComplemento(complemento);
        endereco.setBairro(bairro);
        endereco.setLocalidade(localidade);
        endereco.setNumero(numero);
        endereco.setUf(uf);
        usuario.getEnderecos().add(endereco);
        repo.save(usuario);
        return usuarioMapper.fromDtro(usuario);
    }

    public UsuarioDTO removeEndereco(Integer idUsuario, Endereco endereco){
        Usuario usuario = findByIdUsoInterno(idUsuario);
        usuario.getEnderecos().remove(endereco);
        save(usuario);
        enderecoService.remove(endereco);
        return usuarioMapper.fromDtro(usuario);
    }

    public UsuarioDTO addContato(Integer idUsuario, String email, String telefone){
        Contato contato = new Contato(null, email, telefone);
        contatoService.save(contato);
        Usuario usuario = findByIdUsoInterno(idUsuario);
        usuario.getContatos().add(contato);
        save(usuario);
        return usuarioMapper.fromDtro(usuario);
    }

    public UsuarioDTO removeContato(Integer idUsuario, Contato contato){
        Usuario usuario = findByIdUsoInterno(idUsuario);
        usuario.getContatos().remove(contato);
        save(usuario);
        contatoService.delete(contato);
        return usuarioMapper.fromDtro(usuario);
    }

    public Boolean atualizarSenha(Integer idusuario, String senha){
        if(senha.equals("")){return false;}
        Usuario usuario = findByIdUsoInterno(idusuario);
        usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
        save(usuario);
        return true;
    }

    public Boolean atualizarNome(Integer idusuario,String nomeCompleto){
        if(nomeCompleto.equals("")){return false;}
        Usuario usuario = findByIdUsoInterno(idusuario);
        usuario.setNomeCompleto(nomeCompleto);
        save(usuario);
        return true;
    }

    public Boolean atualizarLogin(Integer idUsuario, String login){
        if(login.equals("")){return false;}
        Usuario usuario = findByIdUsoInterno(idUsuario);
        usuario.setLogin(login);
        if(save(usuario) != null){
            return true;
        }else {
            return false;
        }
    }

    public Boolean atualizarStatus(Integer idUsuario, String status){
        if(status.equals("")){return false;}
        Usuario usuario = findByIdUsoInterno(idUsuario);
        usuario.setStatus(status);
        if(save(usuario) != null){
            return true;
        }else{
            return false;
        }

    }

    public Boolean atualizarRoles(Integer idUsuario, String roleNome){
        if(roleNome.equals("")){return false;}
        Usuario usuario = findByIdUsoInterno(idUsuario);
        List<Role> roles = usuario.getRoles().stream().filter(l -> l.getNomeRole().equals(roleNome)).collect(Collectors.toList());
        System.out.println(roles.toString());
        if(!roles.isEmpty()){
            System.out.println(roleNome);
            System.out.println("removendo-> " + rolesSerice.findById(roleNome));
            usuario.getRoles().remove(rolesSerice.findById(roleNome));
        }else{
            System.out.println("adcionando-> " + rolesSerice.findById(roleNome));
            usuario.getRoles().add(rolesSerice.findById(roleNome));
        }
        save(usuario);
        return true;
    }

    private Usuario findByIdUsoInterno(Integer idUsuario){
        return repo.findById(idUsuario).orElseThrow(() -> new UsuarioNaoEncontrado(idUsuario));
    }
}
