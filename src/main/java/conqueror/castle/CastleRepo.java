package conqueror.castle;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CastleRepo {

    /**
     * searches all castles
     * @return list of castles
     */
    Castle findAllOrderByIdDesc();

    /**
     * searches castle by name
     * @param name castle name
     * @return castle
     */
    Castle findOneByCastleName(String name);

    /**
     * searches castle by owners
     * @param owners
     * @return list of owners
     */
    List<Castle> findAllByOwner(List<String> owners);

    /**
     * searches castle owners
     * @return owners
     */
    List<String> findAllWithCastles();

    List<Castle> findNearestCastles(int xMax, int yMax, int xMin, int yMin);

    int getDistance(Castle receiver, Castle sender);
}
