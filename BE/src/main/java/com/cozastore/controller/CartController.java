package com.cozastore.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozastore.dto.reponse.CartRespone;
import com.cozastore.dto.request.CartItemIdRequest;
import com.cozastore.dto.request.CartItemRequest;
import com.cozastore.service.cart.ICartService;

@RestController
@RequestMapping("/v1/cart")
public class CartController {

	@Autowired
	private ICartService cartService;

	@GetMapping("/countItem")
	public ResponseEntity<Integer> addProductToCart(Principal principal) {
		return ResponseEntity.ok(cartService.getCountItemCart(principal.getName()));
	}

	@GetMapping()
	public ResponseEntity<CartRespone> getCart(Principal principal) {
		return ResponseEntity.ok(cartService.getCart(principal.getName()));
	}
}
