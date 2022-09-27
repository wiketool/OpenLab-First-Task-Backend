package openlab.firsttask.level.dto;


import lombok.*;
import openlab.firsttask.common.entity.BaseDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LevelResponseDTO extends BaseDTO {

    private static final long serialVersionUID = -583971094157288719L;
    private String state;
    private String flag;
}
