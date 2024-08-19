package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EducationLevel")
public class EducationLevel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int educationLevelID;

	@Column(name = "LevelName")
	private String levelName;

	public EducationLevel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EducationLevel(int educationLevelID, String levelName) {
		super();
		this.educationLevelID = educationLevelID;
		this.levelName = levelName;
	}

	public int getEducationLevelID() {
		return educationLevelID;
	}

	public void setEducationLevelID(int educationLevelID) {
		this.educationLevelID = educationLevelID;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

}
