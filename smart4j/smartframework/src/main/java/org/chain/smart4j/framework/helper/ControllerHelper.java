package org.chain.smart4j.framework.helper;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.chain.smart4j.framework.annotation.Action;
import org.chain.smart4j.framework.bean.Handler;
import org.chain.smart4j.framework.bean.Request;

import javax.swing.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17032651
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public final class ControllerHelper {
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    //GET:/test
    private static final String URL_PATTERN = "\\w+:/\\w*";

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtils.isNotEmpty(controllerClassSet)) {
            for (Class<?> controllerClass : controllerClassSet) {
                Method[] controllerMethods = controllerClass.getDeclaredMethods();
                if (ArrayUtils.isNotEmpty(controllerMethods)) {
                    for (Method method : controllerMethods) {
                        if (method.isAnnotationPresent(Action.class)) {
                            Action action = method.getAnnotation(Action.class);
                            String requestMapping = action.value();
                            if (requestMapping.matches(URL_PATTERN)) {
                                String[] mappingArray = requestMapping.split(":");
                                ACTION_MAP.put(new Request(mappingArray[0], mappingArray[1]),
                                        new Handler(controllerClass, method));
                            }
                        }
                    }
                }
            }
        }
    }

    public static Handler getHandler(String requestMethod, String requestPath) {
        return ACTION_MAP.get(new Request(requestMethod, requestPath));
    }
}
