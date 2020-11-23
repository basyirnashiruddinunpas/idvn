package org.masbas.idvn.config;

import org.masbas.idvn.helpers.AuthFailureHandler;
import org.masbas.idvn.helpers.AuthLogoutSuccessHandler;
import org.masbas.idvn.helpers.UserHelper;
import org.masbas.idvn.services.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {
 	
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    	
    	auth.userDetailsService(userDetailsService());
    	
    	PasswordEncoder encoder = 
    	          PasswordEncoderFactories.createDelegatingPasswordEncoder();
    	    	auth
    	          .inMemoryAuthentication()
    	          .withUser("user")
    	          .password(encoder.encode("mainbola"))
    	          .roles("USER")
    	          .and()
    	          .withUser("admin")
    	          .password(encoder.encode("mainbola"))
    	          .roles("USER", "ADMIN");
    }
 
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
    	http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/admin/**").hasRole(UserHelper.TIPE_ADMIN)
        .antMatchers("/").permitAll()
        .antMatchers("/js/*").permitAll()
        .antMatchers("/css/*").permitAll()
        .antMatchers("/base*").permitAll()
        .antMatchers("/base/*").permitAll()
        .antMatchers("/home").permitAll()
        .antMatchers("/kerentanan*").permitAll()
        .antMatchers("/login*").permitAll()
        .antMatchers("/register*").permitAll()
        .antMatchers("/register/*").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/dologin")
        .defaultSuccessUrl("/")
        .failureUrl("/login?error=true")
        .failureHandler(authenticationFailureHandler())
        .and()
        .logout()
        .logoutUrl("/logout")
        .deleteCookies("JSESSIONID")
        .logoutSuccessHandler(logoutSuccessHandler());;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder(); 
    }
    
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
    	return new AuthFailureHandler();
    }
    
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
    	return new AuthLogoutSuccessHandler();
    }
    
    @Bean
    public MyUserDetailService userDetailsService() {
    	return new MyUserDetailService();
    }
}