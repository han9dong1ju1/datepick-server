package app.hdj.datepick.global.config.logger;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Profile({"local", "dev"})
@Component
public class ApiLoggerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {
        // Before
        long start = System.currentTimeMillis();
        log.info("[REQUEST] {} - {}", request.getMethod(), request.getRequestURI());

        filterChain.doFilter(request, response);

        // After
        long end = System.currentTimeMillis();
        log.info("[RESPONSE] {} - {} {} - {}ms", request.getMethod(), request.getRequestURI(),
                 response.getStatus(), (end - start));
    }
}
