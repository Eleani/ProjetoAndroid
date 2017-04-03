package com.example.nany_.projetoviagem.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by nany_ on 19/03/2017.
 */

public class Gasto implements Serializable {

    private Long id;
    private TipoGasto tipoGasto;
    private Double valor;
    private String data;
    private String descricao;
    private String local;

    public Gasto(TipoGasto tipoGasto, Double valor, String data, String descricao, String local) {
        this.tipoGasto = tipoGasto;
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
        this.local = local;
    }

    public Gasto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoGasto getTipoGasto() {
        return tipoGasto;
    }

    public Double getValor() {
        return valor;
    }

    public String getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setTipoGasto(TipoGasto tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setLocal(String local) {
        this.local = local;
    }

}
