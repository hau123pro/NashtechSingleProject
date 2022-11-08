package com.cozastore.service.review;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.reponse.PageResponse;
import com.cozastore.dto.reponse.ReviewPageResponse;
import com.cozastore.dto.reponse.ReviewRespone;
import com.cozastore.dto.request.ReviewRequest;
import com.cozastore.dto.request.ReviewStatusRequest;
import com.cozastore.entity.Product;
import com.cozastore.entity.Review;
import com.cozastore.entity.User;
import com.cozastore.exception.NotFoundException;
import com.cozastore.mappers.PageMapper;
import com.cozastore.mappers.ReviewMapper;
import com.cozastore.repository.product.IProductRepository;
import com.cozastore.repository.review.IReviewRepository;
import com.cozastore.repository.user.IUserRepository;
import com.cozastore.utils.constant.ErrorString;
import com.cozastore.utils.constant.Status;
import com.cozastore.utils.constant.SuccessString;

@Service
public class ReviewService implements IReviewService {

	IReviewRepository reviewRepository;

	ReviewMapper reviewMapper;

	IProductRepository productRepository;

	IUserRepository userRepository;

	PageMapper pageMapper;
	
	
	@Autowired
	public ReviewService(IReviewRepository reviewRepository, ReviewMapper reviewMapper,
			IProductRepository productRepository, IUserRepository userRepository, PageMapper pageMapper) {
		super();
		this.reviewRepository = reviewRepository;
		this.reviewMapper = reviewMapper;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
		this.pageMapper = pageMapper;
	}

	@Override
	public List<ReviewRespone> getAllReview() {
		List<Review> reviews = reviewRepository.findAll();
		return reviewMapper.convertToListReviewResponse(reviews);
	}

	@Override
	public ReviewPageResponse getReviewByPage(Pageable pageable) {
		Page<Review> reviews = reviewRepository.findAll(pageable);
		List<ReviewRespone> respones = reviewMapper.convertToListReviewResponse(reviews.getContent());
		PageResponse pageResponse = pageMapper.convertPagetoPageResponse(reviews, pageable.getPageNumber(),
				pageable.getPageSize());
		return ReviewPageResponse.builder().reviewRespones(respones).pageResponse(pageResponse).build();
	}

	@Override
	public ReviewPageResponse getReviewActiveByPage(Pageable pageable) {
		Page<Review> reviews = reviewRepository.findByStatus(Status.ACTIVE.getValue(), pageable);
		List<ReviewRespone> respones = reviewMapper.convertToListReviewResponse(reviews.getContent());
		PageResponse pageResponse = pageMapper.convertPagetoPageResponse(reviews, pageable.getPageNumber(),
				pageable.getPageSize());
		return ReviewPageResponse.builder().reviewRespones(respones).pageResponse(pageResponse).build();
	}

	@Override
	public String addReviewToProduct(ReviewRequest reviewRequest, String email) {
		Product product = productRepository.findById(reviewRequest.getProductId())
				.orElseThrow(() -> new NotFoundException(ErrorString.PRODUCT_NOT_FOUND));
		User user = userRepository.findUserByEmail(email)
				.orElseThrow(() -> new NotFoundException(ErrorString.USER_NOT_FOUND));

		Date date = Date.valueOf(LocalDate.now());
		Review review = reviewMapper.convertRequestToReview(reviewRequest);
		review.setProduct(product);
		review.setUser(user);
		review.setDateCreate(date);
		review.setStatus(Status.ACTIVE.getValue());
		List<Review> reviews = product.getListReview();
		reviews.add(review);
		product.setListReview(reviews);
		int countRating = 0;
		int i = 0;
		for (Review item : product.getListReview()) {
			countRating += item.getRating();
			i += 1;
		}
		if (i != 0)
			product.setAverageRating(countRating / i);
		else
			product.setAverageRating(countRating);
		reviewRepository.save(review);
		productRepository.save(product);
		return SuccessString.REVIEW_ADD_SUCCESS;
	}

	@Override
	public String changeStatusReview(ReviewStatusRequest request) {
		Review review = reviewRepository.findById(request.getId())
				.orElseThrow(() -> new NotFoundException(ErrorString.REVIEW_NOT_FOUND));
		review.setStatus(request.getStatus().getValue());
		reviewRepository.save(review);
		return SuccessString.REVIEW_UPDATE_STATUS_SUCCESS;
	}

}
