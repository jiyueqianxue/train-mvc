package io.mine.ft.train.moshi.zerenlian.emp_1;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import io.mine.ft.train.moshi.zerenlian.emp_1.api.Handler;
import io.mine.ft.train.moshi.zerenlian.emp_1.zhujie.HandlerAnnotation;
import net.jodah.typetools.TypeResolver;

@Component
public class HandlerManager implements ApplicationContextAware {

    @SuppressWarnings("rawtypes")
	private final static ConcurrentHashMap<String, Handler> localhandlerMap = new ConcurrentHashMap<String, Handler>();
    
    
    private final static ConcurrentHashMap<String, Class<?>> localDtoClassMap = new ConcurrentHashMap<String, Class<?>>();
    
    private final static ConcurrentHashMap<String, HandlerInfo> landlerInfoMap = new ConcurrentHashMap<String, HandlerInfo>();

    public static int size() {
        return localhandlerMap.size();
    }

    public static Handler<?> getHandler(String handlerName) {
        Handler<?> handler = localhandlerMap.get(handlerName);
        return handler ;
    }

    public static HandlerInfo getHandlerInfo(String handlerName) {
        Handler<?> handler = localhandlerMap.get(handlerName);
        return  handler.getHandlerInfo() ;
    }


    public static void addLocalHandler(String handlerName, Handler<?> handler) {
        localhandlerMap.put(handlerName, handler);
    }

    
    @SuppressWarnings("rawtypes")
	public static Map<String, Handler> getAllLocalHandlers() {
        return localhandlerMap;
    }
    
    public static Map<String, HandlerInfo> getAllLandlerInfoMap() {
        return landlerInfoMap;
    }


    public static List<HandlerInfo> getLocalHandlers() {
        return localhandlerMap.entrySet().stream()
                .map(entry -> entry.getValue().getHandlerInfo())
                .collect(Collectors.toList());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    	
       
		Map<String, Handler> beans = applicationContext.getBeansOfType(Handler.class);
        localhandlerMap.putAll(beans);
        
        beans.forEach((k,v)->{
            Class<?>[] typeArgs = TypeResolver.resolveRawArguments(Handler.class, v.getClass());
            localDtoClassMap.put(k, typeArgs[0]);
        }) ;
        //获取handler信息
        Class<? extends Annotation> annotationClass = HandlerAnnotation.class;
        Map<String,Object> beanWhithAnnotation = applicationContext.getBeansWithAnnotation(annotationClass);
        Set<Map.Entry<String,Object>> entitySet = beanWhithAnnotation.entrySet();
        for (Map.Entry<String,Object> entry :entitySet){
            Class<? extends Object> clazz = entry.getValue().getClass();//获取bean对象
            HandlerAnnotation handlerAnnotation = AnnotationUtils.findAnnotation(clazz,HandlerAnnotation.class);
            HandlerInfo handlerInfo  = new HandlerInfo();
    		handlerInfo.setHandlerClass(clazz.getName());
    		handlerInfo.setDesc(handlerAnnotation.desc());
    		landlerInfoMap.put(clazz.getName(), handlerInfo);
        }
    }

    
    public static Class<?> getDtoClass(String handlerName) {
        Class<?> handlerDtoClass = localDtoClassMap.get(handlerName);
        return handlerDtoClass ;
    }
}
