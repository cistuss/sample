package org.sample.common.config.security

import org.sample.common.config.security.rememberme.RedisPersistentTokenRepository
import org.sample.common.config.security.rememberme.SamplePersistentRememberMeServices
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler

@Configuration
@EnableWebSecurity
open class SampleWebSecurityConfig(
    private val userDetailsService: SampleUserDetailsService,
    private val authSuccessHandler: SampleAuthSuccessHandler,
    private val authFailureHandler: SampleAuthFailureHandler,
    private val tokenRepository: RedisPersistentTokenRepository
) : WebSecurityConfigurerAdapter() {

    @Bean
    open fun passwordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

    override fun configure(http: HttpSecurity) {
        http.userDetailsService(userDetailsService)
            .authorizeRequests()
            .antMatchers("/api/open/**").permitAll()
            .anyRequest().authenticated()

        http.logout()
            .logoutUrl("/api/logout")
            .logoutSuccessHandler(HttpStatusReturningLogoutSuccessHandler())
            .permitAll()

        http.exceptionHandling()
            .authenticationEntryPoint(SampleAuthEntryPoint())

//        http.rememberMe()
//            .key("sample")
//            .tokenValiditySeconds(60 * 60 * 24 * 30) // 30 days
//            .userDetailsService(userDetailsService)

        http.addFilterAt(
            prepareUsernamePasswordAuthFilter(),
            UsernamePasswordAuthenticationFilter::class.java
        )
        http.csrf().disable()
    }

    private fun prepareUsernamePasswordAuthFilter(): SampleUsernamePasswordAuthenticationFilter {
        return SampleUsernamePasswordAuthenticationFilter(
            authenticationManager(),
            authSuccessHandler,
            authFailureHandler,
            prepareRememberMeServices()
        )
    }

    private fun prepareRememberMeServices(): SamplePersistentRememberMeServices {
        return SamplePersistentRememberMeServices(
            "sample",
            userDetailsService,
            tokenRepository
        )
    }
}