package conqueror.shop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    Shop findOneByWarrior(String warrior);
}
