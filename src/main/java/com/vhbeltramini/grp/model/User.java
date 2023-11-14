package com.vhbeltramini.grp.model;

import com.vhbeltramini.grp.model.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;

@Entity
public class User {

    private static final Logger logger = LoggerFactory.getLogger(User.class);

    @Id
    @GeneratedValue
    private Integer id;
    @Size(min=5, message="O primeiro nome deve ter pelo menos 3 caracteres")
    @NotBlank
    private String firstName;

    @Size(min=3, message="O ultimo nome deve ter pelo menos 3 caracteres")
    private String lastName;

    @Size(min=11, message="O cpf deve ter pelo menos 11 caracteres")
    private String cpf;

    @Size(min=3, message="O email deve ter pelo menos 3 caracteres")
    private String email;

    private String address;

    private String passwordHash;

    @Transient
    private String password;

    @NotBlank
    private String role;

    public User() {}

    public User(String firstName, String lastName, String cpf, String email, String password, Role role) throws NoSuchAlgorithmException {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.email = email;
        this.role = role.toString();
        setPasswordHash(password);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String nome) {
        this.firstName = nome;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role.toString();
    }
}