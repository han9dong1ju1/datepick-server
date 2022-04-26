package app.hdj.datepick.global.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalTime;

public class ViewUtil {

    public static boolean getAlreadyViewed(String cookieValue, Long id) {
        return cookieValue.contains("/" + id + "/");
    }

    public static void setViewCookie(boolean alreadyViewed, String cookieName, String cookieValue, Long id, HttpServletResponse response) {
        if (alreadyViewed) {
            return;
        }
        cookieValue = cookieValue.concat(id + "/");
        response.addCookie(generateCookie(cookieName, cookieValue));
    }

    private static Cookie generateCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        Duration duration = Duration.between(LocalTime.now(), LocalTime.MAX);
        cookie.setMaxAge((int) duration.toSeconds());
        cookie.setHttpOnly(true);
        return cookie;
    }

}
