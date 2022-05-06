package app.hdj.datepick.domain.auth.infrastructure;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

public class AuthorizationExtractor {

    private static final String HEADER_KEY = "Authorization";
    private static final String ACCESS_TOKEN_TYPE = "Bearer";

    public static String extract(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaders(HEADER_KEY);
        while (headers.hasMoreElements()) {
            String header = headers.nextElement();
            if (header.toLowerCase().startsWith(ACCESS_TOKEN_TYPE.toLowerCase() + " ")) {
                String value = header.substring(ACCESS_TOKEN_TYPE.length()).trim();
                return value.isBlank() ? null : value;
            }
        }
        return null;
    }
}
