package openlab.firsttask.common.configuration;

import openlab.firsttask.common.annoation.UserSession;
import openlab.firsttask.common.entity.UserSessionDTO;
import openlab.firsttask.common.enums.ApiExceptionEnum;
import openlab.firsttask.common.exception.ApiException;
import openlab.firsttask.common.fields.RequestHeaderFields;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class UserSessionArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return (parameter.hasParameterAnnotation(UserSession.class))
                && (UserSessionDTO.class.isAssignableFrom(parameter.getParameterType()));
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String studentName = Optional.ofNullable(request.getHeader(RequestHeaderFields.STUDENT_NAME_HEADER_FIELD))
                                     .orElse("");
        Long studentId = Long.parseLong(Optional.ofNullable(request.getHeader(RequestHeaderFields.STUDENT_ID_HEADER_FIELD))
                                              .orElse("0"));
        UserSessionDTO userSessionDTO = UserSessionDTO.builder()
                                                      .studentId(studentId)
                                                      .studentName(studentName)
                                                      .build();

        return userSessionDTO;
    }
}
