package org.jiezhou.springframework.context;

import org.jiezhou.springframework.factory.AbstractBeanFactory;

/**
 * @author: elk
 * @create: 2024-01-10 16:53
 **/

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private final Object startupShutdownMonitor = new Object();
    private String location;


    public ClassPathXmlApplicationContext(String location) throws Exception {
        this.location = location;
        refresh();
    }

    public void refresh() throws Exception {
        synchronized (startupShutdownMonitor) {
            AbstractBeanFactory beanFactory = obtainFreshBeanFactory();
            prepareBeanFactory(beanFactory);
            this.beanFactory = beanFactory;
        }
    }

    private void prepareBeanFactory(AbstractBeanFactory beanFactory) throws Exception {
        beanFactory.populateBeans();
    }

    public void refreshBeanFactory() throws Exception {
        new xmlBeanDefinitionReader().var
    }
}
