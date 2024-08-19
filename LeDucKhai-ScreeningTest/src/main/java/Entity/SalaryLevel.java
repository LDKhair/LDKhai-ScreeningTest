package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SalaryLevel")
public class SalaryLevel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "FacultyTypeID")
	private FacultyType facultyType;

	@ManyToOne
	@JoinColumn(name = "EducationLevelID")
	private EducationLevel educationLevel;

	@Column(name = "BaseSalary")
	private double baseSalary;

	public SalaryLevel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SalaryLevel(int id, FacultyType facultyType, EducationLevel educationLevel, double baseSalary) {
		super();
		this.id = id;
		this.facultyType = facultyType;
		this.educationLevel = educationLevel;
		this.baseSalary = baseSalary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}

}
