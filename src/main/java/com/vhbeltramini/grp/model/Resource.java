package com.vhbeltramini.grp.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.awt.image.BufferedImage;

@Entity
public class Resource {

    @Id
    @GeneratedValue
    private Integer id;
    @Size(min=5, message="O nome deve ter pelo menos 5 caracteres")
    @NotBlank
    private String nome;
    @Size(min=20, message="A descrição deve ter pelo menos 20 caracteres")
    @NotBlank
    private String descricao;
    private String especificoes;
    @Nullable
    private int quantidade;
    @NotBlank
    private Float valor;

    public Resource() {}

    public Resource(String nome, String descricao, String especificoes, Integer quantidade, Float valor) {
        super();
        this.nome = nome;
        this.descricao = descricao;
        this.especificoes = especificoes;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEspecificoes() {
        return especificoes;
    }

    public void setEspecificoes(String especificoes) {
        this.especificoes = especificoes;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
