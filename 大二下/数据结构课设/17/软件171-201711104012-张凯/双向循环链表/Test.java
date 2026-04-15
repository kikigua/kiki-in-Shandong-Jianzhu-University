package LinearList;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
	int []b = {1,2,3,4,5,6,7,8,9};
	DuLinkList a=new DuLinkList();
	for(int i=0;i<9;i++) {
		a.insert(i, b[i]);
	}
	int x,y,z,q,p;
	x=(int)a.get(3);
	System.out.println("读取第4个数"+x);
	y=a.indexOf(5);
	System.out.println("第一次出现的下标"+y);
	z=a.length();
	System.out.println("长度"+z);
	a.remove(3);
	System.out.println("移除3");
	a.display();
	a.insert(4, 0);
	System.out.println("插入0");
	a.display();
	if(!a.isEmpty()) {
		System.out.print("不为空");
	}
	else
	{
		System.out.println("为空");
	}
	System.out.println();
	a.tr();
	a.display();
}
}


