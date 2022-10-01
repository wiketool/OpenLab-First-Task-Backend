package openlab.firsttask.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import openlab.firsttask.common.interfaces.StatusCode;

@Getter
@AllArgsConstructor
public enum ApiExceptionEnum implements StatusCode {
    SUCCESS(1000, "成功"),
    INTERNAL_SERVER_ERROR(1001, "内部服务错误"),
    PAGE_NOT_FOUND(1002, "页面未找到"),
    UNSUPPORTED_HTTP_REQUEST_METHOD(1003, "不支持的HTTP方法"),
    MISSING_HEADER_IN_REQUEST(1004, "丢失必需的请求头"),
    DATABASE_ERROR(1005, "数据库错误"),
    REQUEST_PARAMETER_ERROR(1006, "请求参数错误"),
    REQUEST_HEADER_ERROR(1007, "请求头错误");

    final private int code;
    final private String msg;
}
