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

    /**
     * buy new army
     * for a start we are getting warrior from db by warrior name
     * then we are getting user gold from db
     * then we count how much it will cost to buy new warriors
     * if user gold < amountOfWarriors you can't buy new warriors
     * @param castleId castle id
     * @param shopDTO
     * @return saves new warriors
     */
    public Army buyArmy(Long castleId, ShopDTO shopDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();
//        if(castleRepository.findOne(castleId).getOwner() != currentUser)
//        {
//            throw new NotYourCastleException();
//        }
        Shop searchedWarrior = shopRepository.findOneByWarrior(shopDTO.getWarrior());
        Long userGold = userResourcesRepository.findOne(castleId).getGold();
        Long amountOfWarrior = shopDTO.getAmount() * searchedWarrior.getPrice();

        if (userGold >= amountOfWarrior) {
            Army army = new Army(searchedWarrior.getWarrior(), shopDTO.getAmount(), castleId, true);
            UserResources userResources = new UserResources(castleId, castleId, userGold-amountOfWarrior);
            userResourcesRepository.save(userResources);
            return armyRepository.save(army);
        } else
            throw new NotEnoughtGoldException();

    }

}
