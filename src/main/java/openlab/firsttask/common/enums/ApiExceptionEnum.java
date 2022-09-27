package openlab.firsttask.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import openlab.firsttask.common.interfaces.StatusCode;

@Getter
@AllArgsConstructor
public enum ApiExceptionEnum implements StatusCode {
    SUCCESS(1000, "Success"),
    INTERNAL_SERVER_ERROR(1001, "Internal service error"),
    PAGE_NOT_FOUND(1002, "404 Page Not Found"),
    UNSUPPORTED_HTTP_REQUEST_METHOD(1003, "Unsupported HTTP method"),
    MISSING_HEADER_IN_REQUEST(1004, "Missing necessary header in request");

    final private int code;
    final private String msg;
}
