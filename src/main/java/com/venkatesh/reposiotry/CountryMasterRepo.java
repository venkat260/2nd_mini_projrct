package com.venkatesh.reposiotry;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.venkatesh.entity.CountryMasterEntity;

public interface CountryMasterRepo extends JpaRepository<CountryMasterEntity, Serializable> {

	
}
