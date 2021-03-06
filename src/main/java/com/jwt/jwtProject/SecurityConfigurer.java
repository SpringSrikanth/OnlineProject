package com.jwt.jwtProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwt.jwtProject.filters.JwtFilter;
import com.jwt.jwtProject.service.MyUserDetailsService;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	private static final Logger log = LoggerFactory.getLogger(SecurityConfigurer.class);

	@Autowired
	private MyUserDetailsService MyUserDetailsService;

	@Autowired
	private JwtFilter JwtFileter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(MyUserDetailsService);
	}
	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}


	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//for giving permission to one url to authenticate
		/*
		  http.csrf().disable().authorizeRequests() .antMatchers("/authenticate")
		  .permitAll().anyRequest().authenticated()
		  .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.
		  STATELESS);
		 */

		http.cors()
		.and()
		.csrf()
		.disable()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
		.antMatchers("/",
				"/favicon.ico",
				"/**/*.png",
				"/**/*.gif",
				"/**/*.svg",
				"/**/*.jpg",
				"/**/*.html",
				"/**/*.css",
				"/**/*.js")
		.permitAll()
		.antMatchers(
				"/api/user/checkUsernameAvailability",
				"/api/user/checkEmailAvailability",
				"/users/create/user",
				"/hello123",
				"/home",
				"/health"
				)
		.permitAll()
		.antMatchers("/authenticate")
		.permitAll()
		.anyRequest().authenticated();

		http.addFilterBefore(JwtFileter, UsernamePasswordAuthenticationFilter.class);
	}
}
