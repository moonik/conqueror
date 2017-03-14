package conqueror.castle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CastleRepository extends JpaRepository<Castle, Long> {

}
