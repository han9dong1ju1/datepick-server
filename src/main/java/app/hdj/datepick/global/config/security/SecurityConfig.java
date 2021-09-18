package app.hdj.datepick.global.config.security;

import app.hdj.datepick.global.config.security.filter.TokenAuthenticationFilter;
import app.hdj.datepick.global.config.security.filter.service.FirebaseTokenService;
import app.hdj.datepick.global.config.security.filter.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        // Firebase Token Service 세팅
        TokenService tokenService = new FirebaseTokenService();
        return new TokenAuthenticationFilter(tokenService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()   // TODO: 적용
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()
                    .antMatchers("/v1/featured/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
