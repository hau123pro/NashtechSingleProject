package com.cozastore.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozastore.dto.reponse.ReviewPageResponse;
import com.cozastore.dto.reponse.UserPageResponse;
import com.cozastore.dto.request.ProductStatusRequest;
import com.cozastore.dto.request.UserRoleRequest;
import com.cozastore.dto.request.UserStatusRequest;
import com.cozastore.service.user.IUserService;

@RestController
@RequestMapping("/v1/admin/account")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class UserAdminController {
	
	@Autowired
	IUserService userService;
	
	@GetMapping
	public ResponseEntity<UserPageResponse> getAllUserByPage(Pageable pageable) {
		return ResponseEntity.ok(userService.getAllUserByPage(pageable));
	}
	
	@PutMapping(value = "/status/update")
	public ResponseEntity<Object> updateStatusUser(@Valid @RequestBody UserStatusRequest request) {
		userService.changeStatusUser(request);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(value = "/status/role")
	public ResponseEntity<Object> updateRoleUser(@Valid @RequestBody UserRoleRequest request) {
		userService.changeRoleUser(request);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
