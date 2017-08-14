package com.example.demo.mapper;

import java.util.List;

import com.example.demo.SuperMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Select;

/**
 * User 表数据库控制层接口
 */
public interface UserMapper extends SuperMapper<User> {

    @Select("select uid as id, username from user")
    List<User> selectListBySQL();

}