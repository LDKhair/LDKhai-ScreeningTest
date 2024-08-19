package Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Instructor")
public class Instructor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "InstructorID", unique = true, nullable = false)
	private String instructorID;

	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "LastName")
	private String lastName;

	@ManyToOne
	@JoinColumn(name = "FacultyTypeID")
	private FacultyType facultyType;

	@ManyToOne
	@JoinColumn(name = "EducationLevelID")
	private EducationLevel educationLevel;

	@ManyToOne
	@JoinColumn(name = "SalaryLevelID")
	private SalaryLevel salaryLevel;

	@Column(name = "FacultyImage")
	private String facultyImage;
	
	@Column(name = "Gender")
	private boolean gender;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "Address")
	private String address;
	
	@Column(name = "StartDate")
	@Temporal(TemporalType.DATE)
	private Date startDate;

	public Instructor() {
	}

	public Instructor(int id, String instructorID, String firstName, String lastName, FacultyType facultyType,
			EducationLevel educationLevel, SalaryLevel salaryLevel, String facultyImage, boolean gender, String email,
			String address, Date startDate) {
		super();
		this.id = id;
		this.instructorID = instructorID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.facultyType = facultyType;
		this.educationLevel = educationLevel;
		this.salaryLevel = salaryLevel;
		this.facultyImage = facultyImage;
		this.gender = gender;
		this.email = email;
		this.address = address;
		this.startDate = startDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public FacultyType getFacultyType() {
		return facultyType;
	}

	public void setFacultyType(FacultyType facultyType) {
		this.facultyType = facultyType;
	}

	public EducationLevel getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(EducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

	public SalaryLevel getSalaryLevel() {
		return salaryLevel;
	}

	public void setSalaryLevel(SalaryLevel salaryLevel) {
		this.salaryLevel = salaryLevel;
	}

	public String getFacultyImage() {
		return facultyImage;
	}

	public void setFacultyImage(String facultyImage) {
		this.facultyImage = facultyImage;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	
}
