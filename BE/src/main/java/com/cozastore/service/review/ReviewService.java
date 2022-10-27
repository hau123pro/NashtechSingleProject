package com.cozastore.service.review;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.cozastore.dto.reponse.FormatRespone;
import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.reponse.ReviewRespone;
import com.cozastore.dto.request.ReviewRequest;
import com.cozastore.dto.request.ReviewStatusRequest;
import com.cozastore.entity.Product;
import com.cozastore.entity.Review;
import com.cozastore.entity.User;
import com.cozastore.exception.BadRequestException;
import com.cozastore.exception.NotFoundException;
import com.cozastore.mappers.ReviewMapper;
import com.cozastore.mappers.UtilMapper;
import com.cozastore.repository.product.IProductRepository;
import com.cozastore.repository.review.IReviewRepository;
import com.cozastore.repository.user.IUserRepository;
import com.cozastore.utils.constant.ErrorString;
import com.cozastore.utils.constant.Status;
import com.cozastore.utils.constant.SuccessString;

@Service
public class ReviewService implements IReviewService{
	
	@Autowired
	IReviewRepository reviewRepository;
	
	@Autowired
	ReviewMapper reviewMapper;
	
	@Autowired
	IProductRepository productRepository;
	
	@Autowired
	IUserRepository userRepository;

	@Override
	public List<ReviewRespone> getAllReview() {
		List<Review> reviews=reviewRepository.findAll();
		return reviewMapper.convertToListReviewResponse(reviews);
	}

	@Override
	public HeaderResponse<ReviewRespone> getReviewByPage(Pageable pageable) {
		Page<Review> reviews=reviewRepository.findAll(pageable);
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("page-total-count", String.valueOf(reviews.getTotalPages()));
        responseHeaders.add("page-total-elements", String.valueOf(reviews.getTotalElements()));
        List<ReviewRespone> respones=reviewMapper.convertToListReviewResponse(reviews.getContent());
		HeaderResponse<ReviewRespone> headerResponse=new HeaderResponse<>(respones, responseHeaders);
		return headerResponse;
	}
	@Override
	public HeaderResponse<ReviewRespone> getReviewActiveyPage(Pageable pageable) {
		Page<Review> reviews=reviewRepository.findByStatus(Status.ACTIVE.getValue(), pageable);
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("page-total-count", String.valueOf(reviews.getTotalPages()));
        responseHeaders.add("page-total-elements", String.valueOf(reviews.getTotalElements()));
        List<ReviewRespone> respones=reviewMapper.convertToListReviewResponse(reviews.getContent());
		HeaderResponse<ReviewRespone> headerResponse=new HeaderResponse<>(respones, responseHeaders);
		return headerResponse;
	}

	@Override
	public String addReviewToProduct(ReviewRequest reviewRequest,String email) {
		Product product=productRepository.findById(reviewRequest.getProductId())
				.orElseThrow(()->new NotFoundException(ErrorString.PRODUCT_NOT_FOUND));
		User user=userRepository.findUserByEmail(email).orElseThrow(()->new NotFoundException(ErrorString.USER_NOT_FOUND));
		Date date = Date.valueOf(LocalDate.now());
		Review review=reviewMapper.convertRequestToReview(reviewRequest);
		review.setProduct(product);
		review.setUser(user);
		review.setDateCreate(date);
		review.setStatus(Status.ACTIVE.getValue());
		reviewRepository.save(review);
		return SuccessString.REVIEW_ADD_SUCCESS;
	}

	@Override
	public String changeStatusReview(ReviewStatusRequest request) {
		Review review=reviewRepository.findById(request.getId())
				.orElseThrow(()->new NotFoundException(ErrorString.REVIEW_NOT_FOUND));
		review.setStatus(request.getStatus().getValue());
		reviewRepository.save(review);
		return SuccessString.REVIEW_UPDATE_STATUS_SUCCESS;
	}

	
	
	
	
	
}
