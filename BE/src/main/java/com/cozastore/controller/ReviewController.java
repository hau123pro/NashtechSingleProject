package com.cozastore.controller;

import java.security.Principal;
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

import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.reponse.ReviewPageResponse;
import com.cozastore.dto.reponse.ReviewRespone;
import com.cozastore.dto.request.ReviewRequest;
import com.cozastore.service.review.IReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/review")
public class ReviewController {
	
	@Autowired
	private IReviewService reviewService;

	@GetMapping("/")
	public ResponseEntity<ReviewPageResponse> getReviewByPage(Pageable pageable) {
		return ResponseEntity.ok(reviewService.getReviewActiveByPage(pageable));
	}
	@PostMapping("/add")
	public ResponseEntity<String> addReviewProduct(@Valid @RequestBody ReviewRequest reviewRequest,
			Principal principal) {
		return ResponseEntity.ok(reviewService.addReviewToProduct(reviewRequest, principal.getName()));
	}
}
