package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the student database table.
 * 
 */
@Entity
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int stuid;

	private int age;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	private String name;

	private String sex;

	//bi-directional many-to-one association to Classinfo
	@ManyToOne
	@JoinColumn(name="className")
	private Classinfo classinfo1;

	public Student() {
	}

	public int getStuid() {
		return this.stuid;
	}

	public void setStuid(int stuid) {
		this.stuid = stuid;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Classinfo getClassinfo1() {
		return this.classinfo1;
	}

	public void setClassinfo1(Classinfo classinfo1) {
		this.classinfo1 = classinfo1;
	}

}