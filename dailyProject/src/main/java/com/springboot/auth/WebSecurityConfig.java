package com.springboot.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests()
			.antMatchers("/").permitAll()
            	//루트(/) 요청을 모두에게 허용
			.antMatchers("/css/**", "/js/**", "/img/**").permitAll()
           	 //css, js, img 아래 모든 url 요청을 모두에게 허용
			.antMatchers("/guest/**").permitAll()
            	//guest 아래 모든 url 요청을 모두에게 허용
			.antMatchers("/member/**").hasAnyRole("USER", "ADMIN")
            	//member 아래 url 요청은 'USER'나 'ADMIN'에게 허용
			.antMatchers("/admin/**").hasRole("ADMIN")
            	//admin아래 url 요청은 'ADMIN'에게 허용
			.anyRequest().authenticated();
		
		http.formLogin()
			.permitAll();
		
		http.logout()
			.permitAll();
	}
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
			.withUser("user").password(passwordEncoder().encode("1234")).roles("USER")
            //사용자이름(user),비밀번호(1234),역할(USER)로 사용자 등록 
			.and()
			.withUser("admin").password(passwordEncoder().encode("1234")).roles("ADMIN");
            //사용자이름(admin),비밀번호(1234),역할(ADMIN)로 사용자 등록 
			//ROLE_ADMIN 에서 ROLE_은 자동으로 붙는다.
	}

	//passwordEncoder() 추가 
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}