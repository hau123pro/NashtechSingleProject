package com.cozastore.repository.author;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cozastore.entity.Author;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Integer>{
	public Page<Author> findByStatus(Integer status,Pageable pageable);

	public List<Author> findByStatus(Integer status);
}
