package controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.reponse.CategoryRespone;
import dto.reponse.FormatRespone;
import dto.reponse.HeaderResponse;
import dto.request.CategoryInsertRequest;
import dto.request.CategoryRequest;
import dto.request.CategoryStatusRequest;
import dto.request.FormatInsertRequest;
import dto.request.FormatRequest;
import dto.request.FormatStatusRequest;
import service.format.IFormatService;

@RestController
@RequestMapping("/v1/admin/format")
public class FormatController {
	
	@Autowired
	IFormatService formatService;
	
	@GetMapping
	public ResponseEntity<List<FormatRespone>> getFormatByPage(Pageable pageable){
		HeaderResponse<FormatRespone> headerResponse=formatService.getFormatByPage(pageable);
		return ResponseEntity.ok().headers(headerResponse.getHeaders()).body(headerResponse.getItems());
	}
	@PutMapping("/update")
	public ResponseEntity<String> updateFormat(@Valid @RequestBody FormatRequest formatRequest){
		return ResponseEntity.ok(formatService.updateFormat(formatRequest));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FormatRespone> getFormatById(@PathVariable Integer id){
		return ResponseEntity.ok(formatService.getFormatById(id));
	}
	
	@PostMapping("/insert")
	public ResponseEntity<String> insertFormat(@Valid @RequestBody FormatInsertRequest insertRequest){
		return ResponseEntity.ok(formatService.insertFormat(insertRequest));
	}
	@PostMapping("/update/status")
	public ResponseEntity<String> updateFormatStatus(@Valid @RequestBody FormatStatusRequest formatStatusRequest){
		return ResponseEntity.ok(formatService.updateStatusFormat(formatStatusRequest));
	}
}
