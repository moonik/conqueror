package conqueror.profile;

import conqueror.castle.Castle;
import conqueror.castle.CastleRepository;
import conqueror.resourceBuilding.ResourceBuildingRepository;
import conqueror.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/userresources/")
public class UserResourcesController {

    @Autowired
    private UserResourcesRepository userResourcesRepository;

    @Autowired
    private ResourceBuildingRepository resourceBuildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CastleRepository castleRepository;

    private UserResources userResources;

    private int profit;

    /**
     * function adds gold to all castles
     * for a start gets profit for the castle from the resource building
     * then searches user resources by castle id (searches its gold)
     */
    @Scheduled(fixedRate = 5000)
    public void getUserResources() {
        List<Castle> castles = castleRepository.findAll();
        if (castles.size() == 0) {
            return;
        } else {
            for (int i = 0; i < castles.size(); i++) {
                profit = resourceBuildingRepository.findOneByCastleId(castles.get(i).getId()).getProfit();
                userResources = userResourcesRepository.findOne(castles.get(i).getId());
                if (userResources == null) {
                    userResourcesRepository.save(new UserResources(castles.get(i).getId(), castles.get(i).getId(), 500L + profit));
                } else
                    userResourcesRepository.save(new UserResources(castles.get(i).getId(), castles.get(i).getId(), userResources.getGold() + profit));
            }
        }
    }
}