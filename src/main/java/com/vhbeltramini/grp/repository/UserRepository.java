package com.vhbeltramini.grp.repository;

import com.vhbeltramini.grp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{

    Optional<User> findByEmail(String email);

    User findById(Long idCoordenador);
}