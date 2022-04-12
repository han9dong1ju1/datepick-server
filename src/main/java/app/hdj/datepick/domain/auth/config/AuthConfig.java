package app.hdj.datepick.domain.auth.config;

import app.hdj.datepick.domain.auth.infrastructure.AuthInterceptor;
import app.hdj.datepick.domain.auth.infrastructure.AuthPrincipalArgumentResolver;
import app.hdj.datepick.domain.auth.infrastructure.AuthUuidArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class AuthConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;
    private final AuthPrincipalArgumentResolver authPrincipalArgumentResolver;
    private final AuthUuidArgumentResolver authUuidArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authPrincipalArgumentResolver);
        resolvers.add(authUuidArgumentResolver);
    }
}
