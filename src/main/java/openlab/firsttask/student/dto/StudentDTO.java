package openlab.firsttask.student.dto;

import lombok.*;
import openlab.firsttask.common.entity.BaseDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StudentDTO extends BaseDTO {
    private static final long serialVersionUID = -6501134274035655409L;

    private Long id;
    private String name;
    private Long studentId;
}
