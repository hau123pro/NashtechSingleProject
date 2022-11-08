package com.cozastore.service.review;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.cozastore.dto.reponse.CategoryPageResponse;
import com.cozastore.dto.reponse.ReviewPageResponse;
import com.cozastore.dto.reponse.ReviewRespone;
import com.cozastore.dto.request.CategoryStatusRequest;
import com.cozastore.dto.request.ReviewRequest;
import com.cozastore.dto.request.ReviewStatusRequest;
import com.cozastore.entity.Category;
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

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

	@InjectMocks
	ReviewService reviewService;

	@Mock
	IReviewRepository reviewRepository;

	@Mock
	ReviewMapper reviewMapper;

	@Mock
	IProductRepository productRepository;

	@Mock
	IUserRepository userRepository;

	@Mock
	PageMapper pageMapper;

	@Test
	void findAllReview_whenDataValid_shouldReturnResponse() {
		List<Review> list = new ArrayList<>();
		Review review = Review.builder().Id(1).status(0).build();
		List<ReviewRespone> expect=new ArrayList<>();
		list.add(review);
		when(reviewRepository.findAll()).thenReturn(list);
		List<ReviewRespone> actual=reviewService.getAllReview();
		expect=reviewMapper.convertToListReviewResponse(list);
		assertThat(actual).usingRecursiveComparison().isEqualTo(expect);
	}
	
	@Test
	void findAllByPage_whenDataValid_shouldReturnResponse() {
		Pageable pageable = PageRequest.of(0, 1);
		ReviewPageResponse expect = new ReviewPageResponse();
		List<Review> content = new ArrayList<>();
		Review value = new Review();
		value.setId(10);
		content.add(value);
		Page<Review> page = new PageImpl<>(content);
		when(reviewRepository.findByStatus(Status.ACTIVE.getValue(), pageable)).thenReturn(page);
		expect.setPageResponse(
				pageMapper.convertPagetoPageResponse(page, pageable.getPageNumber(), pageable.getPageSize()));
		expect.setReviewRespones(reviewMapper.convertToListReviewResponse(content));
		assertThat(reviewService.getReviewActiveByPage(pageable)).usingRecursiveComparison().isEqualTo(expect);
	}
	
	@Test
	void inserReview_whenProductNotFound_shouldReturnException() {
		ReviewRequest reviewRequest=mock(ReviewRequest.class);
		when(productRepository.findById(reviewRequest.getProductId())).thenReturn(Optional.empty());
		NotFoundException actual = Assertions.assertThrows(NotFoundException.class,
				() -> reviewService.addReviewToProduct(reviewRequest, ""));
		assertThat(actual.getMessage()).isEqualTo(ErrorString.PRODUCT_NOT_FOUND);
	}

	@Test
	void inserReview_whenUserNotFound_shouldReturnException() {
		ReviewRequest reviewRequest=mock(ReviewRequest.class);
		Product product=mock(Product.class);
		when(productRepository.findById(reviewRequest.getProductId())).thenReturn(Optional.of(product));
		when(userRepository.findUserByEmail("")).thenReturn(Optional.empty());
		NotFoundException actual = Assertions.assertThrows(NotFoundException.class,
				() -> reviewService.addReviewToProduct(reviewRequest, ""));
		assertThat(actual.getMessage()).isEqualTo(ErrorString.USER_NOT_FOUND);
	}
	
	@Test
	void inserReview_whenDataValid_shouldSaveReview() {
		ReviewRequest reviewRequest=mock(ReviewRequest.class);
		Product product=mock(Product.class);
		User user=mock(User.class);
		Review review=mock(Review.class);
		when(productRepository.findById(reviewRequest.getProductId())).thenReturn(Optional.of(product));
		when(userRepository.findUserByEmail("")).thenReturn(Optional.of(user));
		when(reviewMapper.convertRequestToReview(reviewRequest)).thenReturn(review);
		reviewService.addReviewToProduct(reviewRequest, "");
		verify(reviewRepository).save(review);
		verify(productRepository).save(product);
	}
	
	@Test
	void updateStatusReview_whenReviewNotFound_shouldThrowException() {
		ReviewStatusRequest reviewRequest = mock(ReviewStatusRequest.class);
		when(reviewRepository.findById(reviewRequest.getId())).thenReturn(Optional.empty());
		NotFoundException actual = Assertions.assertThrows(NotFoundException.class,
				() -> reviewService.changeStatusReview(reviewRequest));
		assertThat(actual.getMessage()).isEqualTo(ErrorString.REVIEW_NOT_FOUND);
	}

	@Test
	void updateStatusReview_whenDataValid_shouldSaveReview() {
		ReviewStatusRequest reviewRequest = ReviewStatusRequest.builder().id(1).status(Status.ACTIVE).build();
		Review review = mock(Review.class);
		when(reviewRepository.findById(reviewRequest.getId())).thenReturn(Optional.of(review));
		reviewService.changeStatusReview(reviewRequest);
		verify(review).setStatus(reviewRequest.getStatus().getValue());

	}

	
}
