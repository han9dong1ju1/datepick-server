package app.hdj.datepick.global.error;

import app.hdj.datepick.global.common.dto.BaseResponseDto;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CommonErrorController extends AbstractErrorController {

    public CommonErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping
    public ResponseEntity<BaseResponseDto<Object>> error(HttpServletRequest request) {
        return new ResponseEntity<>(new BaseResponseDto<>("Wrong path", null, null), getStatus(request));
    }

}
