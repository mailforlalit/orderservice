package com.example.orderservice.controller;

import com.example.orderservice.dto.Product;
import com.example.orderservice.feign.OrderFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class OrderController {

    private OrderFeignClient orderFeignClient;
    private RestTemplate restTemplate;
    private WebClient webClient;

    public OrderController(RestTemplate restTemplate, WebClient.Builder webClientBuilder, OrderFeignClient orderFeignClient) {
        this.webClient = webClientBuilder.build();
        this.orderFeignClient = orderFeignClient;
        this.restTemplate = restTemplate;

    }

    @GetMapping("/orders/product/{id}")
    public Product getOrderById(@PathVariable String id) {
        return restTemplate.getForObject("http://productservice/products/" + id, Product.class);
    }

    @GetMapping("/orders/product/web/{id}")
    public Product getWebOrderById(@PathVariable String id) {
        return webClient.get().uri("http://productservice/products/" + id)
                .retrieve().bodyToMono(Product.class).block();
    }

    @GetMapping("/orders/product/feign/{id}")
    public Product getFeignOrderById(@PathVariable Long id) {
        return orderFeignClient.getProductById(id);
    }

}
