package openlab.firsttask.level.controller;

import openlab.firsttask.common.annoation.ApiResponse;
import openlab.firsttask.common.annoation.UserSession;
import openlab.firsttask.common.entity.UserSessionDTO;
import openlab.firsttask.level.dto.LevelResponseDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/level")
public class LevelController {

    @ApiResponse
    @PostMapping("/pass")
    LevelResponseDTO passLevel(@UserSession UserSessionDTO userSessionDTO){
        System.out.println(userSessionDTO);
        return LevelResponseDTO.builder().build();
    }

}
