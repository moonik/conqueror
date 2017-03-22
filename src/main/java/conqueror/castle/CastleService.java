package conqueror.castle;


import conqueror.castle.exceptions.CastleNameAlreadyExistsEcpetion;
import conqueror.resourceBuilding.ResourceBuilding;
import conqueror.resourceBuilding.ResourceBuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CastleService {

    @Autowired
    private CastleRepository castleRepository;

    @Autowired
    private CastleRepo castleRepo;

    @Autowired
    private ResourceBuildingRepository resourceBuildingRepository;

    private int i, j, x, y;

    public Castle createCastle(String name) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();

        Castle lastCastle = castleRepo.findAllOrderByIdDesc();
        if (castleRepository.findByCastleName(name) != null) {
            throw new CastleNameAlreadyExistsEcpetion();
        }
        if (lastCastle == null) {
            x = (0 * 10) + (int) (1 + (int) (Math.random() * 9));
            y = (0 * 10) + (int) (1 + (int) (Math.random() * 9));
            Castle newCastle = castleRepository.save(new Castle(currentUser, name, x, y));
            ResourceBuilding resourceBuilding = new ResourceBuilding(newCastle.getId(), 1, 1, 1);
            resourceBuildingRepository.save(resourceBuilding);
            return castleRepository.save(newCastle);
        }
        if (lastCastle.getX() / 10 == 50) {
            i = 0;
            j = lastCastle.getY() / 10 + 1;

            x = (i * 10) + (int) (0 + (int) (Math.random() * 9));
            y = (j * 10) + (int) (0 + (int) (Math.random() * 9));
            Castle newCastle = castleRepository.save(new Castle(currentUser, name, x, y));
            ResourceBuilding resourceBuilding = new ResourceBuilding(newCastle.getId(), 1, 1, 1);
            resourceBuildingRepository.save(resourceBuilding);
            return castleRepository.save(newCastle);
        } else {
            i = lastCastle.getX() / 10 + 1;
            j = lastCastle.getY() / 10;
            x = (i * 10) + (int) (0 + (int) (Math.random() * 9));
            y = (j * 10) + (int) (0 + (int) (Math.random() * 9));
            Castle newCastle = castleRepository.save(new Castle(currentUser, name, x, y));
            ResourceBuilding resourceBuilding = new ResourceBuilding(newCastle.getId(), 1, 1, 1);
            resourceBuildingRepository.save(resourceBuilding);
            return newCastle;
        }
    }

}
