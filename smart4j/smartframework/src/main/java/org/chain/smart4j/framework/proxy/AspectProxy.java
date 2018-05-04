package org.chain.smart4j.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17032651
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public abstract class AspectProxy implements Proxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);

    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;
        Class<?> cls = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();
        begin();
        try {

            if (intercep(cls, method, params)) {
                before(cls, method, params);
                result = proxyChain.doProxyChain();
                after(cls, method, params);
            } else {
                result = proxyChain.doProxyChain();
            }

        } catch (Exception e) {
            LOGGER.error("proxy failure", e);
            error(cls, method, params, e);
            throw e;

        } finally {
            end();
        }
        return null;
    }

    public void error(Class<?> cls, Method method, Object[] params, Exception e) {
    }

    public void after(Class<?> cls, Method method, Object[] params) throws Throwable {
    }

    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
    }

    public boolean intercep(Class<?> cls, Method method, Object[] params) throws Throwable {
        return true;
    }

    public void end() {
    }

    public void begin() {
    }
}
