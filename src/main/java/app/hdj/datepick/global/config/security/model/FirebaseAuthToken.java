package app.hdj.datepick.global.config.security.model;

import com.google.firebase.auth.FirebaseToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class FirebaseAuthToken implements Authentication {

    // Custom User Details
    private final CustomUserDetails user;

    // Bearer Token String
    private final String token;

    // Authorities
    private final Collection<GrantedAuthority> authorities;

    // Is Authenticated Flag
    private boolean authenticated = false;

    public FirebaseAuthToken(String token) {
        this.user = null;
        this.token = token;
        this.authorities = null;
    }

    public FirebaseAuthToken(
            CustomUserDetails user,
            String token,
            Collection<GrantedAuthority> authorities) {
        this.user = user;
        this.token = token;
        this.authorities = authorities;
        this.authenticated = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.user;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return null;
    }
}
