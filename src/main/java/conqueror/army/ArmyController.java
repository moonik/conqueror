package conqueror.army;

import conqueror.castle.Castle;
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

    @Autowired
    private ArmyService armyService;

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

    @PostMapping("sendArmy/{receiver}")
    public Army sendArmy(@PathVariable("receiver") String receiver, ArmyDTO armyDTO)
    {
        Castle castleReceiver = castleRepo.findOneByCastleName(receiver);
        return armyService.sendArmyToCastle(castleReceiver, armyDTO);
    }

}
