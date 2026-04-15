package com.bn.util;

import com.bn.databaseutil.DBUtil;

public class TranslateUtil
{
	///筛选条件
	public static String getCondition(String info)
	{
		String[] sss=info.split("<#>");
		StringBuffer sb=new StringBuffer();
		for(String ss:sss)
		{
			String[] s=ss.split("%");
			if(!s[1].equals("所有"))
			{
				sb.append(s[0]+"='"+s[1]+"' and ");
			}
		//	System.out.println(sb.toString());
		}
		return sb.substring(0,sb.length()-5).toString();
	}
	//要获取的列名字段
	public static String getColumn(String info)
	{
		String[] sss=info.split("<#>");
		StringBuffer sb=new StringBuffer();
		for(String s:sss)
		{
			sb.append(s+",");
		}
		return sb.substring(0, sb.length()-1).toString();
	}
	
	////
	public static String getInsertSentence(String info)
	{
		String[] str=info.split("<#>");
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<str.length;i++)
		{
			String ss=str[i];
			if(ss!=null&&ss.length()>0)
			sb.append("'"+ss+"',");
			else{
				sb.append("'',");
			}
		}		
		return sb.substring(0, sb.length()-1).toString();		
	}
	////字符串转换成插入的sql语句  纯字符串
	public static String getInsertSql(String info)
	{
		String[] sss=info.split("<#>");
		StringBuffer sb=new StringBuffer("'");
		for(int i=0;i<sss.length;i++)
		{
			String ss=sss[i];
			sb.append(ss+"','");
		}
		return sb.substring(0, sb.length()-2).toString();		
	}
	//插入过程
	public static String getInsertProcess(String info)
	{
		String[] ss=info.split("<#>");
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<20;i++)
		{
			if(i<ss.length)
			{
				sb.append("'"+ss[i]+"',");
			}else
			{
				sb.append("null,");
			}
		}
		return sb.substring(0, sb.length()-1).toString();
	}
//	///初始化，查询项数组转成字符串
//	public static String translateString()
//	{
//		StringBuffer sb=new StringBuffer();
//		for(String[] ss:Constant.CONDITION)
//		{
//			for(String s:ss)
//			{
//				sb.append(s.toString()+"<#>");
//			}
//			sb.replace(sb.length()-3, sb.length(), "<%>");
//		}
//		return sb.subSequence(0, sb.length()-3).toString();
//	}
	///初始化，查询项数组转成字符串
		public static String translateString(String[][] sss)
		{
			StringBuffer sb=new StringBuffer();
			for(String[] ss:sss)
			{
				for(String s:ss)
				{
					sb.append(s.toString()+"<#>");
				}
				sb.replace(sb.length()-3, sb.length(), "<%>");
			}
			return sb.subSequence(0, sb.length()-3).toString();
		}
}