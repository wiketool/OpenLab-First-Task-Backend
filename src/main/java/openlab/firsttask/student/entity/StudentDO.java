package openlab.firsttask.student.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import openlab.firsttask.common.entity.BaseDO;
import openlab.firsttask.student.fields.StudentDOFields;

import java.time.Instant;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@TableName(StudentDOFields.TABLE_NAME)
public class StudentDO extends BaseDO {

    private static final long serialVersionUID = 2825232172364604483L;

    @TableId(value = StudentDOFields.ID, type = IdType.AUTO)
    private Long id;

    @TableField(value = StudentDOFields.TIMESTAMP_CREATE, fill = FieldFill.INSERT)
    private Instant timestampCreate;

    @TableField(value = StudentDOFields.TIMESTAMP_MODIFY, fill = FieldFill.INSERT_UPDATE)
    private Instant timestampModify;

    @TableField(StudentDOFields.DELETED)
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;

    @Version
    @TableField(StudentDOFields.VERSION)
    private Integer version;

    @TableField(StudentDOFields.STUDENT_ID)
    private Long studentId;

    @TableField(StudentDOFields.NAME)
    private String name;

    @TableField(StudentDOFields.QQ)
    private Long qq;

    @TableField(StudentDOFields.PHONE)
    private Long phone;

}
