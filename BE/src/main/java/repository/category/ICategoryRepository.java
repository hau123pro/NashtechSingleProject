package repository.category;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Category;

public interface ICategoryRepository extends JpaRepository<Category, Integer>{
	
}
