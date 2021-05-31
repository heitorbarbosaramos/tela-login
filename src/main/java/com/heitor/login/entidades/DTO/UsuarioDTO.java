package com.heitor.login.entidades.DTO;

import com.heitor.login.entidades.Contato;
import com.heitor.login.entidades.Endereco;
import com.heitor.login.entidades.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Data                   //para gerar os getteres e seteres
@Builder                //Builder vai nos prover uma maneira de criar objetos sem precisarmos de construtores e sem métodos setters em nossas classes
@AllArgsConstructor     //insere os construtores automaticamente
@NoArgsConstructor      //insere os construtores automaticamente
public class UsuarioDTO {


    private Integer id;

    @NotEmpty
    @Size(min = 5, max = 100, message = "Nome deve conter de 5 a 100 caracteres")
    private String nomeCompleto;
    @Email(message = "O Login deve ser um e-mail")
    private String login;
    private String status;

    private Set<Role> roles = new LinkedHashSet<>();
    private Set<Endereco> enderecos = new LinkedHashSet<>();
    private Set<Contato> contatos = new LinkedHashSet<>();
}
