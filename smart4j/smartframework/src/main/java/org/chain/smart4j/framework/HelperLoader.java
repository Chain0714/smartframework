package org.chain.smart4j.framework;

import org.chain.smart4j.framework.helper.BeanHelper;
import org.chain.smart4j.framework.helper.ClassHelper;
import org.chain.smart4j.framework.helper.ControllerHelper;
import org.chain.smart4j.framework.helper.IocHelper;
import org.chain.smart4j.framework.utils.ClassUtil;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17032651
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class HelperLoader {
    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class, BeanHelper.class, IocHelper.class, ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName(), true);
        }
    }
}
