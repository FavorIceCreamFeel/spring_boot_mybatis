package com.smxr.config;

import com.smxr.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * @author LZY
 * @date 2019/8/26 20:24
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private StudentService studentService;
    @Autowired
    private SessionRegistry sessionRegistry;

    /**
     * 制定权限规则
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/favicon.ico","/css/**","/js/**","/img/**","/login/**","/templates/err.ftl").permitAll()
                .antMatchers("/Home/**").hasAuthority("SSR")
                .and()
                .formLogin().loginPage("/login/login").successForwardUrl("/login/index").failureForwardUrl("/login/login")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login/login")
                .and()
                .rememberMe().rememberMeParameter("remebe")
                .and()
                .exceptionHandling().accessDeniedPage("/login/login")//权限不足时跳转
                .and()
                .csrf().disable()
                .sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry).expiredUrl("/login/login");
    }

    /**
     * 认证权限加密
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(studentService).passwordEncoder(passwordEncoder());
    }

    /**
     * 添加内置的session监听器
     * @return
     */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher(){
        return new HttpSessionEventPublisher();
    }
    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }
    /**
     * 重写PasswordEncoder  接口中的方法，实例化加密策略
     * @return 返回 BCrypt 加密策略
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
