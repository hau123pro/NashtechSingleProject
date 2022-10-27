package service.review;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import dto.reponse.FormatRespone;
import dto.reponse.HeaderResponse;
import dto.reponse.ReviewRespone;
import dto.request.ReviewRequest;
import dto.request.ReviewStatusRequest;
import entity.Product;
import entity.Review;
import entity.User;
import exception.BadRequestException;
import mappers.ReviewMapper;
import mappers.UtilMapper;
import repository.product.IProductRepository;
import repository.review.IReviewRepository;
import repository.user.IUserRepository;
import utils.constant.ErrorString;
import utils.constant.Status;
import utils.constant.SuccessString;

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
	public String addReviewToProduct(ReviewRequest reviewRequest,String email) {
		Product product=productRepository.findById(reviewRequest.getProductId())
				.orElseThrow(()->new BadRequestException(ErrorString.PRODUCT_NOT_FOUND));
		User user=userRepository.findUserByEmail(email).orElseThrow(()->new BadRequestException(ErrorString.USER_NOT_FOUND));
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
				.orElseThrow(()->new BadRequestException(ErrorString.REVIEW_NOT_FOUND));
		review.setStatus(request.getStatus().getValue());
		reviewRepository.save(review);
		return SuccessString.REVIEW_UPDATE_STATUS_SUCCESS;
	}
	
	
	
	
}
