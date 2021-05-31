package com.heitor.login.mapper;

import com.heitor.login.entidades.DTO.UsuarioDTO;
import com.heitor.login.entidades.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    Usuario toModel(UsuarioDTO usuarioDTO);
    UsuarioDTO fromDtro(Usuario usuario);
}
