package com.venkatesh.reposiotry;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.venkatesh.entity.CityMasterEntity;

public interface CityMasterRepo extends JpaRepository<CityMasterEntity, Serializable> {

	
	public List<CityMasterEntity> findByStateId(Integer STATE_ID);
}
