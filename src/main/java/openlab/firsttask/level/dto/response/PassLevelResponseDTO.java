package openlab.firsttask.level.dto.response;


import lombok.*;
import openlab.firsttask.common.entity.BaseDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PassLevelResponseDTO extends BaseDTO {
    private static final long serialVersionUID = -583971094157288719L;
    private Integer state;
    private String flag;
}
