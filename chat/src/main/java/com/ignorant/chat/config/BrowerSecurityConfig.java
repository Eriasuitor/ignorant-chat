package com.ignorant.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.formLogin().loginPage("/authentication").loginProcessingUrl("/authentication").and().authorizeRequests()
				.antMatchers("/sign_in.html", "/authentication").permitAll().anyRequest().authenticated()
				.and().csrf().disable();
	}
}
