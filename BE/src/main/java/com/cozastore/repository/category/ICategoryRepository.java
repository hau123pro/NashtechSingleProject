package com.cozastore.repository.category;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cozastore.entity.Category;

public interface ICategoryRepository extends JpaRepository<Category, Integer>{
	
	public Page<Category> findByStatus(Integer status,Pageable pageable);
	
	public List<Category> findByStatus(Integer status);
}
