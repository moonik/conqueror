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
}
