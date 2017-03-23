package conqueror.castle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class CastleRepositoryImpl implements CastleRepo {

    @PersistenceContext
    private EntityManager em;


//    @Override
//    public Castle findAllOrderByXDesc() {
//        Query query = em.createQuery("SELECT c from Castle c ORDER BY x DESC");
//        List<Castle> lastCastle = query.getResultList();
//        if(lastCastle.size() == 0 || lastCastle.isEmpty())
//        {
//            return null;
//        }
//        return lastCastle.get(0);
//    }

    @Override
    public Castle findAllOrderByIdDesc() {
        Query query = em.createQuery("SELECT c from Castle c ORDER BY id DESC");
        List<Castle> lastCastle = query.getResultList();
        if (lastCastle.size() == 0 || lastCastle.isEmpty()) {
            return null;
        }
        return lastCastle.get(0);
    }

    @Override
    public Castle findOneByCastleName(String name) {
        Query query = em.createQuery("SELECT c from Castle c");
        List<Castle> castles = query.getResultList();
        if (castles.size() == 0 || castles.isEmpty()) {
            return null;
        }
        return castles.get(0);
    }

    @Override
    public List<Castle> findAllByOwner(List<String> owners) {
        List<Castle> castles = new ArrayList<>();
        for(int i = 0; i < owners.size(); i++)
        {
            Query query = em.createQuery("SELECT c from Castle c WHERE c.owner = ?1");
            query.setParameter(1, owners.get(i));
            castles.addAll(query.getResultList());
        }
        return castles;
    }

    @Override
    public List<String> findAllWithCastles() {
        Query query = em.createQuery("SELECT DISTINCT c.owner from Castle c");
        List<String> castles = query.getResultList();
        return castles;
    }
}
