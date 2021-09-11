package app.hdj.datepick.domain.featured.exception;

import app.hdj.datepick.global.error.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;

public class FeaturedNotFoundException extends CustomException {
    public FeaturedNotFoundException() {
        super(ErrorCode.FEATURED_NOT_FOUND);
    }
}
