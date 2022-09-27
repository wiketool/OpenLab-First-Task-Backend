package openlab.firsttask.common.configuration;

import openlab.firsttask.common.utils.AssertUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ViewNameMethodReturnValueHandler;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ReturnValueHandlerConfiguration implements InitializingBean {

    RequestMappingHandlerAdapter adapter;

    ApiResponseReturnValueHandler apiResponseReturnValueHandler;

    @Autowired
    ReturnValueHandlerConfiguration(RequestMappingHandlerAdapter adapter,ApiResponseReturnValueHandler handler) {
        this.adapter = adapter;
        this.apiResponseReturnValueHandler = handler;
    }

    @Override
    public void afterPropertiesSet() {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
        AssertUtils.notNull(returnValueHandlers,new RuntimeException("List<HandlerMethodReturnValueHandler> is null"));
        ArrayList<HandlerMethodReturnValueHandler> handlers = new ArrayList<>(returnValueHandlers);
        for (int i = 0; i < handlers.size(); i++) {
            /*
                需要注意，在ViewNameMethodReturnValueHandler前面有个RequestResponseBodyMethodProcessor
                如果使用了@RequestBody或者@RestController的话，就会被RequestResponseBodyMethodProcessor处理，不会到自定义的returnValueHandler

                @RestController就是@ResponseBody和@Controller的结合，源码如下
                https://github.com/spring-projects/spring-framework/blob/main/spring-web/src/main/java/org/springframework/web/bind/annotation/RestController.java
            */
            if (handlers.get(i) instanceof ViewNameMethodReturnValueHandler) {
                handlers.add(i, apiResponseReturnValueHandler);
//                如果不break，添加后下一个 i 又是ViewNameMethodReturnValueHandler
                break;
            }
        }
        adapter.setReturnValueHandlers(handlers);
    }
}
