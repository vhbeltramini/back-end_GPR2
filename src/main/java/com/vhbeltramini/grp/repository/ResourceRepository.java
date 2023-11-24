package com.vhbeltramini.grp.repository;

import com.vhbeltramini.grp.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ResourceRepository extends JpaRepository<Resource, Integer>, JpaSpecificationExecutor<Resource>  {

}