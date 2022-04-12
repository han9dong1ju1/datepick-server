package app.hdj.datepick.domain.auth.infrastructure;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class AuthorizationExtractor {

    private static final String HEADER_KEY = "Authorization";
    private static final String ACCESS_TOKEN_TYPE = "Bearer";

    public static String extract(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaders(HEADER_KEY);
        while (headers.hasMoreElements()) {
            String header = headers.nextElement();
            if (header.toLowerCase().startsWith(ACCESS_TOKEN_TYPE.toLowerCase())) {
                return header.substring(ACCESS_TOKEN_TYPE.length()).trim();
            }
        }
        return null;
    }

}
