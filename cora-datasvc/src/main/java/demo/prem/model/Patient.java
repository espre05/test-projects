package demo.prem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;
@Entity
@Table(name="PATIENTS")
public class Patient { 

	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;
	@Column(name = "firstname")
	private String firstName;
//	@Column(name = "middlename")
//	private String middleName;
	@Column(name = "lastname")
	private String lastName;
	@Column(name = "mrn")
	private String mrn;
	@Column(name = "gender")
	private GenderType gender;
	@Column(name = "dob")
	private Date dateOfBirth;
//	@Column(name = "dob")
//	private String uniquePatientId;

	public Long getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
//	public String getMiddleName() {
//		return middleName;
//	}
//	public void setMiddleName(String middleName) {
//		this.middleName = middleName;
//	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMrn() {
		return mrn;
	}
	public void setMrn(String mrn) {
		this.mrn = mrn;
	}
	public GenderType getGender() {
		return gender;
	}
	public void setGender(GenderType gender) {
		this.gender = gender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
//	public String getUniquePatientId() {
//		return uniquePatientId;
//	}
//	public void setUniquePatientId(String uniquePatientId) {
//		this.uniquePatientId = uniquePatientId;
//	}
}