package dd.projects.ddshop.repositories;

import dd.projects.ddshop.models.Cart_entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartEntryRepository extends JpaRepository<Cart_entry,Integer> {
}
