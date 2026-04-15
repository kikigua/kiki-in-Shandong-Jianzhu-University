package 题目二;

import java.util.*;  
public class jisuantext{
	public static void main(String[] args)throws Exception{
		boolean flag=false;
		while(flag==false){
			try{
		jisuan p=new jisuan();
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入运算表达式：");
		String expression=sc.next();
		String postfix=p.convertToPostfix(expression);
		System.out.println(p.numberCalculate(postfix));
		} 
		catch(Exception e){
			System.out.println("输入错误！请重新输入：");
		}
		}
}
}