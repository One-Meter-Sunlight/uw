package com.example.demo.client.product;

import com.example.demo.constants.ResponseCodeConstants;
import com.example.demo.model.CustomResponseBody;
import com.example.demo.model.dto.order.ItemDto;
import com.example.demo.model.dto.product.ReservedProductDto;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ProductClientFallbackFactory implements FallbackFactory<ProductClient> {

    @Override
    public ProductClient create(Throwable cause) {
        return new ProductClient() {
            @Override
            public CustomResponseBody<List<ReservedProductDto>> reserveProducts(String _orderId, List<ItemDto> _items) {
                log.error(cause.getMessage());

                CustomResponseBody<List<ReservedProductDto>> response = new CustomResponseBody<>();
                response.setResultCode(ResponseCodeConstants.REMOTE_CALL_FAILED);
                return response;
            }
        };
    }

}
