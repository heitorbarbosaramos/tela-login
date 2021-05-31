package com.heitor.login.config;

import com.heitor.login.entidades.DTO.UsuarioDTO;
import com.heitor.login.entidades.Usuario;
import com.heitor.login.repository.UsuarioRepository;
import com.heitor.login.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class ImplementsUser implements UserDetailsService {

    @Autowired
    private UsuarioRepository repo;


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Usuario usuario = repo.findByStatusAtivo(login);

        if(usuario == null){ throw  new UsernameNotFoundException("Usuario não encontrado");}

        return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
    }
}
