package openlab.firsttask.common.configuration;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Supplier;

@Component
public class CustomMybatisPlusMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "timestampCreate", Instant.class, Instant.now()); // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "timestampModify", Instant.class, Instant.now()); // 起始版本 3.3.0(推荐)
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "timestampModify", Instant.class, Instant.now()); // 起始版本 3.3.0(推荐)
    }


    /***
     * Mybatis Plus默认提供的注入策略都是如果有值则不注入，所以重载该策略
     * 重载后为无论有没有值都注入
     */
    @Override
    public MetaObjectHandler strictFillStrategy(MetaObject metaObject, String fieldName, Supplier<?> fieldVal) {
        Object obj = fieldVal.get();
        if (Objects.nonNull(obj)) {
            metaObject.setValue(fieldName, obj);
        }
        return this;
    }
}