package com.musinsa.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfiguration {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http.cors { config ->
            config.configurationSource(
                UrlBasedCorsConfigurationSource().also { cors ->
                    CorsConfiguration().apply {
                        allowedOrigins = listOf("*")
                        allowedMethods = listOf("POST", "PUT", "DELETE", "GET", "OPTIONS", "HEAD")
                        allowedHeaders = listOf(
                            "Authorization",
                            "Content-Type",
                            "X-Requested-With",
                            "Accept",
                            "Origin",
                            "Access-Control-Request-Method",
                            "Access-Control-Request-Headers",
                            "Access-Control-Allow-Origin",
                            "Access-Control-Allow-Credentials",
                            "Cookie"
                        )
                        exposedHeaders = listOf(
                            "Access-Control-Allow-Origin",
                            "Access-Control-Allow-Credentials",
                            "Authorization",
                            "Content-Disposition",
                            "Set-Cookie"
                        )
                        maxAge = 3600
                        allowCredentials = true
                        cors.registerCorsConfiguration("/**", this)
                    }
                }
            )
        }
            .csrf { csrf -> csrf.disable() }
            .sessionManagement { sessionManagement ->
                // Always -> spring security session fix
                // If_Required -> spring security think required
                // Never -> spring security not manage session
                // Stateless -> when not use session(ex: JWT)
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .anyRequest().permitAll()
            }
//            .addFilterBefore(
//                OnceJwtAuthorizationFilter(accessTokenKey = accessTokenKey, tokenProvider = tokenProvider),
//                UsernamePasswordAuthenticationFilter::class.java
//            )
            .build()
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
