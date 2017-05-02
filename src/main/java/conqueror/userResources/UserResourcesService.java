package conqueror.userResources;

import conqueror.castle.Castle;
import conqueror.castle.CastleRepo;
import conqueror.castle.CastleRepository;
import conqueror.castle.exceptions.CastleException;
import conqueror.shop.exceptions.NotEnoughtGoldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserResourcesService {

    @Autowired
    private UserResourcesRepository userResourcesRepository;

    @Autowired
    private CastleRepository castleRepository;

    @Autowired
    private CastleRepo castleRepo;

    private static final int SPEED = 40;

    /**
     * sends gold to other castle
     * @param userResourcesDTO contains amount and sender id
     * @param idReceiver castle that you want to send gold to
     * @return gold
     */
    public UserResources sendGold(Long idReceiver, UserResourcesDTO userResourcesDTO) throws InterruptedException {
        UserResources castleReceiver = userResourcesRepository.findOne(idReceiver);
        UserResources castleSender = userResourcesRepository.findOne(userResourcesDTO.getIdSender());
        Castle receiver = castleRepository.findOne(idReceiver);
        Castle sender = castleRepository.findOne(userResourcesDTO.getIdSender());
        int distance = castleRepo.getDistance(receiver, sender);

        if (castleSender.getGold() - userResourcesDTO.getAmount() < 0) {
            throw new NotEnoughtGoldException("Not enough gold!");
        }
        else if(castleReceiver.getId().equals(castleSender.getId()))
        {
            throw new CastleException("You're trying to send gold to the same castle!");
        }
        castleSender.setGold(castleSender.getGold() - userResourcesDTO.getAmount());
        userResourcesRepository.save(castleSender);

        castleReceiver.setGold(castleReceiver.getGold() + userResourcesDTO.getAmount());

        TimeUnit.SECONDS.sleep(distance/SPEED);

        return userResourcesRepository.save(castleReceiver);
    }
}
