package programmer.zaman.now.springdata.jpa.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import programmer.zaman.now.springdata.jpa.entity.Category;

import java.util.List;

@SpringBootTest
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void testCreate() {
        Category category = new Category();
        category.setName("GADGET");

        categoryRepository.save(category);

        Assertions.assertNotNull(category.getId());

    }

    @Test
    void testUpdate() {
        Category category = categoryRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(category);

        category.setName("GADGET MURAH");
        categoryRepository.save(category);

        Category newCategory = categoryRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(newCategory);
        Assertions.assertEquals(newCategory.getName(), "GADGET MURAH");
    }

    @Test
    void queryMethod(){
        Category category = categoryRepository.findFirstByNameEquals("GADGET MURAH").orElse(null);
        Assertions.assertNotNull(category);
        Assertions.assertEquals(category.getName(), "GADGET MURAH");

        List<Category> categories = categoryRepository.findAllByNameLike("%GADGET%");

        Assertions.assertEquals(1, categories.size());
        Assertions.assertEquals("GADGET MURAH", categories.get(0).getName());
    }

    @Test
    void testAudit() {
        Category category = new Category();
        category.setName("Food");

        categoryRepository.save(category);

        Assertions.assertNotNull(category.getName());
        Assertions.assertNotNull(category.getCreatedDate());
        Assertions.assertNotNull(category.getLastModifiedDate());
    }

    @Test
    void testExample() {
        Category category = new Category();
        category.setName("GADGET MURAH");

        Example<Category> example = Example.of(category);

        List<Category> categories = categoryRepository.findAll(example);

        Assertions.assertEquals(1, categories.size());
    }

    @Test
    void testExample2() {
        Category category = new Category();
        category.setName("GADGET MURAH");
        category.setId(1L);

        Example<Category> example = Example.of(category);

        List<Category> categories = categoryRepository.findAll(example);

        Assertions.assertEquals(1, categories.size());
    }

    @Test
    void testExampleMatcher() {
        Category category = new Category();
        category.setName("gadget murah");

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withIgnoreNullValues();

        Example<Category> example = Example.of(category, matcher);

        List<Category> categories = categoryRepository.findAll(example);

        Assertions.assertEquals(1, categories.size());
    }
}
