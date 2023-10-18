package com.vhbeltramini.grp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue
    private Integer id;
    @Size(min=5, message="O nome deve ter pelo menos 5 caracteres")
    @NotBlank
    private String nome;
    @OneToOne
    private User coordenador;
    private Float valorPrevisto;
    private Float valorExecutado;
    private Float valorSaldo;
    private Float valorTotal;
    private String situacao;
    private Date anoExercicio;
    private Date anoEmpenho;
    @OneToMany
    private List<ProjectItems> itens;

    public Project() {}

    public Project(Integer id, String nome, User coordenador, Float valorPrevisto, Float valorExecutado, Float valorSaldo, Float valorTotal, String situacao, Date anoExercicio, Date anoEmpenho, List<ProjectItems> itens) {
        this.id = id;
        this.nome = nome;
        this.coordenador = coordenador;
        this.valorPrevisto = valorPrevisto;
        this.valorExecutado = valorExecutado;
        this.valorSaldo = valorSaldo;
        this.valorTotal = valorTotal;
        this.situacao = situacao;
        this.anoExercicio = anoExercicio;
        this.anoEmpenho = anoEmpenho;
        this.itens = itens;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public User getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(User coordenador) {
        this.coordenador = coordenador;
    }

    public Float getValorPrevisto() {
        return valorPrevisto;
    }

    public void setValorPrevisto(Float valorPrevisto) {
        this.valorPrevisto = valorPrevisto;
    }

    public Float getValorExecutado() {
        return valorExecutado;
    }

    public void setValorExecutado(Float valorExecutado) {
        this.valorExecutado = valorExecutado;
    }

    public Float getValorSaldo() {
        return valorSaldo;
    }

    public void setValorSaldo(Float valorSaldo) {
        this.valorSaldo = valorSaldo;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Date getAnoExercicio() {
        return anoExercicio;
    }

    public void setAnoExercicio(Date anoExercicio) {
        this.anoExercicio = anoExercicio;
    }

    public Date getAnoEmpenho() {
        return anoEmpenho;
    }

    public void setAnoEmpenho(Date anoEmpenho) {
        this.anoEmpenho = anoEmpenho;
    }

    public List<ProjectItems> getItens() {
        return itens;
    }

    public void setItens(List<ProjectItems> itens) {
        this.itens = itens;
    }
}
