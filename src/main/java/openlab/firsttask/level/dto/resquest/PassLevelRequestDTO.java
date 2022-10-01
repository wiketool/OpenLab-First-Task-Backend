package openlab.firsttask.level.dto.resquest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassLevelRequestDTO {
    @NotNull
    @Min(0)
    private Integer levelId;

    @NotNull
    private String secret;
}
