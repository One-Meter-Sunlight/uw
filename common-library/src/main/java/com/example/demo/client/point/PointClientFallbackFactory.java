package com.example.demo.client.point;

import com.example.demo.client.point.PointClient;
import com.example.demo.constants.ResponseCodeConstants;
import com.example.demo.model.CustomResponseBody;
import com.example.demo.model.dto.point.FreezePointsDto;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class PointClientFallbackFactory implements FallbackFactory<PointClient> {

    @Override
    public PointClient create(Throwable cause) {
        return new PointClient() {
            @Override
            public CustomResponseBody<BigDecimal> calculateUsableAmount(String _userId) {
                log.error(cause.getMessage());

                CustomResponseBody<BigDecimal> response = new CustomResponseBody<>();
                response.setResultCode(ResponseCodeConstants.REMOTE_CALL_FAILED);
                return response;
            }

            @Override
            public CustomResponseBody freezePoints(FreezePointsDto _freeze) {
                log.error(cause.getMessage());

                CustomResponseBody response = new CustomResponseBody<>();
                response.setResultCode(ResponseCodeConstants.REMOTE_CALL_FAILED);
                return response;
            }
        };
    }

}
