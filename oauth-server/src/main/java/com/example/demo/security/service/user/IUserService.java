package com.example.demo.security.service.user;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.model.dto.user.UserDto;

/**
 * User 表数据服务层接口
 */
public interface IUserService extends IService<UserDto> {

    UserDto getUserByUserName(String username);

}