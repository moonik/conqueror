package conqueror.resourceBuilding;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceBuildingRepository extends JpaRepository<ResourceBuilding, Long> {

    /**
     * searches resource building by castle id
     * @param id castle id
     * @return resource building
     */
    ResourceBuilding findOneByCastleId(Long id);
}
