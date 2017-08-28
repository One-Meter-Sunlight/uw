package com.example.demo.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.example.demo.SuperMapper;
import com.example.demo.model.dto.user.UserDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * User 表数据库控制层接口
 */
public interface UserMapper extends SuperMapper<UserDto> {


    @Select("select uid as id, username, password, role from user where username = #{username}")
    UserDto selectByUsername(@Param("username") String username);
}