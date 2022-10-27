package com.cozastore.repository.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cozastore.entity.Format;
import com.cozastore.entity.Review;

public interface IReviewRepository extends JpaRepository<Review, Integer>{
	public Page<Review> findByStatus(Integer status,Pageable pageable);

}
