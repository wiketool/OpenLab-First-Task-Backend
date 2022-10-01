package openlab.firsttask.common.configuration;


import openlab.firsttask.common.entity.ResultVO;
import openlab.firsttask.common.enums.ApiExceptionEnum;
import openlab.firsttask.common.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("rawtypes")
@ControllerAdvice
public class GlobalExceptionHandlerConfiguration {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerConfiguration.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ResultVO> exceptionHandler(Exception e) {
        logException(e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(ResultVO.error());
    }

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ResultVO> apiExceptionHandler(ApiException e) {
        logException(e);
        return ResponseEntity.status(e.getHttpStatus())
                             .body(ResultVO.error(e));
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseEntity<ResultVO> noHandlerFoundExceptionHandler(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(ResultVO.error(ApiExceptionEnum.PAGE_NOT_FOUND));
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResultVO> httpRequestMethodNotSupportedExceptionHandler(Exception e,
                                                                                  HttpServletResponse httpServletResponse) {
        HttpRequestMethodNotSupportedException ex = (HttpRequestMethodNotSupportedException) e;
        HttpHeaders allowMethods = new HttpHeaders();
        allowMethods.setAllow(ex.getSupportedHttpMethods());

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                             .headers(allowMethods)
                             .body(ResultVO.error(ApiExceptionEnum.UNSUPPORTED_HTTP_REQUEST_METHOD));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<ResultVO> methodArgumentNotValidExceptionHandler(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(ResultVO.error((ApiExceptionEnum.REQUEST_PARAMETER_ERROR)));
    }

    private void logException(Exception e) {
        StackTraceElement stackTraceElement = e.getStackTrace()[0];
        String className = stackTraceElement.getClassName();
        String fileName = stackTraceElement.getFileName();
        int lineNumber = stackTraceElement.getLineNumber();
        String methodName = stackTraceElement.getMethodName();

        logger.error("抛出异常，位于类名:{},文件名:{},行数:{},办法名:{}", className, fileName, lineNumber, methodName);
    }
}
