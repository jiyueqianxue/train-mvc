package io.mine.ft.train.moshi.zerenlian.emp_1.zhujie;

//import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Documented
@Inherited
@Component
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface HandlerAnnotation {

    //@AliasFor(annotation = Component.class)
    String value() default "";


    /**
     * handler信息描述
     */
    String desc() default "请填充描述";

    /**
     * 是否共享Handler
     *
     * @return
     */
    boolean share() default false;
    /**
     * handler 版本信息
     * @return
     */
    String version() default "0";
    
     /**
      * 
      * @return
      */
    String author() default "请填充开发者名称";
    


}
