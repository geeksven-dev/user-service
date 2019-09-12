package com.geeksven.userservice.configuration

import com.geeksven.userservice.configure
import com.geeksven.userservice.service.UserService
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client
import javax.sql.DataSource

@Configuration
@EnableOAuth2Client
class WebSecurityConfiguration(private val dataSource: DataSource,
                               private val userService: UserService) : WebSecurityConfigurerAdapter() {

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager = super.authenticationManagerBean()

    override fun configure(auth: AuthenticationManagerBuilder) = configure(auth) {
        userDetailsService(userService).passwordEncoder(BCryptPasswordEncoder())
        jdbcAuthentication().dataSource(dataSource)
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(http: HttpSecurity) = configure(http) {
        authorizeRequests()
                    .requestMatchers(EndpointRequest.to("info")).permitAll()
                .antMatchers("/",
                        "/login**",
                        "/assets/**",
                        "/.well-known/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login").permitAll()
    }
}