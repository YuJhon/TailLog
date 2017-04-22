package com.jhon.rain.utils;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;



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

   
    public static <T> T getComponent(Class<T> componentClass)
    {
        return SpringContextUtil.getApplicationContext().getBean(componentClass);
    }
    
    
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> componentClass,String beanName)
    {
        Object obj = SpringContextUtil.getApplicationContext().getBean(beanName);
        return (T)obj;
    }
}
