package app.hdj.datepick.global.config.security.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
public class TokenUser implements Serializable {

    private final Long id;

    private final String uid;

}
