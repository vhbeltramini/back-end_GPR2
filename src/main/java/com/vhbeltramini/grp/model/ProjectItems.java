package com.vhbeltramini.grp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class ProjectItems {

    @Id
    @GeneratedValue
    private Integer id;
    @NotBlank
    private Integer projectId;
    @Size(min=5, message="O nome deve ter pelo menos 5 caracteres")
    @NotBlank
    private String nome;
    @NotBlank
    private Float valorPrevisto;
    private Float valorExecutado;
    private Float valorSaldo;

    public ProjectItems() {}

    public ProjectItems(Integer id, Integer projectId, String nome, Float valorPrevisto, Float valorExecutado, Float valorSaldo) {
        this.id = id;
        this.projectId = projectId;
        this.nome = nome;
        this.valorPrevisto = valorPrevisto;
        this.valorExecutado = valorExecutado;
        this.valorSaldo = valorSaldo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
}
