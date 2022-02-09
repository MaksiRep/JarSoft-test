package ru.maksirep.jarsoft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import ru.maksirep.jarsoft.filters.CredentialsAuthenticationFilter;
import ru.maksirep.jarsoft.filters.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        var corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("http://localhost:3000/");
        corsConfig.setAllowCredentials(true);

        http.cors();

        http.authorizeRequests()
                .antMatchers("/categories/**").hasRole("ADMIN")
                .antMatchers("/banners/**").hasRole("ADMIN")
                .and();

        http.formLogin().disable();
        http.csrf().disable();

        http.addFilter(new CredentialsAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new JwtAuthenticationFilter(userDetailsService()), CredentialsAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}