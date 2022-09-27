package openlab.firsttask.common.configuration;


import openlab.firsttask.common.entity.ResultVO;
import openlab.firsttask.common.enums.ApiExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@SuppressWarnings("rawtypes")
@ControllerAdvice
public class GlobalExceptionHandlerConfiguration {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerConfiguration.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ResultVO> exceptionHandler(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(ResultVO.error());
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseEntity<ResultVO> noHandlerFoundExceptionHandler(Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(ResultVO.error(ApiExceptionEnum.PAGE_NOT_FOUND));
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class )
    public ResponseEntity<ResultVO> httpRequestMethodNotSupportedExceptionHandler(Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(ResultVO.error(ApiExceptionEnum.UNSUPPORTED_HTTP_REQUEST_METHOD));
    }
}
