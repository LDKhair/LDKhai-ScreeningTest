package Entity;

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
@Table(name = "Banned")
public class Banned {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "InstructorID", nullable = false)
	private Instructor instructor;

	@Column(name = "BannedDate", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date bannedDate;

	@Column(name = "ReasonBan", columnDefinition = "TEXT")
	private String reasonBan;

	public Banned() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Banned(int id, Instructor instructor, Date bannedDate, String reasonBan) {
		super();
		this.id = id;
		this.instructor = instructor;
		this.bannedDate = bannedDate;
		this.reasonBan = reasonBan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Date getBannedDate() {
		return bannedDate;
	}

	public void setBannedDate(Date bannedDate) {
		this.bannedDate = bannedDate;
	}

	public String getReasonBan() {
		return reasonBan;
	}

	public void setReasonBan(String reasonBan) {
		this.reasonBan = reasonBan;
	}

}
