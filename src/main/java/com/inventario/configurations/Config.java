package com.inventario.configurations;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

//@SuppressWarnings("deprecation")
//@Configuration
//@EnableWebSecurity
public class Config { // extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private UserDetailsService userDetailsService;
//
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(encodePWD());
//		super.configure(auth);
//	}
//
//	@Bean
//	public BCryptPasswordEncoder encodePWD() {
//		return new BCryptPasswordEncoder();
//	}
//
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/**", "/webjars/**", "/img/**", "/css/**", "/js/**", "swagger-ui.html")
//				.addResourceLocations("classpath:/META-INF/resources/webjars/", "classpath:/META-INF/resources/");
//	}
//
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
//	}

}
