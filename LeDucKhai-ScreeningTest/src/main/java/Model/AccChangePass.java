package Model;

import DTO.AccountDTO;

public class AccChangePass extends AccountDTO{
	public String newPassword;

	public AccChangePass() {
		super();
	}

	public AccChangePass(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}

	public AccChangePass(String newPass) {
		super();
		this.newPassword = newPass;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPass) {
		this.newPassword = newPass;
	}
}
