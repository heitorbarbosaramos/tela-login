package com.heitor.login.services;

import com.heitor.login.entidades.Role;
import com.heitor.login.exceptions.RoleNaoEncontrado;
import com.heitor.login.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private RoleRepository repo;

    @Autowired
    public RoleService(RoleRepository roleRepository){
        this.repo = roleRepository;
    }

    public Role save(Role role){
        return repo.save(role);
    }

    public List<Role> findAll(){
        return repo.findAll();
    }

    public Role findById(String nomeRole){
        return repo.findById(nomeRole).orElseThrow(() -> new RoleNaoEncontrado(nomeRole));
    }

}
