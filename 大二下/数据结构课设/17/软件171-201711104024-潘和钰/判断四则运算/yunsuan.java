package 大二上课设选做题二;

import java.util.Scanner;

public class yunsuan {
	
public String convertToPostfix(String expression) throws Exception{
	LinkStack st=new LinkStack();
	String postfix=new String();
	for(int i=0;expression!=null&&i<expression.length();i++){
		char c=expression.charAt(i);
		if(' '!=c){
			if(isOpenParenthesis(c)){
				st.push(c);
			}
			else if(isCloseParenthesis(c)){
				char ac=(Character) st.pop();
				while(!isOpenParenthesis(ac)){
					postfix=postfix.concat(String.valueOf(ac));
					ac=(Character)st.pop();
				}}
			else if(isOperator(c)){
				if(!st.isEmpty()){
					Character ac=(Character)st.pop();
					while(ac !=null&&priority(ac)>=priority(c)){
						postfix=postfix.concat(String.valueOf(ac));
						ac=(Character)st.pop();
					}
					if(ac!=null){
						st.push(ac);
					}
				}
				st.push(c);
			}
			else{
				postfix=postfix.concat(String.valueOf(c));
			}
		}
	}
	while(!st.isEmpty())
		postfix=postfix.concat(String.valueOf(st.pop()));
	return postfix;
}
	public boolean isOperator(char c){
		if('+'==c||'-'==c||'*'==c||'/'==c){
			return true;
		}else{
			return false;
		}
	}
	public boolean isOpenParenthesis(char c){
		return '('==c;
	}
	public boolean isCloseParenthesis(char c){
		return ')'==c;
				}
	public int priority(char c){
		if(c=='*'||c=='/'){
			return 3;
		}
		else if(c=='+'||c=='-'){
			return 2;
		}
		else{
			return 0;
		}
	}
	public double numberCalculate(String postfix) throws Exception{
		LinkStack st=new LinkStack();
		for(int i=0;postfix!=null&&i<postfix.length();i++){
			char c=postfix.charAt(i);
			if(isOperator(c)){
				double d2=Double.valueOf(st.pop().toString());
				double d1=Double.valueOf(st.pop().toString());
				double d3=0;
				if('+'==c){
					d3=d1+d2;
				}else if('-'==c){
					d3=d1-d2;
				}else if('*'==c){
					d3=d1*d2;
				}else if('/'==c){
					if(d2==0){
						throw new IllegalArgumentException("除数不能为0.");
					}else
					d3=d1/d2;
				}
				st.push(d3);
			}else{
				st.push(c);
			}
		}
		return (Double)st.pop();
		
	}

}