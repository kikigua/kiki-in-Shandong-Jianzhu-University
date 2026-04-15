package 大二上课设选做题二;
import java.util.Scanner;
public class text {
	public static void main(String[] args)throws Exception{
		boolean flag=false;
		while(flag==false){
			try{
		yunsuan p=new yunsuan();
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入运算表达式：");
		String expression=sc.next();
		String postfix=p.convertToPostfix(expression);
		System.out.print("运算结果为：");
		System.out.println(p.numberCalculate(postfix));
		} 
		catch(Exception e){
			System.out.println("输入错误！请重新输入：");
		}
		}
}

}
