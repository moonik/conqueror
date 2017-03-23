package conqueror.shop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    /**
     * searches warrior by its name
     * @param warrior warrior's name
     * @return warrior
     */
    Shop findOneByWarrior(String warrior);
}
