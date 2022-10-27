package service.review;

import java.util.List;

import org.springframework.data.domain.Pageable;

import dto.reponse.HeaderResponse;
import dto.reponse.ReviewRespone;
import dto.request.ReviewRequest;
import dto.request.ReviewStatusRequest;

public interface IReviewService {
	public List<ReviewRespone> getAllReview();
	
	public HeaderResponse<ReviewRespone> getReviewByPage(Pageable pageable);
	
	public String addReviewToProduct(ReviewRequest reviewRequest,String email);
	
	public String changeStatusReview(ReviewStatusRequest request);
}
