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

import com.cozastore.dto.reponse.FormatPageResponse;
import com.cozastore.dto.reponse.FormatRespone;
import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.request.FormatInsertRequest;
import com.cozastore.dto.request.FormatRequest;
import com.cozastore.dto.request.FormatStatusRequest;
import com.cozastore.service.format.IFormatService;

@RestController
@RequestMapping("/v1/format")
public class FormatController {
	
	@Autowired
	IFormatService formatService;
	
	
	@GetMapping("/active")
	public ResponseEntity<FormatPageResponse> getFormatActiveByPage( @PageableDefault(size=8) Pageable pageable){
		return ResponseEntity.ok(formatService.getFormatActiveByPage(pageable));
	}
	@GetMapping("/active/all")
	public ResponseEntity<List<FormatRespone>> getAllFormatActive(){
		return ResponseEntity.ok(formatService.getAllFormatActive());
	}
	
}
