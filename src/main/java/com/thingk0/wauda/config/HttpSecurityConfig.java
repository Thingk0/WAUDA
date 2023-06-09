package com.thingk0.wauda.config;

import com.thingk0.wauda.config.custom.LoginFilter;
import com.thingk0.wauda.handler.CustomAuthenticationFailureHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Order(101)
@Configuration
@RequiredArgsConstructor
public class HttpSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .formLogin()
                    .loginPage("/members/login").permitAll()
                    .loginProcessingUrl("/members/login/process").permitAll()
                        .usernameParameter("email")
                        .passwordParameter("password")
                    .defaultSuccessUrl("/", false)
                    .failureHandler(customAuthenticationFailureHandler)
                    .and()
                .addFilterBefore(new LoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .and()
                .authorizeRequests()
                    .antMatchers("/", "/members/signup").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .httpBasic()
                    .and()
                .csrf().disable();
    }
}
