package dd.projects.ddshop.repositories;

import dd.projects.ddshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
