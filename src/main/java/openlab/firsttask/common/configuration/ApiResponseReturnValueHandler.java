package openlab.firsttask.common.configuration;


import openlab.firsttask.common.annoation.ApiResponse;
import openlab.firsttask.common.entity.ResultVO;
import openlab.firsttask.common.utils.AssertUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.Objects;

@Component
public class ApiResponseReturnValueHandler implements HandlerMethodReturnValueHandler {

    private final RequestResponseBodyMethodProcessor processor;

    ApiResponseReturnValueHandler(ApplicationContext applicationContext) {
        RequestMappingHandlerAdapter adapter = applicationContext.getBean(RequestMappingHandlerAdapter.class);
        processor = (RequestResponseBodyMethodProcessor)
                Objects.requireNonNull(adapter.getReturnValueHandlers())
                       .stream()
                       .filter(handler -> handler instanceof RequestResponseBodyMethodProcessor)
                       .findFirst()
                       .orElseThrow(()->{return new RuntimeException("RequestResponseBodyMethodProcessor NPE in ApiResponseReturnValueHandler!");});
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        /*
          returnType的类是HandlerMethod.ReturnValueMethodParameter
          通过调用getContainingClass获取到包含该方法的类
          https://time.geekbang.org/course/detail/100042601-243259
         */
        Class<?> containingClass = returnType.getContainingClass();
        boolean isClassAnnotated = AnnotatedElementUtils.hasAnnotation(containingClass, ApiResponse.class);
        boolean isMethodAnnotated = returnType.hasMethodAnnotation(ApiResponse.class);
        return isClassAnnotated || isMethodAnnotated;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        AssertUtils.notNull(processor, new RuntimeException("RequestResponseBodyMethodProcessor NPE in ApiResponseReturnValueHandler!"));
        if (returnValue instanceof ResultVO<?>) {
            processor.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
        } else {
            processor.handleReturnValue(ResultVO.success(returnValue), returnType, mavContainer, webRequest);
        }
    }
}
