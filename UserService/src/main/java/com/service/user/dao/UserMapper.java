package com.service.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.service.user.bean.User;

@Mapper
public interface UserMapper {
	User findOne(Long id);
	
	int deleteById(String id);

    int insert(User user);   

    int updateById(Long id);    

    List<User> findAll();
    
    //int updateByPrimaryKeySelective(ApplyOrder record);

}
