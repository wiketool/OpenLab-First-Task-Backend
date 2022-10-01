package openlab.firsttask.level.dto.resquest;

import lombok.*;
import openlab.firsttask.common.entity.BaseDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class VerifyLevelRequestDTO extends BaseDTO {

    private static final long serialVersionUID = 927172670815597497L;

    @NotNull
    @Min(0)
    private Integer levelId;

    @NotNull
    private String flag;
}
