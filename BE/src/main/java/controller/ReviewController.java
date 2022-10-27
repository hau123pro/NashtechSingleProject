package controller;

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

import dto.reponse.FormatRespone;
import dto.reponse.HeaderResponse;
import dto.reponse.ReviewRespone;
import dto.request.CategoryStatusRequest;
import dto.request.ReviewStatusRequest;
import service.format.FormatService;
import service.review.IReviewService;
import service.review.ReviewService;

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
