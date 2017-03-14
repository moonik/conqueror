package conqueror.castle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class CastleRepositoryImpl implements CastleRepo {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Castle findAllOrderByXDesc() {
        Query query = em.createQuery("SELECT c from Castle c ORDER BY x DESC");
        List<Castle> lastCastle = query.getResultList();
        if(lastCastle.size() == 0 || lastCastle.isEmpty())
        {
            return null;
        }
        return lastCastle.get(0);
    }
}
