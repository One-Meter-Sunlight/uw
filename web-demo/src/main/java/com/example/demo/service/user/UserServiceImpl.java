package com.example.demo.service.user;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.user.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


	@Override
	public List<User> selectListBySQL() {
		return baseMapper.selectListBySQL();
	}

	public List<User> selectList(Page<User> page){
		return baseMapper.selectList(page);
	}

	@Override
	public User getUserById(long id) {
		log.debug("Getting user={}", id);
		return baseMapper.selectById(id);
	}

	@Override
	public User getUserByUserName(String username) {
		log.debug("Getting user by username={}", username);
		return baseMapper.selectByUsername(username);
	}
}