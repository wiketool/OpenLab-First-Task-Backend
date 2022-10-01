package openlab.firsttask.level.dto.response;

import lombok.*;
import openlab.firsttask.common.entity.BaseDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LevelStateResponseDTO extends BaseDTO {
    private static final long serialVersionUID = 3024472866358213833L;

    private Integer passed;
}
