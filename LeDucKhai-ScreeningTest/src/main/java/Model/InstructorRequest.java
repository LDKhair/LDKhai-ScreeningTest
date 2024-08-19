package Model;

import java.util.Date;

import DTO.InstructorDTO;

public class InstructorRequest extends InstructorDTO {
	private Integer typeID, levelID;

	public InstructorRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InstructorRequest(String instructorID, String firstName, String lastName, String email, String address,
			String typeName, String levelName, Date startDate, boolean gender, int id) {
		super(instructorID, firstName, lastName, email, address, typeName, levelName, startDate, gender, id);
		// TODO Auto-generated constructor stub
	}

	public InstructorRequest(Integer typeID, Integer levelID) {
		super();
		this.typeID = typeID;
		this.levelID = levelID;
	}

	public Integer getTypeID() {
		return typeID;
	}

	public void setTypeID(Integer typeID) {
		this.typeID = typeID;
	}

	public Integer getLevelID() {
		return levelID;
	}

	public void setLevelID(Integer levelID) {
		this.levelID = levelID;
	}

}
