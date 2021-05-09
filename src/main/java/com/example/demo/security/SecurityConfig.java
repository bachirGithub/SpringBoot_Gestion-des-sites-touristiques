package com.example.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
private String imagesDir=System.getProperty("user.home");
@Autowired
private DataSource dataSource;
@Autowired
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
	auth.jdbcAuthentication()
	.dataSource(dataSource)
	.usersByUsernameQuery("select username as principal,password as credentials,"
			+ "active from users where username=?")
	.authoritiesByUsernameQuery("select users_username as principal, roles_role as role from users_roles "
			+ "where users_username=?")
	.passwordEncoder(new BCryptPasswordEncoder())
	.rolePrefix("ROLE_");
	
	}
	
@Override
protected void configure(HttpSecurity http) throws Exception {
	http.formLogin();
	http.authorizeRequests().antMatchers("/**/Form**").hasRole("ADMIN");
	//http.authorizeRequests().antMatchers("/region/Details","/sites/Details").hasRole("USER");
	//http.authorizeRequests().antMatchers("/","/images/**", "/sites/Index").permitAll();
	//http.authorizeRequests().anyRequest().authenticated();
	http.exceptionHandling().accessDeniedPage("/notAuthorize");
}

@Bean
public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
}

}
