package conqueror.castle;

import org.springframework.stereotype.Repository;

@Repository
public interface CastleRepo {

    Castle findAllOrderByXDesc();


}
