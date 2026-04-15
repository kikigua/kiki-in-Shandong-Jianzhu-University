package 选做题三;

public class Student {
public int id;
public String name;
public String sex;
public String cla;
public String tel;
public Student() {
	this(0,null,null,null,null);
}
public Student(int id,String name,String sex,String cla,String tel) {
	this.id=id;
	this.name=name;
	this.sex=sex;
	this.cla=cla;
	this.tel=tel;
}
public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getSex() {
    return sex;
}

public void setSex(String sex) {
    this.sex = sex;
}

public String getCla() {
    return cla;
}

public void setCla(String cla) {
    this.cla = cla;
}

public String getTel() {
    return tel = tel;
}

public void setTEL(String tel) {
    this.tel = tel;
}
public String toString() {
    return  "学生学号"+this.getId()+" 学生姓名:"
+ this.getName() +" 学生性别:"+this.getSex()+" 学生班级: "
    		+this.getCla()+" 学生的电话号码: " + this.getTel();
}
/*  public boolean equals(Object obj) {
    Student s=(Student) obj;
    if(s.getName().equals(this.getName())&&s.getTEL().equals(this.getTEL())){
        return true;
    }
    return super.equals(obj);
}*/
public int hashCode() {
    return this.getId();
}
}
