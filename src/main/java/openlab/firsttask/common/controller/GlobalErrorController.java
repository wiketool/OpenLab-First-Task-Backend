package openlab.firsttask.common.controller;

import openlab.firsttask.common.annoation.ApiResponse;
import openlab.firsttask.common.entity.ResultVO;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@ApiResponse
@Controller
@RequestMapping({"${server.error.path:${error.path:/error}}"})
public class GlobalErrorController implements ErrorController {

    @RequestMapping
    public ResultVO<?> error() {
        return ResultVO.error();
    }
}
