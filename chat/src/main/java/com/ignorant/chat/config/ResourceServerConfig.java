package com.ignorant.chat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import com.ignorant.chat.authentication.AuthenticationFailureHandler;
import com.ignorant.chat.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.formLogin().loginPage("/authentication").loginProcessingUrl("/authentication")
				.successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler).and().authorizeRequests()
				.antMatchers("/sign_in.html", "/authentication").permitAll().antMatchers(HttpMethod.OPTIONS).permitAll()
				.anyRequest().authenticated().and().csrf().disable();
	}
}
