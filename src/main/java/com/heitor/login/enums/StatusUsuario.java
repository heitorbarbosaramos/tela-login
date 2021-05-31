package com.heitor.login.enums;

import java.util.ArrayList;
import java.util.List;

public enum StatusUsuario {

    ATIVO (1, "Ativo"),
    DESATIVADO(2, "Desativado"),
    SEM_STATUS(3, "Sem status");

    private Integer id;
    private String descricao;


    StatusUsuario(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static List<StatusUsuario> listaStatusUsuario(){
        List<StatusUsuario> lista = new ArrayList<>();
        for(StatusUsuario x : StatusUsuario.values()){
            lista.add(x);
        }
        return lista;
    }

    public static StatusUsuario getId(Integer idStatusUsuario){
        for(StatusUsuario x : StatusUsuario.values()){
            if(idStatusUsuario == x.getId()){
                return x;
            }
        }
        throw new RuntimeException("STATUS NÃO ENCONTRADO, ID: " + idStatusUsuario);
    }

    public static StatusUsuario findDescricao(String descricao){
        for(StatusUsuario x : StatusUsuario.values()){
            if(descricao.equals(x.getDescricao())){
                return x;
            }
        }
        throw new RuntimeException("STATUS NÃO ENCONTRADO, DESCRICAO: " + descricao);
    }

}
