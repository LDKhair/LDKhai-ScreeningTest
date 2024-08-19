package DTO;

import java.util.Date;

public class InstructorDTO {
	private String instructorID, firstName, lastName, email, address;
	private String typeName, levelName;
	private Date startDate;
	private boolean gender;
	private int id;

	public InstructorDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InstructorDTO(String instructorID, String firstName, String lastName, String email, String address,
			String typeName, String levelName, Date startDate, boolean gender, int id) {
		super();
		this.instructorID = instructorID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.typeName = typeName;
		this.levelName = levelName;
		this.startDate = startDate;
		this.gender = gender;
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInstructorID() {
		return instructorID;
	}

	public void setInstructorID(String instructorID) {
		this.instructorID = instructorID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
