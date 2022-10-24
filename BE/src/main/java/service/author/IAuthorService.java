package service.author;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import entity.Author;
import repository.author.IAuthorRepository;


public interface IAuthorService {
	
	public Page<Author> getAllAuthor(Pageable pageable);
	
	
}
