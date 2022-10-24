package controller;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import entity.Author;
import service.author.IAuthorService;

@RestController
@RequestMapping("/v1/admin/author")
@Validated
public class AuthorController {
	@Autowired
	IAuthorService iAuthorService;
	
	@GetMapping()
	public List<Author> getAllAuthor( @PageableDefault(size=2) Pageable page ){
			List<Author> list= iAuthorService.getAllAuthor(page).getContent();
			return list;
	}
	
	
}
