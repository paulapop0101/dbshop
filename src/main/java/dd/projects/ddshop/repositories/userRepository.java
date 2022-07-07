package dd.projects.ddshop.repositories;

import dd.projects.ddshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<User,Integer> {
}
