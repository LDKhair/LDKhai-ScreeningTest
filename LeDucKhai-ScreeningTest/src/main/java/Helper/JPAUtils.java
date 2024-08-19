package Helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {
	private static EntityManagerFactory factory;

	public static EntityManager em() {
		if (factory == null || !factory.isOpen()) {
			factory = Persistence.createEntityManagerFactory("SREENING_TEST");
		}
		return factory.createEntityManager();
	}

	public static void shutdown() {
		if (factory != null && factory.isOpen()) {
			factory.close();
		}
		factory = null;
	}

}
