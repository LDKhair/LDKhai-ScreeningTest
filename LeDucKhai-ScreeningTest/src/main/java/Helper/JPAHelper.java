package Helper;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

public class JPAHelper implements AutoCloseable {
	private final EntityManager em;

	public JPAHelper(EntityManager em) {
		this.em = em;
	}

	public <T> T getSingleResult(String jpql, Class<T> resultClass, String paramName, Object paramValue) {
		T result = null;
	    try {
	        TypedQuery<T> query = em.createQuery(jpql, resultClass);
	        query.setParameter(paramName, paramValue);
	        result = query.getSingleResult();
	    } catch (NoResultException e) {
	        result = null;
	    } catch (NonUniqueResultException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}

	public <T> List<T> findResultList(String jpql, Class<T> resultClass) {
		try {
            TypedQuery<T> query = em.createQuery(jpql, resultClass);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error executing query: " + jpql, e);
        }
	}

	@Override
	public void close() {
		if (em.isOpen()) {
			em.close();
		}
	}
}
