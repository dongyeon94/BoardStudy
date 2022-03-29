package com.example.Board.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .mvcMatchers("/", "/user/login", "/user/signup", "/login").permitAll()
            .anyRequest().authenticated()
            .and()
            .csrf().disable();

        http.formLogin().loginPage("/user/login")
                .loginProcessingUrl("/login")
                .usernameParameter("loginId")
                .defaultSuccessUrl("/")
                .failureUrl("/user/login?error=true");

        http.logout().logoutSuccessUrl("/").permitAll();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers("/assets/**")
            .antMatchers("/css/**")
            .antMatchers("/js/**")
            .antMatchers("/img/**")
            .antMatchers("/favicon.ico", "/resources/**", "/error")
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}