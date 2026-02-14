package com.example.orderservice.controller;

import com.example.orderservice.dto.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    private RestTemplate restTemplate;

    public OrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/orders/product/{id}")
    public Product getOrderById(@PathVariable String id) {
        return restTemplate.getForObject("http://productservice/products/" + id, Product.class);
    }

}
