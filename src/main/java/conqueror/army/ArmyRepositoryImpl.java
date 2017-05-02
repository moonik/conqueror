package conqueror.army;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class ArmyRepositoryImpl implements ArmyRepo{

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Army> findArmy(Long castleId) {
        Query query = em.createQuery("SELECT a from Army a Where a.castleId=?1 AND a.deffense=1");
        query.setParameter(1, castleId);
        List<Army> armyList = query.getResultList();

        return armyList;
    }

    @Override
    public Army getArmyByWarriorAndCastleId(Long castleId, Long warriorId) {
        Query query = em.createQuery("SELECT a from Army a JOIN a.castle c JOIN a.warrior w WHERE c.id =?1 AND w.id=?2");
        query.setParameter(1, castleId);
        query.setParameter(2, warriorId);
        List<Army> armyList = query.getResultList();

        return armyList.size() == 0? null : armyList.get(0);
    }
}
