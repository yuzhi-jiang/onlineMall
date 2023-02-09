package com.yefeng.springtest.config;

import com.yefeng.springtest.Client.handler.FailHandler;
import com.yefeng.springtest.Client.handler.SuccessHandler;
import com.yefeng.springtest.Client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService service;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private SuccessHandler successHandler;
    @Autowired
    private FailHandler failHandler;


    private final String USERNAME="name";
    private final String PASSWORD="password";


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(service);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);

        return daoAuthenticationProvider;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);

    }
    private String[] loadExcludePath() {
        return new String[]{
                "/",
                "/static/**",
                "/templates/**",
                "/img/**",
                "/js/**",
                "/css/**",
                "/lib/**"
        };
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .httpBasic().disable()
                .authorizeRequests()
//                .antMatchers("/**")
//                .permitAll();
                .antMatchers("/admin/login","/publicHtml/**","/daohang","/foot","/productlist","/orderConfirm","/product/**","/productdetail","/html2","/login","/index","/register","/user/login","/user/register","/css/**","/js/**","/img/**","/bootstrap-select/**","/**.js","/**.css")
                .permitAll()
//                .antMatchers("/admin/**")
//                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/user/login")
                .usernameParameter(USERNAME)
                .passwordParameter(PASSWORD)
                .successHandler(successHandler)
                .failureHandler(failHandler);
    }
}
