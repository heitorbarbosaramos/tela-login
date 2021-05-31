package com.heitor.login.entidades;

import com.heitor.login.enums.StatusUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.*;


@Entity
@Data                   //para gerar os getteres e seteres
@Builder                //Builder vai nos prover uma maneira de criar objetos sem precisarmos de construtores e sem métodos setters em nossas classes
@AllArgsConstructor     //insere os construtores automaticamente
@NoArgsConstructor      //insere os construtores automaticamente
public class Usuario implements UserDetails , Comparable<Usuario>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomeCompleto;
    @Column(unique = true)
    @Email(message = "O Login deve ser um email")
    private String login;
    @NotBlank
    private String senha;
    private String status;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "usuariosRoles", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"),
            inverseJoinColumns =  @JoinColumn(name = "id_role", referencedColumnName = "nomeRole"))
    private Set<Role> roles = new LinkedHashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuariosEndereco", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"),
            inverseJoinColumns =  @JoinColumn(name = "id_endereco", referencedColumnName = "id"))
    private  Set<Endereco> enderecos = new LinkedHashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarioContatos", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_contato", referencedColumnName = "id"))
    private Set<Contato> contatos = new LinkedHashSet<>();

    public Usuario(Integer id, String nomeCompleto, String login, String senha, StatusUsuario status) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.login = login;
        this.senha = new BCryptPasswordEncoder().encode(senha);
        this.status = (status != null) ? status.getDescricao() : StatusUsuario.SEM_STATUS.getDescricao();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public void setSenha(String senha) {
        this.senha = new BCryptPasswordEncoder().encode(senha);
    }

    @Override
    public String getPassword() {
        return senha;
    }


    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public int compareTo(@NotNull Usuario outro) {
        return nomeCompleto.compareTo(outro.getNomeCompleto());
    }

}
