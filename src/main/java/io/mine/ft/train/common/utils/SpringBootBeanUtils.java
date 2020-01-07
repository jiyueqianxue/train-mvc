package io.mine.ft.train.common.utils;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ConfigurableApplicationContext;

public class SpringBootBeanUtils {
	 /**
     * 主动向Spring容器中注册bean
     *
     * @param applicationContext Spring容器
     * @param name               BeanName
     * @param clazz              注册的bean的类性
     * @param args               构造方法的必要参数，顺序和类型要求和clazz中定义的一致
     * @param <T>
     * @return 返回注册到容器中的bean对象
     */
    @SuppressWarnings({ "hiding", "unchecked" })
	public static <T> T registerBean(ConfigurableApplicationContext applicationContext, String name, Class<T> clazz,
                                     Object... args) {
        if(applicationContext.containsBean(name)) {
            Object bean = applicationContext.getBean(name);
            if (bean.getClass().isAssignableFrom(clazz)) {
                return (T) bean;
            } else {
                throw new RuntimeException("BeanName 重复 " + name);
            }
        }
 
 
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        for (Object arg : args) {
            beanDefinitionBuilder.addConstructorArgValue(arg);
        }
        BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
 
        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) applicationContext.getBeanFactory();
        beanFactory.registerBeanDefinition(name, beanDefinition);
        return applicationContext.getBean(name, clazz);
    }
 
    public static void removeBean(ConfigurableApplicationContext applicationContext, Class<T> cls){
        String name = cls.getSimpleName();
        name = name.replaceFirst(name.substring(0,1), name.substring(0,1).toLowerCase());
        removeBean(applicationContext, name);
    }
 
    public static void removeBean(ConfigurableApplicationContext applicationContext, String beanName){
        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) applicationContext.getBeanFactory();
        beanFactory.removeBeanDefinition(beanName);
    }

}
