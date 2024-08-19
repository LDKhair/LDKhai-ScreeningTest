package Servlet.Instructor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import DAO.AccountDAO;
import DAO.InstructorDAO;
import DTO.InstructorDTO;
import Entity.Account;
import Entity.Instructor;
import Helper.JSONHelper;
import Helper.MailHelper;
import Model.InstructorRequest;

@WebServlet({ "/getall/*", "/viewlist", "/instructorservice/*" })
public class InstructorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String uri = req.getRequestURI();
		if (uri.contains("getdetail")) {
			getDetailInstructor(req, res);
		} else {
			List<InstructorDTO> instructors = InstructorDAO.getAllInstructors();
			JSONHelper.writeJsonResponse(res, instructors);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String uri = req.getRequestURI();

		if (uri.contains("create")) {
			createNewInstructor(req, res);
		} else if (uri.contains("update")) {
			updateInstructor(req, res);
		}
	}

	private void updateInstructor(HttpServletRequest req, HttpServletResponse res) throws IOException {
		InstructorRequest instructorRequest = JSONHelper.parseJSON(JSONHelper.readJSON(req), InstructorRequest.class);

		Instructor insUpdate = InstructorDAO.findInstructorById(instructorRequest.getId());
		if (insUpdate == null) {
			JSONHelper.respondWithError(res, "Instructor not found", 400);
			return;
		}

		InstructorDAO.updateInstructorInformation(insUpdate, instructorRequest);
		InstructorDAO.createOrUpdateInstructor(insUpdate);

		JSONHelper.respondWithSuccess(res, "Update information success");
	}

	private void createNewInstructor(HttpServletRequest req, HttpServletResponse res) throws IOException {
		InstructorRequest instructorRequest = JSONHelper.parseJSON(JSONHelper.readJSON(req), InstructorRequest.class);

		if (InstructorDAO.findInstructorByCode(instructorRequest.getInstructorID()) != null) {
			JSONHelper.respondWithError(res, "Duplicate instructor ID", 400);
			return;
		}

		Instructor newInstructor = new Instructor();
		InstructorDAO.mapInstructorRequestToInstructor(newInstructor, instructorRequest);

		Instructor savedInstructor = InstructorDAO.createOrUpdateInstructor(newInstructor);

		if (savedInstructor != null) {
			Account newAcc = AccountDAO.createNewAccountWithInstructor(savedInstructor);
			
			String emailInstructor = savedInstructor.getEmail(); 
			String emailContent = emailAccountResponse(newAcc, savedInstructor);
			
			MailHelper.sendMail(emailInstructor, emailContent);
			JSONHelper.respondWithSuccess(res, "Add new instructor success");
		} else {
			JSONHelper.respondWithError(res, "An error occurred", 400);
		}
	}

	private void getDetailInstructor(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String id = req.getParameter("id");
		if (id == null) {
			JSONHelper.respondWithError(res, "Request id not found", 400);
			return;
		}

		Integer numberId = Integer.valueOf(req.getParameter("id"));
		Instructor targetIns = InstructorDAO.findInstructorById(numberId);
		if (targetIns == null) {
			JSONHelper.respondWithError(res, "Instructor not found", 400);
			return;
		}

		InstructorRequest result = new InstructorRequest();
		InstructorDAO.convertInstructorRequest(result, targetIns);
		JSONHelper.writeJsonResponse(res, result);
	}

	private String emailAccountResponse(Account acc, Instructor ins) {
        return String.format(
            "<div>\n" +
            "    <p style=\"font-size: 18px;\">\n" +
            "        Dear <strong>%s</strong>, <br>\n" +
            "        We would like to inform you that your account has been successfully created in our system. Below is your account information: <br>\n" +
            "        Username: <strong>%s</strong> <br>\n" +
            "        Password: <strong>%s</strong> (Please change your password after first login) <br>\n" +
            "		 Start date: <strong>%s</strong><br>\n" +
            "		 Thank you for your companionship.<br>\n" +
            "    </p>\n" +
            "</div>", 
            ins.getLastName(), acc.getUsername(), acc.getPassword(), ins.getStartDate()
        );
    }

}
