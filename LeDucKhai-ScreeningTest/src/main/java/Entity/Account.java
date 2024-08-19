package Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "Username", unique = true, nullable = false, insertable = false, updatable = false)
	private String username;

	@Column(name = "Password", nullable = false)
	private String password;

	@Column(name = "Role")
	private boolean role;

	@Column(name = "IsFirstLogin")
	private boolean isFirstLogin = true;

	@OneToOne
	@JoinColumn(name = "Username", referencedColumnName = "InstructorID")
	private Instructor instructor;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int id, String username, String password, boolean role, boolean isFirstLogin,
			Instructor instructor) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.isFirstLogin = isFirstLogin;
		this.instructor = instructor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRole() {
		return role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}

	public boolean isFirstLogin() {
		return isFirstLogin;
	}

	public void setFirstLogin(boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

}
