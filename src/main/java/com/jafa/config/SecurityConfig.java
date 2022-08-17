package com.jafa.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.jafa.security.CustomAccessDeniedHandler;
import com.jafa.security.LoginSuccessHandler;
import com.jafa.security.ProjectUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
		
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("utf-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter, CsrfFilter.class);
		
		http.authorizeRequests()
			.antMatchers("/stratum/all").permitAll()
			.antMatchers("/stratum/user").access("hasRole('ROLE_USER')")
			.antMatchers("/stratum/admin").access("hasRole('ROLE_ADMIN')");
		
		http.formLogin()
				.usernameParameter("loginId")
				.passwordParameter("loginPw")
				.loginPage("/member/projectLogin") // 로그인 화면 get 
				.loginProcessingUrl("/member/projectLogin") // 로그인 처리 post
				.successHandler(loginSuccessHandler());
		
		http.logout()
			.logoutUrl("/member/projectLogout") // 로그아웃 처리 post
			.invalidateHttpSession(true)
			.deleteCookies("remember-me", "JSESSION_ID");
		
		http.rememberMe().key("project").tokenRepository(persistentTokenRepository())
			.tokenValiditySeconds(604800);
		
		http.exceptionHandling()
			.accessDeniedHandler(customAccessDeniedHandler());
		
		http.csrf().ignoringAntMatchers("/uploadAjaxAction","/deleteFile","/resources/**",
				"/userUploadAjaxAction","/deleteUserFile");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new LoginSuccessHandler();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ProjectUserDetailsService userDetailsService() {
		return new ProjectUserDetailsService();
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}
	
	@Bean
	public CustomAccessDeniedHandler customAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
}
