package DAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Entity.SalaryLevel;
import Helper.JPAUtils;

public class SalaryLevelDAO {

	public static SalaryLevel findSalaryLevel(Integer typeID, Integer levelID) {
		EntityManager em = JPAUtils.em();
		SalaryLevel target = new SalaryLevel();
		try {
			em.getTransaction().begin();
			String jpql = "SELECT sl FROM SalaryLevel sl WHERE sl.facultyType.id = :typeID AND sl.educationLevel.id = :levelID";
			TypedQuery<SalaryLevel> query = em.createQuery(jpql, SalaryLevel.class);
			query.setParameter("typeID", typeID);
			query.setParameter("levelID", levelID);
			
			target = query.getSingleResult();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return target;
	}
}
