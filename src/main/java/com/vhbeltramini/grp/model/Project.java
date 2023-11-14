package com.vhbeltramini.grp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue
    private Integer id;
    @Size(min=5, message="O nome do projeto deve ter pelo menos 5 caracteres")
    @NotBlank
    private String projeto;
    @OneToOne
    private User coordenador;
    private Float valorPrevisto;
    private Float valorExecutado;
    private Float valorSaldo;
    private Float valorTotal;
    private String situacao;
    private Integer exercicio;
    private String empenho;
    @OneToMany
    private List<ProjectItems> itens;

    public Project() {}

    public Project(Integer id, String projeto, User coordenador, Float valorPrevisto, Float valorExecutado, Float valorSaldo, Float valorTotal, String situacao, Integer exercicio, String empenho, List<ProjectItems> itens) {
        this.id = id;
        this.projeto = projeto;
        this.coordenador = coordenador;
        this.valorPrevisto = valorPrevisto;
        this.valorExecutado = valorExecutado;
        this.valorSaldo = valorSaldo;
        this.valorTotal = valorTotal;
        this.situacao = situacao;
        this.exercicio = exercicio;
        this.empenho = empenho;
        this.itens = itens;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getProjeto() {
        return projeto;
    }

    public void setProjeto(String nome) {
        this.projeto = nome;
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

    public Integer getExercicio() {
        return exercicio;
    }

    public void setExercicio(Integer exercicio) {
        this.exercicio = exercicio;
    }

    public String getEmpenho() {
        return empenho;
    }

    public void setEmpenho(String empenho) {
        this.empenho = empenho;
    }

    public List<ProjectItems> getItens() {
        return itens;
    }

    public void setItens(List<ProjectItems> itens) {
        this.itens = itens;
    }
}
