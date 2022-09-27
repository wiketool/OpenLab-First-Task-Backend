package openlab.firsttask.common.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TimestampUtils {
    /**
     * @return 当前时间戳，秒，东八区
     */
    public static long now() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
    }
}
