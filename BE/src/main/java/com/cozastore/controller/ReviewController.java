package com.cozastore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozastore.dto.reponse.FormatRespone;
import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.reponse.ReviewRespone;
import com.cozastore.dto.request.CategoryStatusRequest;
import com.cozastore.dto.request.ReviewStatusRequest;
import com.cozastore.service.format.FormatService;
import com.cozastore.service.review.IReviewService;
import com.cozastore.service.review.ReviewService;

@RestController
@RequestMapping("/v1/admin/review")
public class ReviewController {
	
	@Autowired
	IReviewService reviewService;
	
	@GetMapping
	public ResponseEntity<List<ReviewRespone>> getReviewByPage(Pageable pageable){
		HeaderResponse<ReviewRespone> headerResponse=reviewService.getReviewByPage(pageable);
		return ResponseEntity.ok().headers(headerResponse.getHeaders()).body(headerResponse.getItems());
	}
	
	@PostMapping("/update/status")
	public ResponseEntity<String> updateReviewStatus(@Valid @RequestBody ReviewStatusRequest statusRequest){
		return ResponseEntity.ok(reviewService.changeStatusReview(statusRequest));
	}
	
	
}
