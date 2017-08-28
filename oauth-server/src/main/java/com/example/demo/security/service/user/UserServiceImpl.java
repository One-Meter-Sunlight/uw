package com.example.demo.security.service.user;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.dto.user.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * User 表数据服务层接口实现类
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDto> implements IUserService {

    @Override
    public UserDto getUserByUserName(String username) {
        log.debug("Getting user by username={}", username);
        return baseMapper.selectByUsername(username);
    }
}