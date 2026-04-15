package 选做题三;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//首先建立了一个学生数组，把所要插入的学生先放在一个数组里。然后再分别用链地址法和线性探测法将学生插入进去，进行处理冲突。
public class StudentHashTableTest {
	 List<Student> list=new ArrayList<Student>();
	public void addInfo(){                   
		
		Scanner in=new Scanner(System.in);
		System.out.println("请输入要插入的学生个数:");
		int i=in.nextInt();
		for(int j=0;j<i;j++){
			int m=j+1;
			Student s=new Student();
			System.out.println("请输入第"+ j +"个学生的学号  姓名  性别  班级  电话");
			s.setId(in.nextInt());
			//System.out.println("请输入第"+j+"个学生的姓名");
			s.setName(in.next());
			//System.out.println("请输入第"+j+"个学生的性别");
			s.setSex(in.next());
			//System.out.println("请输入第"+j+"个学生的班级");
			s.setCla(in.next());
			//System.out.println("请输入第"+j+"个学生的电话号码");
			s.setTEL(in.next());
			list.add(s);
		}
	}
	public static void main(String[] args) {
		StudentHashTableTest sht=new StudentHashTableTest();
		System.out.print("本系统为学生管理系统，请按提示继续操作：\n");
		sht.addInfo();                                                        //输入学生信息
		HashTable<Student> ht=new HashTable<Student>(sht.list.size());
		HashtableLine L=new HashtableLine(sht.list.size());
		for(int i=0;i<sht.list.size();i++){
			try {
				ht.insert(sht.list.get(i));
				L.insert(sht.list.get(i));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		ht.display();                                                 //链式地址法的输出哈希表中各单链表的元素
		System.out.println();
		L.display();                                                         //线性探测法的
		System.out.println();
		
		
		
		
		
		//根据学生的姓名查找电话
		   while(true){
				System.out.println("请输入要操作的学生名字：");
				Scanner in=new Scanner(System.in);
				String s=in.next();
				Student student1=new Student();
			
				//整个都在for循环之中
				//超强for循环遍历集合
				int k=0;
				for(Student s1:sht.list){
					if(s1.name.equals(s)){
						student1=s1;
					}
					else {
						k++;
						continue;
					}
					try{
					  if(ht.contain(student1))
					 {
					 System.out.println("查找 " + student1.getName() +  "成功" + "他的号码为" + student1.getTel());
					 }
					  
					
							}catch (Exception e) {
					System.out.println("无该学生信息");}
					try {
						if(L.contain(student1) ){
								System.out.println("查找 " + student1.getName() +  "成功" + "他的号码为" + student1.getTel());
							}
						
							}catch (Exception e) {
								System.out.println("无该学生信息");
						}
				}
				if(k==sht.list.size())
				{
					System.out.println("无该学生信息");
				}
			
				
				
				}
				
				
		     } 
	}





		
