package com.ignorant.chat.domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
	@Select("SELECT * FROM users WHERE id = #{id}")
	@Results({ @Result(property = "name", column = "name"), @Result(property = "password", column = "password"),
			@Result(property = "salt", column = "salt"), @Result(property = "state", column = "state") })
	UserEntity getOne(Long id);

	@Insert("INSERT INTO users(name, passWord, salt, state) VALUES(#{name}, #{passWord}, #{salt},#{state})")
	void insert(UserEntity user);
}
