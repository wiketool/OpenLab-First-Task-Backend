package openlab.firsttask.common.utils;


import openlab.firsttask.common.enums.ApiExceptionEnum;
import openlab.firsttask.common.exception.ApiException;

public class AssertUtils {

    public static void isTrue(boolean expression, ApiExceptionEnum apiExceptionEnum){
        if(!expression){
            throw new ApiException(apiExceptionEnum);
        }
    }

    public static void isTrue(boolean expression, RuntimeException e){
        if(!expression){
            throw e;
        }
    }

    public static void notNull(Object object, ApiExceptionEnum apiExceptionEnum){
        if(object == null){
            throw new ApiException(apiExceptionEnum);
        }
    }

    public static void notNull(Object object, RuntimeException re){
        if(object == null){
            throw re;
        }
    }

}
