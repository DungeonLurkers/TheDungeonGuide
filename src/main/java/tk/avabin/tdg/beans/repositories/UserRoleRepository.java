package tk.avabin.tdg.beans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.avabin.tdg.beans.entities.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
}
