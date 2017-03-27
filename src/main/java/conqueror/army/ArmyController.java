package conqueror.army;

import conqueror.castle.CastleRepo;
import conqueror.castle.CastleRepository;
import conqueror.castle.exceptions.CastleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/army/")
public class ArmyController {

    @Autowired
    private ArmyRepository armyRepository;

    @Autowired
    private CastleRepository castleRepository;

    @Autowired
    private ArmyRepo armyRepo;

    @Autowired
    private CastleRepo castleRepo;

    @GetMapping("getArmy/{castleId}")
    public List<Army> getArmy(@PathVariable("castleId") Long castleId)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();

        List<Army> army = armyRepo.findArmy(castleId);
        if(currentUser != castleRepository.findOne(castleId).getOwner())
        {
            throw new CastleException();
        }else
            return army;
    }

//    @PostMapping("sendArmy/{receiver}")
//    public List<Army> sendArmy(@PathVariable("receiver") String receiver, ArmyDTO armyDTO)
//    {
//        Long idReceiver = castleRepo.findOneByCastleName(receiver).getId();
//        return userResourcesService.sendGold(idReceiver, userResourcesDTO);
//    }

}
