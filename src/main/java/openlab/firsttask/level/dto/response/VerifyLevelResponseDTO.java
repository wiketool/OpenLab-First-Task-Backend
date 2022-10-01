package openlab.firsttask.level.dto.response;

import lombok.*;
import openlab.firsttask.common.entity.BaseDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class VerifyLevelResponseDTO extends BaseDTO {

    private static final long serialVersionUID = -3592562656738607350L;

    private Integer valid;
}
