package programmer.zaman.now.springdata.jpa.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import programmer.zaman.now.springdata.jpa.entity.Category;
import programmer.zaman.now.springdata.jpa.entity.Product;
import programmer.zaman.now.springdata.jpa.model.SimpleProduct;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findAllByCategory_Name(String name);
    List<Product> findAllByCategory_Name(String name, Sort sort);
    Page<Product> findAllByCategory_Name(String name, Pageable pageable);

    Long countByCategory_Name(String name);

    boolean existsByName(String name);

    @Transactional
    int deleteByName(String name);

    List<Product> searchProductUsingName(@Param("name") String name, Pageable pageable);

    @Query(
            value = "select p from Product p where p.name like :name or p.category.name like :name",
            countQuery = "select count(p) from Product p where p.name like :name or p.category.name like :name"
    )
    Page<Product> searchProduct(@Param("name") String name, Pageable pageable);

    @Modifying
    @Query("update Product p set p.price = 0 where p.id = :id")
    int setPriceToZero(@Param("id") Long id);

    @Modifying
    @Query("delete from Product p where p.name = :name")
    int deleteProductUsingName(@Param("name") String name);

    Stream<Product> streamAllByCategory(Category category);

    Slice<Product> findAllByCategory(Category category, Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Product> findFirstByIdEquals(Long id);

    <T>List<T> findAllByNameLike(String name, Class<T> tClass);
}
