package programmer.zaman.now.springdata.jpa.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void success() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            categoryService.create();
        });
    }

    @Test
    void failed() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            categoryService.test();
        });
    }

    @Test
    void successProgrammatic() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            categoryService.createCategories();
        });
    }

    @Test
    void failedProgrammatic() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            categoryService.testProgrammatic();
        });
    }

    @Test
    void testManual() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            categoryService.manual();
        });
    }
}
