package DAO;

import javax.persistence.EntityManager;

import Entity.Banned;
import Helper.JPAUtils;

public class BannedDAO {
	
	public static Banned addInstructorToBan(Banned ban) {
		EntityManager em = JPAUtils.em();
		Banned newBanned = new Banned();
		try {
			em.getTransaction().begin();
			newBanned = em.merge(ban);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return newBanned;
	}
}
