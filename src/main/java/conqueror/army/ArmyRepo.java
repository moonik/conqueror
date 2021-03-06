package conqueror.army;

import java.util.List;

public interface ArmyRepo {

    List<Army> findArmy(Long castleId);
    Army getArmyByWarriorAndCastleId(Long castleId, Long warriorId);

}
