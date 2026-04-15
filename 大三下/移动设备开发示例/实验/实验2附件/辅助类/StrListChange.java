package com.king.photo.util;

import java.util.ArrayList;
import java.util.List;

public class StrListChange 
{
	//将字符串转换成List
	public static List<String[]> StrToList(String info)
	{
		List<String[]> list = new ArrayList<String[]>();
		if(info.isEmpty())
		{
			return null;
		}
		String[] s = info.split("<%>");
		for(String ss:s)
		{			
			list.add(ss.split("<#>"));
		}
	    return list;
	}
}