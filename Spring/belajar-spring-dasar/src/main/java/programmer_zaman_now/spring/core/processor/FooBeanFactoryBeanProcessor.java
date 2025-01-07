package programmer_zaman_now.spring.core.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;
import programmer_zaman_now.spring.core.data.Foo;

@Component
public class FooBeanFactoryBeanProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        GenericBeanDefinition definition = new GenericBeanDefinition();
        definition.setBeanClass(Foo.class);
        definition.setScope("singleton");

        registry.registerBeanDefinition("foo",definition);
    }
}
