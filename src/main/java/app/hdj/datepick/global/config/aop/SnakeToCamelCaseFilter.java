package app.hdj.datepick.global.config.aop;

import com.google.common.base.CaseFormat;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class SnakeToCamelCaseFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {
        final Map<String, String[]> parameters = new ConcurrentHashMap<>();

        // 파라미터의 키 값을 snake_case에서 camelCase 변환 후 맵에 값을 가지고 있음
        for (String param : request.getParameterMap().keySet()) {
            String camelCaseParam = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, param);

            parameters.put(camelCaseParam, request.getParameterValues(param));
            // parameters.put(param, request.getParameterValues(param));
        }

        // 필터체인을 이용하여, request에 해당 값을 추가하여 반환
        filterChain.doFilter(new HttpServletRequestWrapper(request) {

            @Override
            public String getParameter(String name) {
                return parameters.containsKey(name) ? parameters.get(name)[0] : null;
            }

            @Override
            public Enumeration<String> getParameterNames() {
                return Collections.enumeration(parameters.keySet());
            }

            @Override
            public String[] getParameterValues(String name) {
                return parameters.get(name);
            }

            @Override
            public Map<String, String[]> getParameterMap() {
                return parameters;
            }

        }, response);
    }
}
