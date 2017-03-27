package conqueror.profile;

import conqueror.castle.Castle;
import conqueror.castle.CastleRepo;
import conqueror.castle.CastleRepository;
import conqueror.resourceBuilding.ResourceBuildingRepository;
import conqueror.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    private UserResourcesService userResourcesService;

    @Autowired
    private CastleRepo castleRepo;

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

    @GetMapping("getResources")
    public Long userResources() {
        Long res = 0L;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();
        List<Castle> castles = castleRepository.findByOwner(currentUser);
        List<UserResources> resources = new ArrayList<>();

        for (int i = 0; i < castles.size(); i++) {
            res += userResourcesRepository.findOne(castles.get(i).getId()).getGold();
        }

        return res;
    }

    @PostMapping("sendGold/{receiver}")
    public UserResources sendGold(@PathVariable("receiver") String receiver, UserResourcesDTO userResourcesDTO) {
        Long idReceiver = castleRepo.findOneByCastleName(receiver).getId();
        return userResourcesService.sendGold(idReceiver, userResourcesDTO);
    }

    @GetMapping("getGold/{id}")
    public UserResources getCastleGold(@PathVariable("id") Long id) {
        return userResourcesRepository.findOne(id);
    }

}