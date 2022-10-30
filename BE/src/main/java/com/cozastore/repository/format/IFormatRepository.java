package com.cozastore.repository.format;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cozastore.entity.Category;
import com.cozastore.entity.Format;
@Repository
public interface IFormatRepository extends JpaRepository<Format, Integer>{
	public Page<Format> findByStatus(Integer status,Pageable pageable);
	
	public List<Format> findByStatus(Integer status);

}
