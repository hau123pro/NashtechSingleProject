package mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dto.reponse.ReviewRespone;
import entity.Review;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewMapper {
	@Autowired
	private UtilMapper utilMapper;
	public List<ReviewRespone> getAllReview(List<Review> list){
		List<ReviewRespone> listRespone=new ArrayList<>();
	 for(Review review:list) {
		 ReviewRespone reviewRespone=ReviewRespone.builder()
				 					.userName(review.getUser().getName())
				 					.content(review.getContent())
				 					.rating(review.getRating())
				 					.dateCreate(review.getDateCreate())
				 					.build();
		 listRespone.add(reviewRespone);
	 }
	 return listRespone;
	}
}
