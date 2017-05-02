package conqueror.castle;


import conqueror.castle.exceptions.CastleException;
import conqueror.resourceBuilding.ResourceBuilding;
import conqueror.resourceBuilding.ResourceBuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CastleService {

    @Autowired
    private CastleRepository castleRepository;

    @Autowired
    private CastleRepo castleRepo;

    @Autowired
    private ResourceBuildingRepository resourceBuildingRepository;

    private int i, j, x, y;

    /**
     * function create castle with coordinates x and y
     * map is 50x50
     * i is a line
     * j is a column
     * x = i * 10 + random value from 1 to 9
     * y = j * 10 + random value from 1 to 9
     * if a new castle wants to be created on x = 51 then i = 0 and j + 1
     * function searches last castle and gets its coordinates and then pluses coordinates to a new castle
     *
     * @param name castle name
     * @return saves castle
     */
    public Castle createCastle(String name) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();

        Castle lastCastle = castleRepo.findAllOrderByIdDesc();
        if (lastCastle == null) {
            x = (0 * 10) + (int) (1 + (int) (Math.random() * 9));
            y = (0 * 10) + (int) (1 + (int) (Math.random() * 9));
            Castle newCastle = castleRepository.save(new Castle(currentUser, name, x, y));
            ResourceBuilding resourceBuilding = new ResourceBuilding(newCastle.getId(), 1, 1, 1);
            resourceBuildingRepository.save(resourceBuilding);
            return castleRepository.save(newCastle);
        }

        if (castleRepository.findOneByOwner(currentUser) != null || name.equals("") || name.contains(" ")) {
            throw new CastleException("You already have one castle or you entered bad castle name");
        }

//        if (castleRepo.findOneByCastleName(name) != null) {
//            throw new CastleNameAlreadyExistsEcpetion();
//        }

        if (lastCastle.getX() / 10 == 50) {
            i = 0;
            j = lastCastle.getY() / 10 + 1;

            x = (i * 10) + (int) (1 + (int) (Math.random() * 9));
            y = (j * 10) + (int) (1 + (int) (Math.random() * 9));
            Castle newCastle = castleRepository.save(new Castle(currentUser, name, x, y));
            ResourceBuilding resourceBuilding = new ResourceBuilding(newCastle.getId(), 1, 1, 1);
            resourceBuildingRepository.save(resourceBuilding);
            return castleRepository.save(newCastle);
        } else {
            i = lastCastle.getX() / 10 + 1;
            j = lastCastle.getY() / 10;
            x = (i * 10) + (int) (1 + (int) (Math.random() * 9));
            y = (j * 10) + (int) (1 + (int) (Math.random() * 9));
            Castle newCastle = castleRepository.save(new Castle(currentUser, name, x, y));
            ResourceBuilding resourceBuilding = new ResourceBuilding(newCastle.getId(), 1, 1, 1);
            resourceBuildingRepository.save(resourceBuilding);
            return newCastle;
        }
    }

    /**
     * searches nearest castles
     * @param id castle id
     * @return list of castles
     */
    public List<Castle> getNearestCastles(Long id) {
        Castle myCastle = castleRepository.findOne(id);
        x = myCastle.getX();
        y = myCastle.getY();
        return castleRepo.findNearestCastles(x+50, y+50, x-50, y-50);
    }

}
