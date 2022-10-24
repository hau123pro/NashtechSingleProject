package controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.reponse.HeaderResponse;
import dto.reponse.OrderRespone;
import entity.Orders;
import mappers.OrderMapper;
import service.order.IOrderService;

@RestController
@RequestMapping("/v1/admin/order")
public class OrderController {
	
	@Autowired 
	private IOrderService iOrderService;
	
	@GetMapping()
	public ResponseEntity<List<OrderRespone>> getAllOrder(@PageableDefault(size=5) Pageable pageable){
		HeaderResponse<OrderRespone> headerResponse=iOrderService.getAllOrder(pageable);
		return ResponseEntity.ok().headers(headerResponse.getHeaders()).body(headerResponse.getItems());
	}
	@PostMapping("/add")
	public String  addNewOrder(Principal principal) {
		return iOrderService.addOrder(principal.getName());
	}
//	@PutMapping("/update")
//	public 
	
}	
