package com.vhbeltramini.grp.repository;

import com.vhbeltramini.grp.model.ProjectItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectItemsRepository extends JpaRepository<ProjectItems, Integer>{

}