package dd.projects.ddshop.repositories;

import dd.projects.ddshop.models.AssignedValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssignedValueRepository extends JpaRepository<AssignedValue,Integer> {
}
