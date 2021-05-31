package com.heitor.login.config;

import com.heitor.login.entidades.Endereco;
import com.heitor.login.entidades.Estabelecimento;
import com.heitor.login.entidades.Role;
import com.heitor.login.entidades.Usuario;
import com.heitor.login.enums.StatusEstabelecimento;
import com.heitor.login.enums.StatusUsuario;
import com.heitor.login.services.EnderecoService;
import com.heitor.login.services.EstabelecimentoService;
import com.heitor.login.services.RoleService;
import com.heitor.login.services.UsuarioService;
import com.heitor.login.services.email.EmailService;
import com.heitor.login.services.utils.BuscaEnderecoPorCep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbService {

    @Autowired
    private UsuarioService serviceUsuario;

    @Autowired
    private RoleService serviceRole;

    @Autowired
    private EnderecoService serviceEndereco;

    @Autowired
    private EstabelecimentoService serviceEstabelecimento;

    @Autowired
    private EmailService serviceEmail;


    private final Logger LOG = LoggerFactory.getLogger(DbService.class);

    public void implementandoRolesUsuarios(){

        LOG.info("IMPLEMENTADO ROLES USUARIOS");

        Role role1 = new Role("ROLE_ADMIN", "Administrador");
        Role role2 = new Role("ROLE_GERENTE", "Gerente");
        Role role3 = new Role("ROLE_CAIXA", "Caixa");
        Role role4 = new Role("ROLE_ATENDENTE", "Atendente");

        serviceRole.save(role1);
        serviceRole.save(role2);
        serviceRole.save(role3);
        serviceRole.save(role4);

        LOG.info("ROLES USUARIOS IMPLEMENTADOS");
    }

    public void implementacaoEstabelecimento(){
        LOG.info("IMPLEMENTADO DADOS FICTCIOS ESTABELECIMENTO ");
        Endereco endEstabelecimento = BuscaEnderecoPorCep.buscarCep("06725-050");
        serviceEstabelecimento.save("Antonio Magalhões","Loja do hamburger", "77.311.306/0001-93", "392.437.380-97", endEstabelecimento.getCep(), endEstabelecimento.getLogradouro(), endEstabelecimento.getComplemento(), endEstabelecimento.getBairro(), endEstabelecimento.getLocalidade(), endEstabelecimento.getNumero(), endEstabelecimento.getUf(), "loja@gmail.com", "(11) 98936-2323", StatusEstabelecimento.PRINCIPAL.getDecricao());
        LOG.info("DADOS FICTCIOS ESTABELECIMENTO IMPLEMENTADOS");
    }

    public void implementaBancoTeste(){

        Usuario  usuario1 = new Usuario(null, "Heitor Fernando Barbosa Ramos", "heitorhfbr@gmail.com","123", StatusUsuario.ATIVO );
        Usuario  usuario2 = new Usuario(null, "Antonio Fagundes Miranda Ramos", "antonio@gmail.com","123",  StatusUsuario.ATIVO );
        Usuario  usuario3 = new Usuario(null, "Laurindo Silvano Mathias", "laurindo@gmail.com","123",  null);
        Usuario  usuario4 = new Usuario(null, "Luana Barbosa Camargo", "luana@gmail.com","123",  StatusUsuario.DESATIVADO);


        serviceUsuario.save(usuario1);
        serviceUsuario.save(usuario2);
        serviceUsuario.save(usuario3);
        serviceUsuario.save(usuario4);

        serviceRole.findAll().forEach(role ->{
            serviceUsuario.addRoles(usuario1.getId(), role.getNomeRole());
        });
;
        serviceUsuario.addEndereco(usuario1.getId(), "06725-050", "200", "Casa 4");
        serviceUsuario.addEndereco(usuario1.getId(), "06725-050", "200", "Casa 4");
        serviceUsuario.addEndereco(usuario1.getId(), "06725-050", "200", "Casa 4");

        serviceUsuario.addContato(usuario1.getId(), "heitorhfbr@gmail.com", "(11) 98946-1545");
        serviceUsuario.addContato(usuario1.getId(), "heitorhfbr@hotmail.com", "");
        LOG.info("BANCO DE TESTES IMPLEMENTADO");


//        String corpoEmail ="<h1>Olá Mundo</h1><br/><p>teste de envio de email com formatação HTML</p>";
//        serviceEmail.sendMail("heitorhfbr@gmail.com", "TituloTeste", corpoEmail);

    }



}
