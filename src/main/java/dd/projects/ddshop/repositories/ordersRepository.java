package dd.projects.ddshop.repositories;

import dd.projects.ddshop.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ordersRepository extends JpaRepository<Orders,Integer> {
}
