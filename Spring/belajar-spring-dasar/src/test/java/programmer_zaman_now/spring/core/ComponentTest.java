package programmer_zaman_now.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import programmer_zaman_now.spring.core.data.MultiFoo;
import programmer_zaman_now.spring.core.repository.CategoryRepository;
import programmer_zaman_now.spring.core.repository.CustomerRepository;
import programmer_zaman_now.spring.core.repository.ProductRepository;
import programmer_zaman_now.spring.core.service.CategoryService;
import programmer_zaman_now.spring.core.service.CustomerService;
import programmer_zaman_now.spring.core.service.ProductService;

public class ComponentTest {
    private ConfigurableApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(ComponentConfiguration.class);
        context.registerShutdownHook();
    }

    @Test
    void testService() {
        ProductService productService1 = context.getBean(ProductService.class);
        ProductService productService2 = context.getBean("productService",ProductService.class);
    }

    @Test
    void testConstructorDependency() {
        ProductRepository productRepository = context.getBean(ProductRepository.class);
        ProductService productService = context.getBean(ProductService.class);

        Assertions.assertNotNull(productRepository);
        Assertions.assertSame(productRepository, productService.getProductRepository());
    }

    @Test
    void testSetterDependency() {
        CategoryService categoryService = context.getBean(CategoryService.class);
        Assertions.assertNotNull(categoryService);
        CategoryRepository categoryRepository = context.getBean(CategoryRepository.class);
        Assertions.assertSame(categoryRepository, categoryService.getCategoryRepository());
    }

    @Test
    void testFieldDependency() {
        CustomerService customerService = context.getBean(CustomerService.class);
        CustomerRepository normalCustomerRepository = context.getBean("normalCustomerRepository",CustomerRepository.class);
        CustomerRepository premiumCustomerRepository = context.getBean("premiumCustomerRepository",CustomerRepository.class);

        Assertions.assertSame(customerService.getNormalCustomerRepository(), normalCustomerRepository);
        Assertions.assertSame(customerService.getPremiumCustomerRepository(), premiumCustomerRepository);
    }


    @Test
    void testObjectProvider() {
        MultiFoo multiFoo = context.getBean(MultiFoo.class);

        Assertions.assertEquals(3, multiFoo.getFoos().size());
    }
}
