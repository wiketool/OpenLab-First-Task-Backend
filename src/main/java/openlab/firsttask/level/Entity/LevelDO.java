package openlab.firsttask.level.Entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import openlab.firsttask.common.entity.BaseDO;
import openlab.firsttask.level.fields.LevelDOFields;

import java.time.Instant;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@TableName(value = LevelDOFields.TABLE_NAME)
public class LevelDO extends BaseDO {
    private static final long serialVersionUID = 7738437760206205608L;

    @TableId(value = LevelDOFields.ID, type = IdType.AUTO)
    private Long id;

    @TableField(value = LevelDOFields.TIMESTAMP_CREATE, fill = FieldFill.INSERT)
    private Instant timestampCreate;

    @TableField(value = LevelDOFields.TIMESTAMP_MODIFY, fill = FieldFill.INSERT_UPDATE)
    private Instant timestampModify;

    @TableField(LevelDOFields.DELETED)
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;

    @Version
    @TableField(LevelDOFields.VERSION)
    private Integer version;

    /**
     * 学生主键Id
     */
    @TableField(LevelDOFields.STUDENT_PK_ID)
    private Long studentPKId;

    @TableField(LevelDOFields.PASSED)
    private Integer passed;

    @TableField(LevelDOFields.LEVEL_ID)
    private Integer levelId;

    @TableField(LevelDOFields.FLAG)
    private String flag;

    @TableField(LevelDOFields.SCORE)
    private Integer score;
}
