package DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import DTO.InstructorDTO;
import Entity.EducationLevel;
import Entity.FacultyType;
import Entity.Instructor;
import Entity.SalaryLevel;
import Helper.JPAHelper;
import Helper.JPAUtils;
import Model.InstructorRequest;

public class InstructorDAO {

	public static Instructor findInstructorById(Integer id) {
		return executeInsideTransaction(em -> em.find(Instructor.class, id));
	}

	public static Instructor createOrUpdateInstructor(Instructor instructor) {
		return executeInsideTransaction(em -> {
			Instructor mergedInstructor = em.merge(instructor);
			return mergedInstructor;
		});
	}

	public static List<InstructorDTO> getAllInstructors() {
		return executeInsideTransaction(em -> {
			JPAHelper jpaHelper = new JPAHelper(em);
			List<Instructor> instructors = jpaHelper.findResultList("SELECT i FROM Instructor i", Instructor.class);
			return mapToDTO(instructors);
		});
	}

	public static Instructor findInstructorByCode(String code) {
		return executeInsideTransaction(em -> {
			JPAHelper jpaHelper = new JPAHelper(em);
			String jpql = "SELECT i FROM Instructor i WHERE i.instructorID = :instructorID";
			return jpaHelper.getSingleResult(jpql, Instructor.class, "instructorID", code);
		});
	}

	private static List<InstructorDTO> mapToDTO(List<Instructor> instructors) {
		List<InstructorDTO> listDTO = new ArrayList<>();
		for (Instructor instructor : instructors) {
			InstructorDTO dto = new InstructorDTO();
			dto.setFirstName(instructor.getFirstName());
			dto.setLastName(instructor.getLastName());
			dto.setInstructorID(instructor.getInstructorID());
			dto.setTypeName(instructor.getFacultyType().getTypeName());
			dto.setStartDate(instructor.getStartDate());
			dto.setLevelName(instructor.getEducationLevel().getLevelName());
			dto.setGender(instructor.isGender());
			dto.setId(instructor.getId());
			dto.setAddress(instructor.getAddress());
			dto.setEmail(instructor.getEmail());
			listDTO.add(dto);
		}
		return listDTO;
	}

	public static void convertInstructorRequest(InstructorRequest request, Instructor instructor) {
		request.setFirstName(instructor.getFirstName());
		request.setLastName(instructor.getLastName());
		request.setGender(instructor.isGender());
		request.setInstructorID(instructor.getInstructorID());
		request.setLevelID(instructor.getEducationLevel().getEducationLevelID());
		request.setTypeID(instructor.getFacultyType().getFacultyTypeID());
		request.setStartDate(instructor.getStartDate());
		request.setId(instructor.getId());
		request.setEmail(instructor.getEmail());
		request.setAddress(instructor.getAddress());
	}

	public static void mapInstructorRequestToInstructor(Instructor instructor, InstructorRequest request) {
		instructor.setFirstName(request.getFirstName());
		instructor.setLastName(request.getLastName());
		instructor.setInstructorID(request.getInstructorID());
		instructor.setGender(request.isGender());
		instructor.setStartDate(request.getStartDate());
		instructor.setAddress(request.getAddress());
		instructor.setEmail(request.getEmail());
		
		updateInstructorInformation(instructor, request);
	}

	public static void updateInstructorInformation(Instructor instructor, InstructorRequest request) {
		FacultyType type = FacultyTypeDAO.findFacultyTypeByID(request.getTypeID());
		EducationLevel level = EducationLevelDAO.findEducationLevelByID(request.getLevelID());
		SalaryLevel levelSalary = SalaryLevelDAO.findSalaryLevel(type.getFacultyTypeID(), level.getEducationLevelID());

		instructor.setFacultyType(type);
		instructor.setEducationLevel(level);
		instructor.setSalaryLevel(levelSalary);
		instructor.setAddress(request.getAddress());
		instructor.setEmail(request.getEmail());
	}

	private static <T> T executeInsideTransaction(JPATransaction<T> action) {
		EntityManager em = JPAUtils.em();
		try {
			em.getTransaction().begin();
			T result = action.execute(em);
			em.getTransaction().commit();
			return result;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException("Error executing transaction", e);
		} finally {
			em.close();
		}
	}

	@FunctionalInterface
	private interface JPATransaction<T> {
		T execute(EntityManager em);
	}
}
