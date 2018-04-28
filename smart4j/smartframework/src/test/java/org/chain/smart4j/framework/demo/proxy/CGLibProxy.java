package org.chain.smart4j.framework.demo.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17032651
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class CGLibProxy implements MethodInterceptor {

    private static CGLibProxy instance;

    private CGLibProxy() {
    }

    public static CGLibProxy getInstance() {
        if (null == instance) {
            synchronized (new byte[0]) {
                if (null == instance) {
                    instance = new CGLibProxy();
                }
            }
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("CGLib invoke method...");
        return methodProxy.invokeSuper(o, objects);
    }
}
