package app.hdj.datepick.global.config.security.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserAuthentication extends AbstractAuthenticationToken {

    // 인증된 유저의 ID
    private final Long userId;

    public UserAuthentication(
            Long userId,
            Collection<GrantedAuthority> authorities) {
        super(authorities);
        this.userId = userId;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Long getPrincipal() {
        return this.userId;
    }

    @Override
    public boolean isAuthenticated() {
        return userId != null;
    }

}
