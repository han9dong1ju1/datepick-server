package app.hdj.datepick.global.config.security;

import app.hdj.datepick.global.config.security.filter.TokenAuthenticationFilter;
import app.hdj.datepick.global.config.security.handler.AuthenticationExceptionHandler;
import app.hdj.datepick.global.config.security.handler.AuthorizationExceptionHandler;
import app.hdj.datepick.global.config.security.util.FirebaseTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()   // TODO: 적용
                .csrf().disable()   // TODO: 적용
                .formLogin().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // API 인증 설정
                .and()
                .authorizeRequests()
                    // User
                    .antMatchers("/v1/users/me/**").hasAuthority("USER")
                    .antMatchers(HttpMethod.GET, "/v1/users/{\\d+}").permitAll()
                    .antMatchers(HttpMethod.POST, "/v1/users/register").permitAll()
                    .antMatchers("/v1/users/**").hasAuthority("USER")
                    // Course
                    .antMatchers(HttpMethod.POST, "/v1/courses").hasAuthority("USER")
                    .antMatchers(HttpMethod.GET, "/v1/courses/me").hasAuthority("USER")
                    .antMatchers(HttpMethod.GET, "/v1/courses/picked").hasAuthority("USER")
                    .antMatchers(HttpMethod.GET, "/v1/courses/{\\d+}").permitAll()
                    .antMatchers("/v1/courses/{\\d+}").hasAuthority("USER")
                    .antMatchers("/v1/courses/{\\d+}/**").hasAuthority("USER")

                    .anyRequest().permitAll()

                // 예외 처리
                .and()
                .exceptionHandling()
                    .authenticationEntryPoint(new AuthenticationExceptionHandler())   // 인증 오류 처리
                    .accessDeniedHandler(new AuthorizationExceptionHandler())         // 권한 오류 처리

                // JWT 토큰 인증 필터 추가
                .and()
                .addFilterBefore(new TokenAuthenticationFilter(new FirebaseTokenUtil()), UsernamePasswordAuthenticationFilter.class);
    }

}
