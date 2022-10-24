package service.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.reponse.ReviewRespone;
import entity.Review;
import mappers.ReviewMapper;
import mappers.UtilMapper;
import repository.review.IReviewRepository;

@Service
public class ReviewService implements IReviewService{
	
	@Autowired
	IReviewRepository iReviewRepository;
	
	@Autowired
	ReviewMapper reviewMapper;

	@Override
	public List<ReviewRespone> getAllReview() {
		// TODO Auto-generated method stub
		List<Review> reviews=iReviewRepository.findAll();
		return reviewMapper.getAllReview(reviews);
	}

}
