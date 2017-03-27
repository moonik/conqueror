package conqueror.army;

import conqueror.castle.CastleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArmyService {

    @Autowired
    private CastleRepository castleRepository;

    @Autowired
    private ArmyRepository armyRepository;

//    public UserResources sendGold(Long idReceiver, ArmyDTO armyDTO) {
//        Army castleReceiver = armyRepository.findOne(idReceiver);
//        Army castleSender = armyRepository.findOne(armyDTO.getIdSender());
//
//        if (castleSender.getAmount() - armyDTO.getAmount() < 0) {
//            throw new NotEnoughtGoldException();
//        }
//        else if(castleReceiver.getId() == castleSender.getId())
//        {
//            throw new CastleException();
//        }
//
//        Army newCastleReceiver = new Army()
//    }
}
