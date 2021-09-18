package app.hdj.datepick.global.config.security.filter.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {

    /**
     * 1. 토큰의 지정된 형식에 따라 요청의 헤더를 파싱
     * 2. 토큰을 검증하고 해당 토큰으로 생성한 Authentication 객체를 반환
     */
    Authentication getAuthentication(HttpServletRequest request);

}
