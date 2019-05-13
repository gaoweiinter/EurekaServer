package com.example.eureka.server.demo;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableConfigurationProperties({EurekaServerConfing.class})
//@Component
//@ConfigurationProperties(prefix="defineTest")
//@PropertySource({"classpath:application-eureka-primary.properties"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	@Value("${security.user.name}")
//	private String name;
//	@Value("${security.user.password}")
//	private String password;
//	@Value("${security.user.role}")
//	private String role;
	
//	@Autowired
//	EurekaServerConfig1 config;
	
	@Resource
	public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		System.out.println("Info from Properties: =====" + name + "==" + password + "===" + role);
		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("user")
				.password(new BCryptPasswordEncoder().encode("pwd123")).roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();// 关闭csrf
		super.configure(http);
	}
}
