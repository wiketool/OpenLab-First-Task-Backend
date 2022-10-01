package openlab.firsttask.level.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import openlab.firsttask.common.entity.BaseDTO;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LevelPasswordResponseDTO extends BaseDTO {
    private static final long serialVersionUID = 9151769541645589187L;

    private Integer passed;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
}
