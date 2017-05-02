package conqueror.profile;

import conqueror.castle.CastleRepository;
import conqueror.castle.exceptions.CastleException;
import conqueror.shop.exceptions.NotEnoughtGoldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserResourcesService {

    @Autowired
    private UserResourcesRepository userResourcesRepository;

    @Autowired
    private CastleRepository castleRepository;

    /**
     * sends gold to other castle
     * @param userResourcesDTO contains amount and sender id
     * @param idReceiver castle that you want to send gold to
     * @return gold
     */
    public UserResources sendGold(Long idReceiver, UserResourcesDTO userResourcesDTO) {
        UserResources castleReceiver = userResourcesRepository.findOne(idReceiver);
        UserResources castleSender = userResourcesRepository.findOne(userResourcesDTO.getIdSender());

        if (castleSender.getGold() - userResourcesDTO.getAmount() < 0) {
            throw new NotEnoughtGoldException();
        }
        else if(castleReceiver.getId().equals(castleSender.getId()))
        {
            throw new CastleException();
        }

        castleSender.setGold(castleSender.getGold() - userResourcesDTO.getAmount());
        userResourcesRepository.save(castleSender);
        castleReceiver.setGold(castleReceiver.getGold() + userResourcesDTO.getAmount());
        return userResourcesRepository.save(castleReceiver);
    }
}
