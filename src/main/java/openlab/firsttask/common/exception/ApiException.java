package openlab.firsttask.common.exception;

import lombok.Getter;
import openlab.firsttask.common.enums.ApiExceptionEnum;

@Getter
public final class ApiException extends RuntimeException {
    private final int code;
    private final String msg;
    private Exception exception;

    public ApiException(Exception e) {
        code = ApiExceptionEnum.INTERNAL_SERVER_ERROR.getCode();
        msg = ApiExceptionEnum.INTERNAL_SERVER_ERROR.getMsg();
        exception = e;
    }

    public ApiException(ApiExceptionEnum apiExceptionEnum) {
        code = apiExceptionEnum.getCode();
        msg = apiExceptionEnum.getMsg();
    }

    public ApiException(ApiExceptionEnum apiExceptionEnum, Exception e) {
        this(apiExceptionEnum);
        exception = e;
    }

    public ApiException(ApiExceptionEnum apiExceptionEnum, String additionalMessage) {
        code = apiExceptionEnum.getCode();
        msg = apiExceptionEnum.getMsg() + " " + additionalMessage;
    }

    public ApiException(ApiExceptionEnum apiExceptionEnum, String additionalMessage, Exception e) {
        this(apiExceptionEnum, additionalMessage);
        exception = e;
    }

}
