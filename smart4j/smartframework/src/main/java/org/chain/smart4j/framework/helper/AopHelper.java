package org.chain.smart4j.framework.helper;

import org.apache.commons.collections4.CollectionUtils;
import org.chain.smart4j.framework.annotation.Aspect;
import org.chain.smart4j.framework.proxy.AspectProxy;
import org.chain.smart4j.framework.proxy.Proxy;
import org.chain.smart4j.framework.proxy.ProxyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17032651
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class AopHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);

    static {
        try {
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
            for (Map.Entry<Class<?>, List<Proxy>> targetEntry : targetMap.entrySet()) {
                Class<?> targetClass = targetEntry.getKey();
                List<Proxy> proxyList = targetEntry.getValue();
                Object proxy = ProxyManager.createProxy(targetClass, proxyList);
                BeanHelper.setBean(targetClass, proxy);
            }
        } catch (Exception e) {
            LOGGER.error("aop failure", e);
        }
    }

    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception {
        Map<Class<?>, List<Proxy>> targetMap = new HashMap<Class<?>, List<Proxy>>();
        for (Map.Entry<Class<?>, Set<Class<?>>> proxyMapEntry : proxyMap.entrySet()) {
            final Class<?> proxyClass = proxyMapEntry.getKey();
            Set<Class<?>> targetClassSet = proxyMapEntry.getValue();
            for (Class<?> targetClass : targetClassSet) {
                if (targetMap.containsKey(targetClass)) {
                    targetMap.get(targetClass).add((Proxy) proxyClass.newInstance());
                } else {
                    targetMap.put(targetClass, new ArrayList<Proxy>() {{
                        add((Proxy) proxyClass.newInstance());
                    }});
                }
            }
        }
        return targetMap;
    }

    private static Map<Class<?>, Set<Class<?>>> createProxyMap() throws Exception {
        Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        if (CollectionUtils.isNotEmpty(proxyClassSet)) {
            for (Class<?> cls : proxyClassSet) {
                if (cls.isAnnotationPresent(Aspect.class)) {
                    Aspect aspect = cls.getAnnotation(Aspect.class);
                    Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                    proxyMap.put(cls, targetClassSet);
                }
            }
        }
        return proxyMap;
    }

    private static Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception {
        Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
        Class<? extends Annotation> annotation = aspect.value();
        if (!annotation.equals(Aspect.class)) {
            targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }
        return targetClassSet;
    }
}
