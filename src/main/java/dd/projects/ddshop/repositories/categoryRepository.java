package dd.projects.ddshop.repositories;

import dd.projects.ddshop.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface categoryRepository extends JpaRepository<Category,Integer> {
}
