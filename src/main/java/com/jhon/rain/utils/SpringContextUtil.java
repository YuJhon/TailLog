package com.jhon.rain.utils;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * <p>spring上下文获取工具类 〈一句话功能简述〉 〈功能详细描述〉</p>
 * 
 * @author jiangyu
 * @version v1.0
 * @see SpringContextUtil
 * @since v1.0
 */
public class SpringContextUtil implements ApplicationContextAware
{

    private SpringContextUtil()
    {

    }

    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext)
        throws BeansException
    {
        // SpringContextUtil.applicationContext = applicationContext;
        setContext(applicationContext);
    }

    private static void setContext(ApplicationContext applicationContext)
    {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    /**
     * <p> 功能描述：获取bean对象</p>
     * @author jiangyu
     * @date 2016年3月17日 下午6:59:24
     * @param componentClass 要加载的bean类
     * @return T 自定义的类型
     * @since v1.0
     */
    public static <T> T getComponent(Class<T> componentClass)
    {
        return SpringContextUtil.getApplicationContext().getBean(componentClass);
    }
    
    /**
     * <p> 功能描述：获取bean</p>
     * @author  jiangyu
     * @date  2016年11月27日 下午3:24:18
     * @param componentClass 对应的bean的组件类 
     * @param beanName 对应的bean的名称 
     * @return 返回componentClass对应的类型组件实体
     * @version v1.0
     * @since V1.0
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> componentClass,String beanName)
    {
        Object obj = SpringContextUtil.getApplicationContext().getBean(beanName);
        return (T)obj;
    }
}
