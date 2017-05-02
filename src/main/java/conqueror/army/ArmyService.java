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

    
}
