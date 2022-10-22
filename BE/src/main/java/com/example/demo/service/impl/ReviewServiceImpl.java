package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.reponse.ReviewRespone;
import com.example.demo.entity.Review;
import com.example.demo.mappers.ReviewMapper;
import com.example.demo.mappers.UtilMapper;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	ReviewMapper reviewMapper;

	@Override
	public List<ReviewRespone> getAllReview() {
		// TODO Auto-generated method stub
		List<Review> reviews=reviewRepository.findAll();
		return reviewMapper.getAllReview(reviews);
	}

}
