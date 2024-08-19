package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FacultyType")
public class FacultyType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int facultyTypeID;

	@Column(name = "TypeName")
	private String typeName;

	public FacultyType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FacultyType(int facultyTypeID, String typeName) {
		super();
		this.facultyTypeID = facultyTypeID;
		this.typeName = typeName;
	}

	public int getFacultyTypeID() {
		return facultyTypeID;
	}

	public void setFacultyTypeID(int facultyTypeID) {
		this.facultyTypeID = facultyTypeID;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
