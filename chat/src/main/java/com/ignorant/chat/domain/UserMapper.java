package com.ignorant.chat.domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
	@Select("SELECT * FROM user WHERE name = #{name}")
	@Results({ @Result(property = "name", column = "name"), @Result(property = "password", column = "password"),
			@Result(property = "salt", column = "salt"), @Result(property = "state", column = "state") })
	UserEntity getOne(String name);

	@Insert("INSERT INTO user(name, password, salt, state) VALUES(#{name}, #{password}, #{salt},#{state})")
	void insert(UserEntity user);
}
