package conqueror.profile;

import conqueror.castle.Castle;
import conqueror.castle.CastleRepository;
import conqueror.resourceBuilding.ResourceBuilding;
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

    private int profit;

    @Scheduled(fixedRate = 5000)
    public void getUserResources() {
        System.out.print("scheduling");
        List<Castle> castles = castleRepository.findAll();
        if (castles.size() == 0)
        {
            return;
        }else {
            ResourceBuilding resourceBuilding;
            for (int i = 0; i < castles.size(); i++) {
                profit = resourceBuildingRepository.findOneByCastleId(castles.get(i).getId()).getProfit();
                UserResources userResources = userResourcesRepository.findOne(castles.get(i).getId());
                if(userResources == null)
                {
                    userResourcesRepository.save(new UserResources(castles.get(i).getId(), castles.get(i).getId(), 500L + profit));
                }else
                userResourcesRepository.save(new UserResources(castles.get(i).getId(), castles.get(i).getId(), userResources.getGold() + profit));
            }
        }
    }
}
