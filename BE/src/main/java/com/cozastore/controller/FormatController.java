package com.cozastore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozastore.dto.reponse.CategoryRespone;
import com.cozastore.dto.reponse.FormatRespone;
import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.request.CategoryInsertRequest;
import com.cozastore.dto.request.CategoryRequest;
import com.cozastore.dto.request.CategoryStatusRequest;
import com.cozastore.dto.request.FormatInsertRequest;
import com.cozastore.dto.request.FormatRequest;
import com.cozastore.dto.request.FormatStatusRequest;
import com.cozastore.service.format.IFormatService;

@RestController
@RequestMapping("/v1/admin/format")
public class FormatController {
	
	@Autowired
	IFormatService formatService;
	
	@GetMapping
	public ResponseEntity<List<FormatRespone>> getFormatByPage(@PageableDefault(size=8) Pageable pageable){
		HeaderResponse<FormatRespone> headerResponse=formatService.getFormatByPage(pageable);
		return ResponseEntity.ok().headers(headerResponse.getHeaders()).body(headerResponse.getItems());
	}
	@GetMapping("/active")
	public ResponseEntity<List<FormatRespone>> getCategoryActiveByPage( @PageableDefault(size=8) Pageable pageable){
		HeaderResponse<FormatRespone> headerResponse=formatService.getFormatActiveByPage(pageable);
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
