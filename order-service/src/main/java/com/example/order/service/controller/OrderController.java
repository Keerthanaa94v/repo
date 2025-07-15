package com.example.order.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.order.service.dto.OrderResponseDTO;
import com.example.order.service.dto.ProductDto;
import com.example.order.service.entity.Order;
import com.example.order.service.repository.OrderRepository;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	//Method to place an order
	@PostMapping("/placeOrder")
	public Mono<ResponseEntity<OrderResponseDTO>> placeOrder(@RequestBody Order order){
		//Fetch product details from productdto
		return webClientBuilder.build().get().uri("http://localhost:8081/products/"+ order.getProductId()).retrieve()
				.bodyToMono(ProductDto.class).map(productDto ->{
					OrderResponseDTO orderResponse=new OrderResponseDTO();
					
					orderResponse.setProductId(order.getProductId());
					orderResponse.setProductName(productDto.getName());
					orderResponse.setQuantity(order.getQuantity());
					orderResponse.setTotalPrice(productDto.getPrice()*order.getQuantity());
					orderResponse.setProductPrice(productDto.getPrice());
					
					//save order details to DB
					orderRepository.save(order);
					orderResponse.setOrderId(order.getId());
					return ResponseEntity.ok(orderResponse);
					
				});
	}
	
	//Get all orders
	@GetMapping
	public List<Order> getAllOrders(){
		return orderRepository.findAll();
	}
	
	

}
