package conqueror.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserResourcesRepository extends JpaRepository<UserResources, Long>{
}
