package repository.review;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Review;

public interface IReviewRepository extends JpaRepository<Review, Integer>{

}
