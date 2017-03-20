package conqueror.shop;

import conqueror.army.Army;
import conqueror.army.ArmyRepository;
import conqueror.castle.CastleRepository;
import conqueror.profile.UserResources;
import conqueror.profile.UserResourcesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/shop/")
public class ShopController {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private UserResourcesRepository userResourcesRepository;

    @Autowired
    private CastleRepository castleRepository;

    @Autowired
    private ArmyRepository armyRepository;

    @PostMapping("buy/{warriorId}")
    public Army buy(@PathVariable("warriorId") Long warriorId, @RequestParam("amount") Long amount) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();
        Long castleId = castleRepository.findByOwner(currentUser).getId();
        Shop searchedWarrior = shopRepository.findOne(warriorId);
        Long userGold = userResourcesRepository.findOne(castleId).getGold();
        Long amountOfWarrior = amount * searchedWarrior.getCost();

        if (userGold >= amountOfWarrior) {
            Army army = new Army(searchedWarrior.getWarrior(), amount, castleId, true);
            UserResources userResources = new UserResources(castleId, castleId, userGold-amountOfWarrior);
            userResourcesRepository.save(userResources);
            return armyRepository.save(army);
        } else
            throw new NotEnoughtGoldException();

    }

}
