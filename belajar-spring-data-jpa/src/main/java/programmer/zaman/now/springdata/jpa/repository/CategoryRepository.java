package programmer.zaman.now.springdata.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import programmer.zaman.now.springdata.jpa.entity.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findFirstByNameEquals(String name);

    List<Category> findAllByNameLike(String name);
}
