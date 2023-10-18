package com.vhbeltramini.grp.service.DTO;

import com.vhbeltramini.grp.model.enums.Role;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String cpf;
    private String email;
    private String role;

    public UserDTO(String firstName, String lastName, String cpf, String email, Role role) throws NoSuchAlgorithmException {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.email = email;
        this.role = role.toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
