package com.heitor.login.repository;

import com.heitor.login.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByLogin(String login);

    @Query("select u from Usuario u where u.status='Ativo' and u.login=:login")
    Usuario findByStatusAtivo(@PathVariable(value = "login") String login);
}
