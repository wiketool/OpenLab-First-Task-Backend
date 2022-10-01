package openlab.firsttask.common.entity;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserSessionDTO extends BaseDTO{
    private static final long serialVersionUID = -436483780305811007L;
    Long studentId;

    String studentName;

    Long studentPKId;
}
