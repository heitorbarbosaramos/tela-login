package com.heitor.login.repository;

import com.heitor.login.entidades.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByNomeRole(String nomeRole);
}
