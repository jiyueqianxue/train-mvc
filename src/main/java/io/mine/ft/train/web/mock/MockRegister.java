package io.mine.ft.train.web.mock;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;

import io.mine.ft.train.service.ShopSupport;
import io.mine.ft.train.service.ShopSupportMock;
import lombok.extern.slf4j.Slf4j;


/**
 * Created by Spectre on 2019/10/12
 *
 * @Description :
 * @Author : Spectre
 * @Date : 2019/10/12
 * @Version : 1.0.0
 */
//@Component
@Slf4j
//@ConditionalOnExpression("${za.telecom.apollo.mock:0} == 1")
public class MockRegister implements ApplicationListener<ApplicationReadyEvent>, Ordered {

    private static final String MOCK_BEAN_NAME = "repayFacade";

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("=============MockBeanListener=============");
        ConfigurableApplicationContext context = event.getApplicationContext();
        

        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition(MOCK_BEAN_NAME);
        beanFactory.removeBeanDefinition(MOCK_BEAN_NAME);
        beanDefinition.setBeanClassName(ShopSupport.class.getCanonicalName());

        beanFactory.registerBeanDefinition(MOCK_BEAN_NAME, beanDefinition);
        beanFactory.registerSingleton(MOCK_BEAN_NAME, context.getBean(ShopSupportMock.class));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

