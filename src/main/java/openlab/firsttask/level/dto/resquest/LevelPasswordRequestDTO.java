package openlab.firsttask.level.dto.resquest;

import lombok.*;
import openlab.firsttask.common.entity.BaseDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LevelPasswordRequestDTO extends BaseDTO {
    private static final long serialVersionUID = -5008592914792191251L;

    private String password;
}
