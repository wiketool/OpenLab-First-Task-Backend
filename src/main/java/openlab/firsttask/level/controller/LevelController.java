package openlab.firsttask.level.controller;

import openlab.firsttask.common.annoation.ApiResponse;
import openlab.firsttask.common.annoation.UserSession;
import openlab.firsttask.common.entity.UserSessionDTO;
import openlab.firsttask.common.enums.ApiExceptionEnum;
import openlab.firsttask.common.utils.AssertUtils;
import openlab.firsttask.level.dto.response.LevelPasswordResponseDTO;
import openlab.firsttask.level.dto.response.LevelStateResponseDTO;
import openlab.firsttask.level.dto.response.PassLevelResponseDTO;
import openlab.firsttask.level.dto.response.VerifyLevelResponseDTO;
import openlab.firsttask.level.dto.resquest.LevelPasswordRequestDTO;
import openlab.firsttask.level.dto.resquest.PassLevelRequestDTO;
import openlab.firsttask.level.dto.resquest.VerifyLevelRequestDTO;
import openlab.firsttask.level.service.LevelService;
import openlab.firsttask.student.dto.StudentDTO;
import openlab.firsttask.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.text.MessageFormat;

@Controller
@RequestMapping("/level")
public class LevelController {

    @Autowired
    StudentService studentService;

    @Autowired
    LevelService levelService;

    /**
     * 通过关卡
     *
     * @param userSessionDTO 用户身份dto
     * @param requestDTO     通过关卡 请求dto
     * @return {@link PassLevelResponseDTO}
     */
    @ApiResponse
    @PostMapping("/pass")
    public PassLevelResponseDTO passLevel(@UserSession UserSessionDTO userSessionDTO,
                                          @RequestBody @Validated PassLevelRequestDTO requestDTO) {
        boolean isLevelIdValid = requestDTO.getLevelId() >= 0 && requestDTO.getLevelId() <= 10;
        AssertUtils.isTrue(isLevelIdValid, ApiExceptionEnum.REQUEST_PARAMETER_ERROR);

        StudentDTO studentDTO = StudentDTO.builder().name(userSessionDTO.getStudentName())
                                          .studentId(userSessionDTO.getStudentId()).build();
        Long studentPKId = studentService.selectOrInsertStudent(studentDTO);
        userSessionDTO.setStudentPKId(studentPKId);
        PassLevelResponseDTO responseDTO = levelService.selectOrInsertPassLevelInfo(requestDTO, studentPKId);
        return responseDTO;
    }

    @ApiResponse
    @PostMapping("/verify")
    public VerifyLevelResponseDTO verifyLevel(@UserSession UserSessionDTO userSessionDTO,
                                              @RequestBody @Validated VerifyLevelRequestDTO verifyLevelRequestDTO) {
        VerifyLevelResponseDTO responseDTO = VerifyLevelResponseDTO.builder().valid(0).build();
        studentService.selectStudentPKId(userSessionDTO.getStudentId())
                      .ifPresent(studentPKId -> {
                          responseDTO.setValid(levelService.verifyFlagLevel(verifyLevelRequestDTO, studentPKId));
                      });
        return responseDTO;
    }

    @ApiResponse
    @GetMapping("/ispassed")
    public LevelStateResponseDTO levelState(@UserSession UserSessionDTO userSessionDTO,
                                            @RequestParam @Min(0) Integer levelId) {
        LevelStateResponseDTO responseDTO = LevelStateResponseDTO.builder().passed(0).build();
        studentService.selectStudentPKId(userSessionDTO.getStudentId())
                      .ifPresent(studentPKId -> {
                          responseDTO.setPassed(levelService.queryIsPassedLevel(levelId, studentPKId));
                      });
        return responseDTO;
    }

    @ApiResponse
    @GetMapping("/5/check_password")
    public LevelPasswordResponseDTO level5CheckPassword(@UserSession UserSessionDTO userSessionDTO,
                                                        @RequestParam String password) {
        StudentDTO studentDTO = StudentDTO.builder().name(userSessionDTO.getStudentName())
                                          .studentId(userSessionDTO.getStudentId()).build();
        Long studentPKId = studentService.selectOrInsertStudent(studentDTO);
        LevelPasswordResponseDTO responseDTO = levelService.verifyLevelPassword(password, studentPKId);
        return responseDTO;
    }

    @ApiResponse
    @PostMapping("/6/check_password")
    public LevelPasswordResponseDTO level6CheckPassword(@UserSession UserSessionDTO userSessionDTO,
                                                        @RequestBody LevelPasswordRequestDTO requestDTO) {
        StudentDTO studentDTO = StudentDTO.builder().name(userSessionDTO.getStudentName())
                                          .studentId(userSessionDTO.getStudentId()).build();
        Long studentPKId = studentService.selectOrInsertStudent(studentDTO);
        LevelPasswordResponseDTO responseDTO = levelService.verifyLevelPassword(requestDTO.getPassword(), studentPKId);
        return responseDTO;
    }

}
