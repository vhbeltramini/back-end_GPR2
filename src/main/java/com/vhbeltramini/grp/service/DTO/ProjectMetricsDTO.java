package com.vhbeltramini.grp.service.DTO;

import com.vhbeltramini.grp.model.enums.Role;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

public class ProjectMetricsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String coordenador;
    private Float valorSaldo;
    private Float valorExecutado;

    public ProjectMetricsDTO(String firstName, Float valorSaldo, Float valorExecutado) throws NoSuchAlgorithmException {
        super();
        this.coordenador = firstName;
        this.valorSaldo = valorSaldo;
        this.valorExecutado = valorExecutado;
    }

    public String getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(String coordenador) {
        this.coordenador = coordenador;
    }

    public Float getValorSaldo() {
        return valorSaldo;
    }

    public void setValorSaldo(Float valorSaldo) {
        this.valorSaldo = valorSaldo;
    }

    public Float getValorExecutado() {
        return valorExecutado;
    }

    public void setValorExecutado(Float valorExecutado) {
        this.valorExecutado = valorExecutado;
    }
}
