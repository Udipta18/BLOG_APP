package com.example.springboot.blog.springboot.blog.data_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.springboot.blog.springboot.blog.data_api.security.CustomUserDetailsService;
import com.example.springboot.blog.springboot.blog.data_api.security.JwtAuthenticationEntryPoint;
import com.example.springboot.blog.springboot.blog.data_api.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	 @Autowired
	    private JwtAuthenticationEntryPoint authenticationEntryPoint;

	    @Bean
	    public JwtAuthenticationFilter jwtAuthenticationFilter(){
	        return  new JwtAuthenticationFilter();
	    }

	@Bean
	PasswordEncoder PasswordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		 http
         .csrf().disable()
         .exceptionHandling()
         .authenticationEntryPoint(authenticationEntryPoint)
         .and()
         .sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         .and()
         .authorizeRequests()
         .antMatchers(HttpMethod.GET, "/api/**").permitAll()
         .antMatchers("/api/auth/**").permitAll()
         .anyRequest()
         .authenticated();
		 
		 http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(PasswordEncoder());
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/*
	 * @Override
	 * 
	 * @Bean protected UserDetailsService userDetailsService() { // TODO
	 * Auto-generated method stub UserDetails udipta=
	 * User.builder().username("udipta").password(PasswordEncoder().encode(
	 * "password")).roles("USER").build(); UserDetails admin=
	 * User.builder().username("admin").password(PasswordEncoder().encode(
	 * "admin")).roles("ADMIN").build();
	 * 
	 * return new InMemoryUserDetailsManager(udipta,admin); }
	 */

}
