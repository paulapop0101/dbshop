package dd.projects.ddshop.repositories;

import dd.projects.ddshop.models.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantRepository extends JpaRepository<Variant,Integer> {
}
