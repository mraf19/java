package programmer.zaman.now.springdata.jpa.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.support.TransactionOperations;
import programmer.zaman.now.springdata.jpa.entity.Category;
import programmer.zaman.now.springdata.jpa.entity.Product;
import programmer.zaman.now.springdata.jpa.model.ProductPrice;
import programmer.zaman.now.springdata.jpa.model.SimpleProduct;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TransactionOperations transactionOperations;

    @Test
    void testCreateProduct() {
        Category category = categoryRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(category);

        {
            Product product = new Product();
            product.setName("Samsung Galaxy");
            product.setPrice(25_000_000L);
            product.setCategory(category);
            productRepository.save(product);
        }

        {
            Product product = new Product();
            product.setName("Xiaomi Redmi");
            product.setPrice(28_000_000L);
            product.setCategory(category);
            productRepository.save(product);
        }
    }

    @Test
    void testFindByCategoryName() {
        List<Product> products = productRepository.findAllByCategory_Name("GADGET MURAH");

        Assertions.assertEquals(2, products.size());
        Assertions.assertEquals("Apple iPhone 14 Pro Max", products.get(0).getName());
        Assertions.assertEquals("Apple iPhone 13 Pro Max", products.get(1).getName());
    }

    @Test
    void testFindByCategoryNameWithSort() {
        Sort sort = Sort.by(Sort.Order.desc("id"));

        List<Product> products = productRepository.findAllByCategory_Name("GADGET MURAH", sort);


        Assertions.assertEquals(2, products.size());
        Assertions.assertEquals("Apple iPhone 14 Pro Max", products.get(1).getName());
        Assertions.assertEquals("Apple iPhone 13 Pro Max", products.get(0).getName());
    }

    @Test
    void testFindByCategoryNameWithPageable() {
        // PAGE 0
        Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Order.asc("id")));

        Page<Product> products = productRepository.findAllByCategory_Name("GADGET MURAH", pageable);

        Assertions.assertEquals(1, products.getContent().size());
        Assertions.assertEquals(0, products.getNumber());
        Assertions.assertEquals(2, products.getTotalElements());
        Assertions.assertEquals(2, products.getTotalPages());
        Assertions.assertEquals("Apple iPhone 13 Pro Max", products.getContent().get(0).getName());

        // PAGE 1
        pageable = PageRequest.of(1, 1, Sort.by(Sort.Order.desc("id")));

        products = productRepository.findAllByCategory_Name("GADGET MURAH", pageable);

        Assertions.assertEquals(1, products.getContent().size());
        Assertions.assertEquals(1, products.getNumber());
        Assertions.assertEquals(2, products.getTotalElements());
        Assertions.assertEquals(2, products.getTotalPages());
        Assertions.assertEquals("Apple iPhone 14 Pro Max", products.getContent().get(0).getName());

    }

    @Test
    void test() {
        // PAGE 0
        Pageable pageable = PageRequest.of(0, 4, Sort.by(Sort.Order.asc("name")));

        Page<Product> products = productRepository.findAllByCategory_Name("GADGET MURAH", pageable);

        List<Product> content = products.getContent();

        System.out.println(content.get(0).getName());
        System.out.println(content.get(1).getName());
        System.out.println(content.get(2).getName());
        System.out.println(content.get(3).getName());


    }

    @Test
    void count() {
        Long count = productRepository.count();
        Assertions.assertEquals(4L, count);

        Long gadgetMurah = productRepository.countByCategory_Name("GADGET MURAH");
        Assertions.assertEquals(4L, gadgetMurah);

        Long nothing = productRepository.countByCategory_Name("Nothing");
        Assertions.assertEquals(0L, nothing);
    }

    @Test
    void exist() {
        boolean b = productRepository.existsByName("Apple iPhone 14 Pro Max");
        Assertions.assertTrue(b);

        boolean c = productRepository.existsByName("Apple iPhone 14 Pro Max 2");
        Assertions.assertFalse(c);
    }

    @Test
    void deleteBy() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            Category category = categoryRepository.findById(1L).orElse(null);

            Product product = new Product();
            product.setName("POCO F6");
            product.setPrice(10_000_000L);
            product.setCategory(category);
            productRepository.save(product);

            int delete = productRepository.deleteByName("POCO F6");
            Assertions.assertEquals(1, delete);

            delete = productRepository.deleteByName("POCO F6");
            Assertions.assertEquals(0, delete);

        });
    }

    @Test
    void deleteByNew() {
            Category category = categoryRepository.findById(1L).orElse(null);

            Product product = new Product();
            product.setName("POCO F6");
            product.setPrice(10_000_000L);
            product.setCategory(category);
            productRepository.save(product);

            int delete = productRepository.deleteByName("POCO F6");
            Assertions.assertEquals(1, delete);

            delete = productRepository.deleteByName("POCO F6");
            Assertions.assertEquals(0, delete);
    }

    @Test
    void testNamedQuery() {
        Pageable pageable = PageRequest.of(0, 1);
        List<Product> products = productRepository.searchProductUsingName("Apple iPhone 14 Pro Max", pageable);

        Assertions.assertEquals(1, products.size());
        Assertions.assertEquals("Apple iPhone 14 Pro Max", products.get(0).getName());
    }

    @Test
    void testQueryAnnotation() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Order.asc("name")));
        Page<Product> products = productRepository.searchProduct("%iPhone%", pageable);
        Assertions.assertEquals(0, products.getNumber());
        Assertions.assertEquals(2, products.getTotalPages());
        Assertions.assertEquals(2, products.getTotalElements());
        Assertions.assertEquals(1, products.getContent().size());
        Page<Product> products2 = productRepository.searchProduct("%Gadget%", pageable);
        Assertions.assertEquals(0, products2.getNumber());
        Assertions.assertEquals(4, products2.getTotalPages());
        Assertions.assertEquals(4, products2.getTotalElements());
        Assertions.assertEquals(1, products2.getContent().size());
    }

    @Test
    void testModifying() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            int total = productRepository.deleteProductUsingName("Salah");
            Assertions.assertEquals(0, total);

            total = productRepository.setPriceToZero(1L);
            Assertions.assertEquals(1, total);

            Product product = productRepository.findById(1L).orElse(null);
            Assertions.assertNotNull(product);
            Assertions.assertEquals(0L, product.getPrice());
        });
    }

    @Test
    void testStream() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            Category category = categoryRepository.findById(1L).orElse(null);
            Assertions.assertNotNull(category);

            Stream<Product> productStream = productRepository.streamAllByCategory(category);

            productStream.forEach(product -> {
                System.out.println(product.getId() + ": " + product.getName());
            });
        });
    }

    @Test
    void testSlice() {
        Pageable firstPage = PageRequest.of(0, 1);

        Category category = categoryRepository.findById(1L).orElse(null);

        Slice<Product> products = productRepository.findAllByCategory(category, firstPage);
        System.out.println(products.getContent().getFirst().getId() + ": " + products.getContent().getFirst().getName());
        while(products.hasNext()){

            products = productRepository.findAllByCategory(category, products.nextPageable());
            System.out.println(products.getContent().getFirst().getId() + ": " + products.getContent().getFirst().getName());
        }
    }

    @Test
    void testLock1() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            try {
                Product product = productRepository.findFirstByIdEquals(1L).orElse(null);
                Assertions.assertNotNull(product);
                product.setPrice(30_000_000L);

                Thread.sleep(20_000L);
                productRepository.save(product);
            } catch (Throwable throwable){
                throw new RuntimeException(throwable);
            }
        });
    }

    @Test
    void testLock2() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            try {
                Product product = productRepository.findFirstByIdEquals(1L).orElse(null);
                Assertions.assertNotNull(product);
                product.setPrice(10_000_000L);
                productRepository.save(product);
            } catch (Throwable throwable){
                throw new RuntimeException(throwable);
            }
        });
    }

    @Test
    void testSpecification() {
        Specification<Product> specification = (root, query, criteriaBuilder) -> {
            return query.where(
                    criteriaBuilder.or(
                            criteriaBuilder.equal(root.get("name"), "Apple iPhone 14 Pro Max"),
                            criteriaBuilder.equal(root.get("name"), "Apple iPhone 13 Pro Max")
                    )
            ).getRestriction();
        };

        List<Product> products = productRepository.findAll(specification);

        Assertions.assertEquals(2, products.size());

    }

    @Test
    void testProjection() {
        List<SimpleProduct> simpleProducts = productRepository.findAllByNameLike("%Apple%", SimpleProduct.class);
        List<ProductPrice> productPrice = productRepository.findAllByNameLike("%Apple%", ProductPrice.class);

        Assertions.assertEquals(2, simpleProducts.size());
        Assertions.assertEquals(2, productPrice.size());

        for (SimpleProduct simpleProduct : simpleProducts) {
            System.out.println(simpleProduct.id() + ": " + simpleProduct.name());
        }

        for (ProductPrice price : productPrice) {
            System.out.println(price.id() + ": " + price.price());
        }
    }
}
