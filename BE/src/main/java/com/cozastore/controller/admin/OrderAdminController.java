package com.cozastore.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozastore.dto.reponse.HeaderResponse;
import com.cozastore.dto.reponse.OrderPageResponse;
import com.cozastore.dto.reponse.OrderRespone;
import com.cozastore.service.order.IOrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/admin/product")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class OrderAdminController {
	@Autowired
	private IOrderService orderService;

	@GetMapping("/")
	public ResponseEntity<OrderPageResponse> getAllOrder(@PageableDefault(size = 5) Pageable pageable) {
		return ResponseEntity.ok(orderService.getAllOrderByPage(pageable));
	}
}
