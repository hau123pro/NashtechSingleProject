package com.cozastore.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.reponse.ReviewPageResponse;
import com.cozastore.dto.reponse.ReviewRespone;
import com.cozastore.dto.request.ReviewStatusRequest;
import com.cozastore.service.review.IReviewService;

@RestController
@RequestMapping("/v1/admin/review")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class ReviewAdminController {

	@Autowired
	IReviewService reviewService;

	@GetMapping
	public ResponseEntity<ReviewPageResponse> getReviewByPage(Pageable pageable) {
		return ResponseEntity.ok(reviewService.getReviewByPage(pageable));
	}

	@PostMapping("/update/status")
	public ResponseEntity<String> updateReviewStatus(@Valid @RequestBody ReviewStatusRequest statusRequest) {
		return ResponseEntity.ok(reviewService.changeStatusReview(statusRequest));
	}

}
