package com.vhbeltramini.grp.repository;

import com.vhbeltramini.grp.model.Project;
import com.vhbeltramini.grp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer>{

    Optional<Project> findByCoordenador(User coordenador);

}