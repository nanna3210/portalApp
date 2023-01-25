package com.nanna.portal.config;

import com.nanna.portal.constant.SecurityConstant;
import com.nanna.portal.filter.JwtAccessDeniedHandler;
import com.nanna.portal.filter.JwtAuthenticationEntryPoint;
import com.nanna.portal.filter.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
    
        private JwtAuthorizationFilter   jwtAuthorizationFilter;
        private JwtAccessDeniedHandler jwtAccessDeniedHandler;
        private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
        private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    
    @Autowired
    public SecurityConfiguration ( JwtAuthorizationFilter jwtAuthorizationFilter ,
            JwtAccessDeniedHandler jwtAccessDeniedHandler , JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint ,
            @Qualifier("userDetailsService") UserDetailsService userDetailsService , BCryptPasswordEncoder bCryptPasswordEncoder ) {
        this.jwtAuthorizationFilter      = jwtAuthorizationFilter;
        this.jwtAccessDeniedHandler      = jwtAccessDeniedHandler;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.userDetailsService          = userDetailsService;
        this.bCryptPasswordEncoder       = bCryptPasswordEncoder;
    }
    
    @Bean
    public AuthenticationManager authenticationManagerbean( AuthenticationConfiguration configuration ) throws Exception {
        return configuration.getAuthenticationManager ();
    }
    
    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider () {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider ();
        daoAuthenticationProvider.setUserDetailsService ( userDetailsService );
        daoAuthenticationProvider.setPasswordEncoder ( bCryptPasswordEncoder );
        return  daoAuthenticationProvider;
    }
    
    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
        http.csrf ().disable ().cors ()
                .and ().sessionManagement ().sessionCreationPolicy ( SessionCreationPolicy.STATELESS )
                .and ()
                .authorizeHttpRequests ()
                .requestMatchers ( SecurityConstant.PUBLIC_URLS )
                .permitAll ()
                .anyRequest ()
                .authenticated ()
                .and ()
                .exceptionHandling ()
                .accessDeniedHandler ( jwtAccessDeniedHandler )
                .authenticationEntryPoint ( jwtAuthenticationEntryPoint )
                .and ()
                .addFilterBefore ( jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class );
                
            
        
        return http.build ();
    }
    
    
    
    
}
