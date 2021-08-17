package app.hdj.datepick.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class ApiLoggerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Before
        long start = System.currentTimeMillis();
        log.info("[REQUEST] {} - {}",
                request.getMethod(),
                request.getRequestURI());

        filterChain.doFilter(request, response);

        // After
        long end = System.currentTimeMillis();
        log.info("[RESPONSE] {} - {} {} - {}ms",
                request.getMethod(),
                request.getRequestURI(),
                response.getStatus(),
                (end - start));
    }

}
