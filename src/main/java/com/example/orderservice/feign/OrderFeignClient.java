package com.example.orderservice.feign;

import com.example.orderservice.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "productservice")
public interface OrderFeignClient {

    @GetMapping("/products/{id}")
    Product getProductById(@RequestParam("id") long id);


}
