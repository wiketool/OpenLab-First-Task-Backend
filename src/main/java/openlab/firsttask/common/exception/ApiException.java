package openlab.firsttask.common.exception;

import lombok.Getter;
import openlab.firsttask.common.enums.ApiExceptionEnum;
import org.springframework.http.HttpStatus;

@Getter
public final class ApiException extends RuntimeException {
    private final int code;
    private final String msg;

    private final int httpStatus;

    private Exception exception;

    public ApiException(Exception e) {
        code = ApiExceptionEnum.INTERNAL_SERVER_ERROR.getCode();
        msg = ApiExceptionEnum.INTERNAL_SERVER_ERROR.getMsg();
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
        exception = e;
    }

    public ApiException(ApiExceptionEnum apiExceptionEnum) {
        code = apiExceptionEnum.getCode();
        msg = apiExceptionEnum.getMsg();
        httpStatus = apiExceptionEnum.getHttpStatus();
    }

    public ApiException(ApiExceptionEnum apiExceptionEnum, Exception e) {
        this(apiExceptionEnum);
        exception = e;
    }

    public ApiException(ApiExceptionEnum apiExceptionEnum, String message) {
        code = apiExceptionEnum.getCode();
        msg = message;
        httpStatus = apiExceptionEnum.getHttpStatus();
    }

    public ApiException(ApiExceptionEnum apiExceptionEnum, String message, Exception e) {
        this(apiExceptionEnum, message);
        exception = e;
    }
}
