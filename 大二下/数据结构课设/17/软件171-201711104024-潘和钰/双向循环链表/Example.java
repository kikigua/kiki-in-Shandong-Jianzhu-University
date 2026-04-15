package ch1;
import java.util.Scanner;
public class Example {
	public static void main(String args[])throws Exception {
		DuLinkList dull =new DuLinkList();
		int number;
		Scanner sc=new Scanner(System.in);
		int p=1;
		while(p==1) {
		 System.out.println("1.创建链表");
		 System.out.println("2.插入结点");
		 System.out.println("3.删除结点");
		 System.out.println("4.按位序号查找");
		 System.out.println("5.按值查找");
		 System.out.println("6.链表的长度");
		 System.out.println("7.就地逆置");
		number=sc.nextInt();
	
		switch(number)
		{
		case 1:
			System.out.println("请输入数据个数");  
	        int n=sc.nextInt();  
	     dull=new DuLinkList(n);
		dull.display();

		break;
		 
		case 2:
			System.out.println("请输入要插入的位置:");
			int m2=sc.nextInt();
		 System.out.println("请输入要插入的值:");
	Object m3=sc.next();
	try {
		 dull.insert(m2, m3);

			}catch(Exception e){System.out.println(e.getMessage());
			}
		 dull.display();
		 break;
		 
		case 3:
	 System.out.println("请输入要删除的位置:");
		 int m4=sc.nextInt();
			try { 
				dull.remove(m4);
				
		 }catch(Exception e){System.out.println(e.getMessage());} 
			dull.display();
		 break;
		 
		case 4:		
	 System.out.println("请输入所取元素的位置:");
		 int m5=sc.nextInt();
		 try {
		 System.out.println("所取元素为:"+ dull.get(m5));
			}catch(Exception e){System.out.println(e.getMessage());}
		 break;
		 
		case 5:
	 System.out.println("请输入要查找的值:");
		 Object m6=sc.next();
		 System.out.println("要查找元素的位置为:"+ dull.indexOf(m6));
		 break;
		 
		case 6:
		 System.out.println("此链表的长度为:"+dull.length());
		break;
		
		case 7:
			System.out.print("原链表为：");
			dull.display();
		 dull.inverse();
		 System.out.print("逆置后为：");
		 dull.display();
		 break;



		}
		}
	}

}