package openlab.firsttask.common.entity;


import lombok.Getter;
import lombok.Setter;
import openlab.firsttask.common.enums.ApiExceptionEnum;
import openlab.firsttask.common.exception.ApiException;
import openlab.firsttask.common.utils.TimestampUtils;

import java.io.Serializable;

@Getter
@Setter
public final class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = -2479864152810494390L;

    private int code;
    private String msg;
    private long timestamp;
    private T data;

    /**
     * @return 成功且带有数据的相应
     */
    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<T>()
                .setCode(ApiExceptionEnum.SUCCESS.getCode())
                .setMsg(ApiExceptionEnum.SUCCESS.getMsg())
                .setTimestamp(TimestampUtils.now())
                .setData(data);
    }

    /**
     * @return 成功但没有数据的相应
     */
    public static <T> ResultVO<T> success() {
        return new ResultVO<T>()
                .setCode(ApiExceptionEnum.SUCCESS.getCode())
                .setMsg(ApiExceptionEnum.SUCCESS.getMsg())
                .setTimestamp(TimestampUtils.now())
                .setData(null);
    }

    /**
     * @return 默认错误相应
     */
    public static <T> ResultVO<T> error() {
        return new ResultVO<T>()
                .setCode(ApiExceptionEnum.INTERNAL_SERVER_ERROR.getCode())
                .setMsg(ApiExceptionEnum.INTERNAL_SERVER_ERROR.getMsg())
                .setTimestamp(TimestampUtils.now())
                .setData(null);
    }

    /**
     * @return 自定义信息的错误响应
     */
    public static <T> ResultVO<T> error(String msg) {
        return new ResultVO<T>()
                .setCode(ApiExceptionEnum.INTERNAL_SERVER_ERROR.getCode())
                .setMsg(msg)
                .setTimestamp(TimestampUtils.now())
                .setData(null);
    }

    /**
     * @return 统一API错误响应
     */
    public static <T> ResultVO<T> error(ApiException ex) {
        return new ResultVO<T>()
                .setCode(ex.getCode())
                .setMsg(ex.getMsg())
                .setTimestamp(TimestampUtils.now())
                .setData(null);
    }

    /**
     * @return 统一API错误响应
     */
    public static <T> ResultVO<T> error(ApiExceptionEnum ex) {
        return new ResultVO<T>()
                .setCode(ex.getCode())
                .setMsg(ex.getMsg())
                .setTimestamp(TimestampUtils.now())
                .setData(null);
    }


    public ResultVO<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public ResultVO<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResultVO<T> setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public ResultVO<T> setData(T data) {
        this.data = data;
        return this;
    }
}
