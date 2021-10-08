package app.hdj.datepick.global.config.security;

import app.hdj.datepick.global.config.security.filter.FirebaseAuthFilter;
import app.hdj.datepick.global.config.security.util.FirebaseAuthTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public FirebaseAuthFilter tokenAuthenticationFilter() {
        // Firebase Token Service 세팅
        FirebaseAuthTokenUtil tokenUtil = new FirebaseAuthTokenUtil();
        return new FirebaseAuthFilter(tokenUtil);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()   // TODO: 적용
                .csrf().disable()   // TODO: 적용
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()
                    // User
                    .antMatchers("/v1/users/**").permitAll()
                    // Featured
                    .antMatchers("/v1/featured/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
