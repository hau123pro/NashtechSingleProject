package com.cozastore.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cozastore.dto.reponse.ReviewRespone;
import com.cozastore.dto.request.ReviewRequest;
import com.cozastore.entity.Review;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewMapper {
	@Autowired
	private UtilMapper utilMapper;

	public List<ReviewRespone> convertToListReviewResponse(List<Review> list) {
		List<ReviewRespone> listRespone = new ArrayList<>();
		for (Review review : list) {
			ReviewRespone reviewRespone = ReviewRespone.builder().userName(review.getUser().getName())
					.content(review.getContent()).rating(review.getRating()).dateCreate(review.getDateCreate()).build();
			listRespone.add(reviewRespone);
		}
		return listRespone;
	}

	public Review convertRequestToReview(ReviewRequest request) {
		return utilMapper.convertToEntity(request, Review.class);
	}
}
