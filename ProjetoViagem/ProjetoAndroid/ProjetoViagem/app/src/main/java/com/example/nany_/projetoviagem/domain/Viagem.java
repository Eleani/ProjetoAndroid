package com.example.nany_.projetoviagem.domain;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by nany_ on 19/03/2017.
 */

public class Viagem implements Serializable{

    private Long id;
    private String destino;
    private int tipoViagem;
    private String dataChegada;
    private String dataPartida;
    private Double valorOrcamento;
    private int qtdePessoa;
    private List<Gasto> gastos;
    private String foto;

    public Viagem() {
    }

    public Viagem(String destino) {
        this.destino = destino;
    }

    public Viagem(String destino, int tipoViagem, String dataChegada, String dataPartida, Double valorOrcamento, int qtdePessoa) {
        this.destino = destino;
        this.tipoViagem = tipoViagem;
        this.dataChegada = dataChegada;
        this.dataPartida = dataPartida;
        this.valorOrcamento = valorOrcamento;
        this.qtdePessoa = qtdePessoa;
    }

    public Viagem(String destino, int tipoViagem, String dataChegada, String dataPartida, Double valorOrcamento, int qtdePessoa, List<Gasto> gastos) {
        this.destino = destino;
        this.tipoViagem = tipoViagem;
        this.dataChegada = dataChegada;
        this.dataPartida = dataPartida;
        this.valorOrcamento = valorOrcamento;
        this.qtdePessoa = qtdePessoa;
        this.gastos = gastos;
    }

    public Viagem(String destino, String dataChegada, String dataPartida, Double valorOrcamento, int qtdePessoa) {
        this.destino = destino;
        this.dataChegada = dataChegada;
        this.dataPartida = dataPartida;
        this.valorOrcamento = valorOrcamento;
        this.qtdePessoa = qtdePessoa;
    }

    public Viagem(String destino, Double valorOrcamento, int qtdePessoa) {
        this.destino = destino;
        this.valorOrcamento = valorOrcamento;
        this.qtdePessoa = qtdePessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setTipoViagem(int tipoViagem) {
        this.tipoViagem = tipoViagem;
    }

    public void setDataChegada(String dataChegada) {
        this.dataChegada = dataChegada;
    }

    public void setDataPartida(String dataPartida) {
        this.dataPartida = dataPartida;
    }

    public void setValorOrcamento(Double valorOrcamento) {
        this.valorOrcamento = valorOrcamento;
    }

    public void setQtdePessoa(int qtdePessoa) {
        this.qtdePessoa = qtdePessoa;
    }

    public String getDestino() {
        return destino;
    }

    public int getTipoViagem() {
        return tipoViagem;
    }

    public String getDataChegada() {
        return dataChegada;
    }

    public String getDataPartida() {
        return dataPartida;
    }

    public Double getValorOrcamento() {
        return valorOrcamento;
    }

    public int getQtdePessoa() {
        return qtdePessoa;
    }

    public List<Gasto> getGastos() {
        return gastos;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Viagem{" +
                "id=" + id +
                ", destino='" + destino + '\'' +
                ", tipoViagem=" + tipoViagem +
                ", dataChegada='" + dataChegada + '\'' +
                ", dataPartida='" + dataPartida + '\'' +
                ", valorOrcamento=" + valorOrcamento +
                ", qtdePessoa=" + qtdePessoa +
                ", gastos=" + gastos +
                ", foto='" + foto + '\'' +
                '}';
    }
}
