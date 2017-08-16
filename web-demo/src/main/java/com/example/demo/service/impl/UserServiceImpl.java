package com.example.demo.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Override
	public List<User> selectListBySQL() {
		return baseMapper.selectListBySQL();
	}

	public List<User> selectList(Page<User> page){
		return baseMapper.selectList(page);
	}
}