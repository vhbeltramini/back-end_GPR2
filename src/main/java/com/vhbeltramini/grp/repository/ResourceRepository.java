package com.vhbeltramini.grp.repository;

import com.vhbeltramini.grp.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResourceRepository extends JpaRepository<Resource, Integer>{


}