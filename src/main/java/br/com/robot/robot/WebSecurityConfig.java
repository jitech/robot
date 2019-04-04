package br.com.robot.robot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth)
  throws Exception {
    auth
      .inMemoryAuthentication()
      .withUser("UserA").password("{noop}UserA").roles("USER")
      .and()
      .withUser("UserB").password("{noop}UserB").roles("USER")
      .and()
      .withUser("UserC").password("{noop}UserC").roles("USER");
    return;
  }
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      // Disable CSRF protection
      .csrf().disable()
      // Set default configurations from Spring Security
      .authorizeRequests()
        .anyRequest().authenticated()
        .and()
      .formLogin()
        .and()
      .httpBasic();
    return;
  }

}