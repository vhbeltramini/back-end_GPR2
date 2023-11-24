package com.vhbeltramini.grp.model;

import com.vhbeltramini.grp.model.enums.ProjectStatus;
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
    private String observacao;
    private Integer exercicio;
    private Integer anoInicioEmpenho;
    private Integer anoFimEmpenho;
    @OneToMany
    private List<ProjectItems> itens;
    @OneToMany
    private List<Resource> resources;

    public Project() {}

    public Project(String projeto, User coordenador, Float valorPrevisto, Float valorExecutado, Float valorSaldo, Float valorTotal, ProjectStatus situacao, Integer exercicio, Integer anoInicioEmpenho, Integer anoFimEmpenho, String observacao, List<ProjectItems> itens, List<Resource> resources) {
        super();
        this.projeto = projeto;
        this.coordenador = coordenador;
        this.valorPrevisto = valorPrevisto;
        this.valorExecutado = valorExecutado;
        this.valorSaldo = valorSaldo;
        this.valorTotal = valorTotal;
        this.situacao = situacao.toString();
        this.exercicio = exercicio;
        this.anoInicioEmpenho = anoInicioEmpenho;
        this.anoFimEmpenho = anoFimEmpenho;
        this.observacao = observacao;
        this.itens = itens;
        this.resources = resources;
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

    public Integer getAnoInicioEmpenho() {
        return anoInicioEmpenho;
    }

    public void setAnoInicioEmpenho(Integer empenho) {
        this.anoInicioEmpenho = empenho;
    }

    public Integer getAnoFimEmpenho() {
        return anoFimEmpenho;
    }

    public void setAnoFimEmpenho(Integer anoFimEmpenho) {
        this.anoFimEmpenho = anoFimEmpenho;
    }

    public List<ProjectItems> getItens() {
        return itens;
    }

    public void setItens(List<ProjectItems> itens) {
        this.itens = itens;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> recursos) {
        this.resources = recursos;
    }
}
