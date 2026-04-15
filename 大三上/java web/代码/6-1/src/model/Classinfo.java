package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the classinfo database table.
 * 
 */
@Entity
@NamedQuery(name="Classinfo.findAll", query="SELECT c FROM Classinfo c")
public class Classinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String className;

	private String specialty;

	private String tutor;

	//bi-directional many-to-one association to Student
	@OneToMany(mappedBy="classinfo1")
	private List<Student> students1;

	public Classinfo() {
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSpecialty() {
		return this.specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getTutor() {
		return this.tutor;
	}

	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	public List<Student> getStudents1() {
		return this.students1;
	}

	public void setStudents1(List<Student> students1) {
		this.students1 = students1;
	}

	public Student addStudents1(Student students1) {
		getStudents1().add(students1);
		students1.setClassinfo1(this);

		return students1;
	}

	public Student removeStudents1(Student students1) {
		getStudents1().remove(students1);
		students1.setClassinfo1(null);

		return students1;
	}



}