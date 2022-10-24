package repository.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.Author;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Integer>{

}
