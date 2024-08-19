package DAO;

import javax.persistence.EntityManager;

import Entity.EducationLevel;
import Helper.JPAUtils;

public class EducationLevelDAO {
	
	public static EducationLevel findEducationLevelByID(Integer id) {
		EntityManager em = JPAUtils.em();
		return em.find(EducationLevel.class, id);
	}
}
