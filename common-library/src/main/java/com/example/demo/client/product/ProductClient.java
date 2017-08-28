package com.example.demo.client.product;

import com.example.demo.model.CustomResponseBody;
import com.example.demo.model.dto.order.ItemDto;
import com.example.demo.model.dto.product.ReservedProductDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "product-service", fallbackFactory = ProductClientFallbackFactory.class)
public interface ProductClient {

    @RequestMapping(value = "/products/reservation", method = RequestMethod.PUT)
    CustomResponseBody<List<ReservedProductDto>> reserveProducts(
            @RequestParam("order_id") String _orderId,
            @RequestBody List<ItemDto> _items);

}