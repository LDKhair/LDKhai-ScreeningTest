package DAO;

import javax.persistence.EntityManager;

import Entity.FacultyType;
import Helper.JPAUtils;

public class FacultyTypeDAO {

	public static FacultyType findFacultyTypeByID(Integer id) {
		EntityManager em = JPAUtils.em();
		return em.find(FacultyType.class, id);
	}
}
