package com.heitor.login.mapper;

import com.heitor.login.entidades.Contato;
import com.heitor.login.entidades.DTO.UsuarioDTO;
import com.heitor.login.entidades.Endereco;
import com.heitor.login.entidades.Role;
import com.heitor.login.entidades.Usuario;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-29T23:30:37-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Amazon.com Inc.)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toModel(UsuarioDTO usuarioDTO) {
        if ( usuarioDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( usuarioDTO.getId() );
        usuario.setNomeCompleto( usuarioDTO.getNomeCompleto() );
        usuario.setLogin( usuarioDTO.getLogin() );
        usuario.setStatus( usuarioDTO.getStatus() );
        Set<Role> set = usuarioDTO.getRoles();
        if ( set != null ) {
            usuario.setRoles( new HashSet<Role>( set ) );
        }
        Set<Endereco> set1 = usuarioDTO.getEnderecos();
        if ( set1 != null ) {
            usuario.setEnderecos( new HashSet<Endereco>( set1 ) );
        }
        Set<Contato> set2 = usuarioDTO.getContatos();
        if ( set2 != null ) {
            usuario.setContatos( new HashSet<Contato>( set2 ) );
        }

        return usuario;
    }

    @Override
    public UsuarioDTO fromDtro(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setId( usuario.getId() );
        usuarioDTO.setNomeCompleto( usuario.getNomeCompleto() );
        usuarioDTO.setLogin( usuario.getLogin() );
        usuarioDTO.setStatus( usuario.getStatus() );
        Set<Role> set = usuario.getRoles();
        if ( set != null ) {
            usuarioDTO.setRoles( new HashSet<Role>( set ) );
        }
        Set<Endereco> set1 = usuario.getEnderecos();
        if ( set1 != null ) {
            usuarioDTO.setEnderecos( new HashSet<Endereco>( set1 ) );
        }
        Set<Contato> set2 = usuario.getContatos();
        if ( set2 != null ) {
            usuarioDTO.setContatos( new HashSet<Contato>( set2 ) );
        }

        return usuarioDTO;
    }
}
