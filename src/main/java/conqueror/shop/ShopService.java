package conqueror.shop;

import conqueror.army.Army;
import conqueror.army.ArmyRepo;
import conqueror.army.ArmyRepository;
import conqueror.castle.Castle;
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

    @Autowired
    private ArmyRepo armyRepo;

    /**
     * buy new army
     * for a start we are getting warrior from db by warrior name
     * then we are getting user gold from db
     * then we count how much it will cost to buy new warriors
     * if user gold < amountOfWarriors you can't buy new warriors
     * @param shopDTO
     * @return saves new warriors
     */
    public Army buyArmy(Castle castle, ShopDTO shopDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();
//        if(castleRepository.findOne(castleId).getOwner() != currentUser)
//        {
//            throw new NotYourCastleException();
//        }
        Shop searchedWarrior = shopRepository.findOneByWarrior(shopDTO.getWarrior());
        Long userGold = userResourcesRepository.findOne(castle.getId()).getGold();
        Long amountOfWarrior = shopDTO.getAmount() * searchedWarrior.getPrice();

        if (userGold >= amountOfWarrior) {
            Army army = armyRepo.getArmyByWarriorAndCastleId(castle.getId(), searchedWarrior.getId());
            if(army == null) {
                army = new Army(searchedWarrior, shopDTO.getAmount(), castle, true);
            }else
                army.setAmount(army.getAmount() + shopDTO.getAmount());
            UserResources userResources = new UserResources(castle.getId(), castle.getId(), userGold-amountOfWarrior);
            userResourcesRepository.save(userResources);
            return armyRepository.save(army);
        } else
            throw new NotEnoughtGoldException();

    }

}
