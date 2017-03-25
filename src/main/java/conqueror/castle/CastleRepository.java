package conqueror.castle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CastleRepository extends JpaRepository<Castle, Long> {

    /**
     * searches castle by its owner
     * @param currentUser logged user
     * @return castle
     */
    Castle findOneByOwner(String currentUser);
    Castle findOneByCastleName(String name);
    List<Castle> findByOwner(String owner);

}
