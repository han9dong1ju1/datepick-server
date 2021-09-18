package app.hdj.datepick.global.config.security.model;

import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class FirebaseAuthenticationToken extends AbstractAuthenticationToken {

    // Firebase UID
    private final String principal;

    // Firebase Token
    private final FirebaseToken credentials;

    public FirebaseAuthenticationToken(String principal, FirebaseToken credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
    }

    public FirebaseAuthenticationToken(
            String principal,
            FirebaseToken credentials,
            Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
    }

    @Override
    public FirebaseToken getCredentials() {
        return credentials;
    }

    @Override
    public String getPrincipal() {
        return principal;
    }

}
