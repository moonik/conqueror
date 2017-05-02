package conqueror.army;

import conqueror.army.exceptions.WrongAmountOfArmyException;
import conqueror.castle.Castle;
import conqueror.castle.CastleRepo;
import conqueror.castle.CastleRepository;
import conqueror.shop.Shop;
import conqueror.shop.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ArmyService {

    @Autowired
    private CastleRepository castleRepository;

    @Autowired
    private ArmyRepository armyRepository;

    @Autowired
    private ArmyRepo armyRepo;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private CastleRepo castleRepo;

    private static final int SPEED = 40;

    public Army sendArmyToCastle(Castle castleReceiver, ArmyDTO armyDTO) throws InterruptedException {
        Castle castleSender = castleRepository.findOne(armyDTO.getIdSender());
        Shop warrior = shopRepository.findOneByWarrior(armyDTO.getTypeOfWarrior());
        Army armySender = armyRepo.getArmyByWarriorAndCastleId(armyDTO.getIdSender(), warrior.getId());
        Army armyReceiver = armyRepo.getArmyByWarriorAndCastleId(castleReceiver.getId(), warrior.getId());
        int distance = castleRepo.getDistance(castleReceiver, castleSender);
        if (armySender.getAmount() - armyDTO.getAmount() >= 0) {
            if (armyReceiver == null) {
                armyReceiver = new Army(warrior, armyDTO.getAmount(), castleReceiver, true);
            }else
                armyReceiver.setAmount(armyReceiver.getAmount() + armyDTO.getAmount());

            armySender.setAmount(armySender.getAmount() - armyDTO.getAmount());
            armyRepository.save(armySender);

            TimeUnit.SECONDS.sleep(distance/SPEED);

            return armyRepository.save(armyReceiver);
        } else
            throw new WrongAmountOfArmyException("Wrong amount of warriors");
    }
}
