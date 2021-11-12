package app.hdj.datepick.global.config.security;

import app.hdj.datepick.global.config.security.filter.TokenAuthFilter;
import app.hdj.datepick.global.config.security.handler.AuthenticationExceptionHandler;
import app.hdj.datepick.global.config.security.handler.AuthorizationExceptionHandler;
import app.hdj.datepick.global.config.security.provider.FirebaseAuthProvider;
import app.hdj.datepick.global.config.security.util.FirebaseAuthTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
    public TokenAuthFilter tokenAuthenticationFilter() {
        // Firebase Token Util 세팅
        FirebaseAuthTokenUtil tokenUtil = new FirebaseAuthTokenUtil();
        return new TokenAuthFilter(tokenUtil);
    }

    @Bean
    public FirebaseAuthProvider firebaseAuthProvider() {
        return new FirebaseAuthProvider();
    }

    @Bean
    public AuthenticationExceptionHandler authenticationExceptionHandler() {
        return new AuthenticationExceptionHandler();
    }

    @Bean
    public AuthorizationExceptionHandler authorizationExceptionHandler() {
        return new AuthorizationExceptionHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Spring Security 설정
        http
                .cors().disable()   // TODO: 적용
                .csrf().disable()   // TODO: 적용
                .formLogin().disable()
                .httpBasic().disable()
                // API 인증
                .authorizeRequests()
                    // User
                    .antMatchers(HttpMethod.GET, "/v1/users/me").hasAuthority("USER")
                    .antMatchers(HttpMethod.POST, "/v1/users/unregister").hasAuthority("USER")
                    .antMatchers(HttpMethod.PATCH, "/v1/users/{\\d+}").hasAuthority("USER")
                    .antMatchers("/v1/users/*").permitAll()
                    // Diary
                    //.antMatchers("/v1/diary//{\\d+}").hasAuthority("USER")
                .anyRequest().permitAll()
                .and()
                // 예외 처리
                .exceptionHandling()
                    .authenticationEntryPoint(authenticationExceptionHandler())   // 인증 오류 처리
                    .accessDeniedHandler(authorizationExceptionHandler())         // 권한 오류 처리
                .and()
                // 세션 설정
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // JWT 토큰 인증 필터 추가
        http
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(firebaseAuthProvider());
    }

    @Override
    public void configure(WebSecurity web) {
        // Auth Filter 스킵 설정
        web.ignoring()
                .antMatchers(HttpMethod.POST, "/v1/users/register");
    }
}
