package com.heitor.login.entidades;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data                   //para gerar os getteres e seteres
public class Role implements GrantedAuthority, Serializable {

    @Id
    private String nomeRole;
    private String descricao;
//
//    @ManyToMany(mappedBy = "roles")
//    private List<Usuario> usuarios = new ArrayList<>();

    public Role(){
    }

    public Role(String nomeRole, String descricao) {
        this.nomeRole = nomeRole;
        this.descricao = descricao;
    }

    @Override
    public String getAuthority() {
        return nomeRole;
    }
}
