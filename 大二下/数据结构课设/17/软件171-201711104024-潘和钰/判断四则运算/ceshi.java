package 大二上课设选做题二;
import java.util.Scanner;
public class ceshi {
private final int LEFT=0;
private final int RIGHT=1;
private final int OTHER=2;
public int verifyFlag(String str) {
	if("(".equals(str)||"[".equals(str)||"{".equals(str)||"/*".equals(str))
		return LEFT;
	else if(")".equals(str)||"]".equals(str)||"}".equals(str)||"*/".equals(str))
		return RIGHT;
	else 
		return OTHER;
}
public boolean matches(String str1,String str2) {
if (("(". equals(str1)&&")".equals(str2))||("[".equals(str1)&&"]".equals(str2))||("{".equals(str1)&&"}".equals(str2))||("/*".equals(str1)&&"*/".equals(str2)))

return true;

else
return false;
}
private boolean isLegal(String str) throws Exception {
if (!"". equals(str) && str != null) {
LinkStack st= new LinkStack(); // 新建最大存储空
int length = str. length();
for (int i= 0; i< length; i++) {
char c = str.charAt(i); // 指定索引处的 
String t = String. valueOf(c);  //转化成
if (i!= length) {  // c不是
	if (('/'== c &&'*' == str. charAt(i + 1))||('*'==c&&'/'==str.charAt(i+1))) {
		t=t.concat(String.valueOf(str.charAt(i+1)));
i++;
	}
}
			
			if (LEFT == verifyFlag(t)) {//为左分隔符
			st. push(t);  //压人栈
			}
			else if (RIGHT == verifyFlag(t)) { // 为右分隔符
			if (st. isEmpty()|| ! matches(st. pop(). toString(), t)) {
			//右分隔符与栈顶元素不匹
				throw new Exception("错误: Java 语句不合法!"); // 抛出异常

			}
			}
}
			if (!st. isEmpty())  //栈中存在没有匹配的字符
			throw new Exception("错误: Java 语句不合法!");  
			return true;
}
			else
			throw new Exception("错误: Java语句为空!");
			}

}