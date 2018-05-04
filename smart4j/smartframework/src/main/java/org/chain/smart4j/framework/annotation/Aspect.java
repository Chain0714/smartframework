package org.chain.smart4j.framework.annotation;

import java.lang.annotation.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author 17032651
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    Class<? extends Annotation> value();
}
