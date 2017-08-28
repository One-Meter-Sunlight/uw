package com.example.demo.client;

import com.example.demo.constants.ResponseCodeConstants;
import com.example.demo.model.CustomResponseBody;
import com.example.demo.model.dto.user.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {

    @Override
    public CustomResponseBody<UserDto> loadUserByLoginName(String _loginName) {
        CustomResponseBody<UserDto> response = new CustomResponseBody<>();
        response.setResultCode(ResponseCodeConstants.REMOTE_CALL_FAILED);
        return response;
    }

}
