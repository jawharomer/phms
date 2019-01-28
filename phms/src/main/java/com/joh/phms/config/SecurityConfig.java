package com.joh.phms.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.joh.phms.service.AppUserDetailService;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	@Autowired
	private AppUserDetailService appUserDetailService;

	public SecurityConfig() {
		logger.info("SecurityConfig->fired");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login/**", "/logout").permitAll()
				.antMatchers("/adminRoot/**", "/admin/**", "/productStepUps/**", "/vendors/**", "/productCategories/**")
				.hasRole("ADMIN").anyRequest().authenticated().and().formLogin().loginPage("/login")
				.defaultSuccessUrl("/adminRoot").and().logout().deleteCookies("JSESSIONID").logoutUrl("/logout")
				.logoutSuccessUrl("/login").permitAll().and().rememberMe().key("@#$j232Kdf19)__")
				.tokenValiditySeconds(86400).and().exceptionHandling()
				.accessDeniedPage("/WEB-INF/views/accessDenied.jsp");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
		auth.userDetailsService(appUserDetailService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	}

}
