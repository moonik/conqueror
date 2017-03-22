package conqueror.castle;

import org.springframework.stereotype.Repository;

@Repository
public interface CastleRepo {

    Castle findAllOrderByIdDesc();
    Castle findOneByCastleName(String name);
}
