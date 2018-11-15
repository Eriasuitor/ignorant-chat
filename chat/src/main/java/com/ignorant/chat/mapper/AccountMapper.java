package com.ignorant.chat.mapper;

import com.ignorant.chat.pojo.Account;
import com.ignorant.chat.utils.MyMapper;

public interface AccountMapper extends MyMapper<Account> {
	
	public Account queryAccount(String userId);
	
}