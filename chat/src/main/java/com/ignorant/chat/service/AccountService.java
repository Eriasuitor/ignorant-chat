package com.ignorant.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ignorant.chat.mapper.AccountMapper;
import com.ignorant.chat.pojo.Account;

@Component
public class AccountService implements UserDetailsService {

	@Autowired
	private AccountMapper accountMapper;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Account account = accountMapper.queryAccount(username);
		if (account == null)
			throw new UsernameNotFoundException(username);
		return new User(username, account.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("user"));
	}

}
