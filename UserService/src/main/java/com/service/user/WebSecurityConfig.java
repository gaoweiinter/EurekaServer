package com.service.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	@Value("${user.serviceUrl}")
//	private String serviceUrl;
//	@Value("${security.user.name}")
//	private String name;
//	@Value("${security.user.password}")
//	private String password;
//	@Value("${security.user.role}")
//	private String role;
	
//	@Resource
//	public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
////		System.out.println("Info from Properties: =====" + name + "==" + password + "===" + role);
//		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("user")
//				.password(new BCryptPasswordEncoder().encode("pwd123")).roles("ADMIN");
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//所有请求都要经过HTTP basic认证
//		http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
//		http.csrf().disable();// 关闭csrf
		super.configure(http);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		//明文编码器。只做明文测试
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(this.userDetailsService).passwordEncoder(this.passwordEncoder());
	}
	
	@Component
	class CustomUserDetailsService implements UserDetailsService{
		/**
		 * 1 user password1, role=user-role
		 * 2 admin password2, role=admin-role
		 */
		
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
			if("user".equals(username)){
				return new SecurityUser("user","password1","user-role");
			} else if("admin".equals(username)){
				return new SecurityUser("admin","password2","admin-role");
			} else {
				return null;
			}
		}
	}
	
	class SecurityUser implements UserDetails{
		private static final long serialVersionUID = 1L;
		private Long id;
		private String username;
		private String password;
		private String role;
		
		public SecurityUser(String username, String password, String role) {
			super();
			this.username = username;
			this.password = password;
			this.role = role;
		}
		
		public SecurityUser() {
			
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.role);
			authorities.add(authority);
			
			return authorities;
		}

		@Override
		public String getPassword() {
			// TODO Auto-generated method stub
			return password;
		}

		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return username;
		}

		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		}
	}
}
