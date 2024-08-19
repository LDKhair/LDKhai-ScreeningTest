package Model;

import DTO.AccountDTO;

public class AccountResponse extends AccountDTO {
	private boolean isFirstLogin, role;
	private String instructorName;
	private int idInstructor;

	public AccountResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountResponse(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}

	public AccountResponse(boolean isFirstLogin, boolean role, String instructorName, int idInstructor) {
		super();
		this.isFirstLogin = isFirstLogin;
		this.role = role;
		this.instructorName = instructorName;
		this.idInstructor = idInstructor;
	}

	public boolean isFirstLogin() {
		return isFirstLogin;
	}

	public void setFirstLogin(boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	public boolean isRole() {
		return role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public int getIdInstructor() {
		return idInstructor;
	}

	public void setIdInstructor(int idInstructor) {
		this.idInstructor = idInstructor;
	}

}
