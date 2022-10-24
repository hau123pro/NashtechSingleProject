package service.author;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import entity.Author;
import repository.author.IAuthorRepository;
@Service
public class AuthorService implements IAuthorService{
	@Autowired
	IAuthorRepository iAuthorRepository;
	@Override
	public Page<Author> getAllAuthor(Pageable i){
		Page<Author> authorList=iAuthorRepository.findAll(i);
		return authorList;
	}

	
}
