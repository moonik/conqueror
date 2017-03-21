package conqueror.shop;

import conqueror.army.Army;
import conqueror.army.ArmyRepository;
import conqueror.castle.CastleRepository;
import conqueror.profile.UserResources;
import conqueror.profile.UserResourcesRepository;
import conqueror.shop.exceptions.NotEnoughtGoldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private UserResourcesRepository userResourcesRepository;

    @Autowired
    private CastleRepository castleRepository;

    @Autowired
    private ArmyRepository armyRepository;

    public Army buyArmy(Long castleId, Long amount, String warrior) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();
//        if(castleRepository.findOne(castleId).getOwner() != currentUser)
//        {
//            throw new NotYourCastleException();
//        }
        Shop searchedWarrior = shopRepository.findOneByWarrior(warrior);
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
