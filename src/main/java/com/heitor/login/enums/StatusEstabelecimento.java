package com.heitor.login.enums;

import java.util.ArrayList;
import java.util.List;

public enum StatusEstabelecimento {

    PRINCIPAL(1, "Principal"),
    FRANGUIA(2, "Franguia"),
    ASSOCIADA(3, "Associada"),
    DESATIVADA(4, "Desativada");

    private int cod;
    private String decricao;


    StatusEstabelecimento(int cod, String descricao) {
        this.cod = cod;
        this.decricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDecricao() {
        return decricao;
    }

    public static List<StatusEstabelecimento> findAll(){
        List<StatusEstabelecimento> lista = new ArrayList<>();
        for(StatusEstabelecimento x : StatusEstabelecimento.values()){
            lista.add(x);
        }
        return lista;
    }

    public static StatusEstabelecimento findByCod(int cod){
        for(StatusEstabelecimento x : StatusEstabelecimento.values()){
            if(x.getCod() == cod){
                return x;
            }
        }
        throw new RuntimeException("Codigo Status não encontrado: " + cod);
    }

    public static StatusEstabelecimento findByDescricao(String descricao){
        for(StatusEstabelecimento x : StatusEstabelecimento.values()){
            if(x.getDecricao().toUpperCase().equals(descricao.toUpperCase())){
                return x;
            }
        }
        throw new RuntimeException("Descrição Status não encontrado: " + descricao);
    }
}
