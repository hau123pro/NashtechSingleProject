package com.cozastore.service.review;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.reponse.ReviewPageResponse;
import com.cozastore.dto.reponse.ReviewRespone;
import com.cozastore.dto.request.ReviewRequest;
import com.cozastore.dto.request.ReviewStatusRequest;

public interface IReviewService {
	public List<ReviewRespone> getAllReview();

	public ReviewPageResponse getReviewByPage(Pageable pageable);

	public ReviewPageResponse getReviewActiveByPage(Pageable pageable);

	public String addReviewToProduct(ReviewRequest reviewRequest, String email);

	public String changeStatusReview(ReviewStatusRequest request);
}
