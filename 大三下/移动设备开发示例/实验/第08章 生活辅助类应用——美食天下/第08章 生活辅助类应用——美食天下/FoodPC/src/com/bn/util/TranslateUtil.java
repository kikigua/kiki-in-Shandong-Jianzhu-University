package com.bn.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


public class TranslateUtil
{	
	///将服务端传回的字符串拆成二维数组，给表格使用，多行
	public static String[][] getContent(String info,String hang,String lie)
	{
		ArrayList<String[]> messageA=new ArrayList<String[]>();
		int row;
		String[] ssss=info.split(hang);
		row=ssss.length;
		for(String sss: ssss)
		{			
			String[] ss=sss.split(lie);
			messageA.add(ss);
		}
		String[][] remeg=new String[row][];
		for(int i=0;i<row;i++)
		{
			remeg[i]=messageA.get(i);
		}
		return remeg;		
	}
	///将服务端传回的字符串拆成二维数组，给表格使用，多行
		public static List<String[]> getListData(String info,String hang,String lie)
		{
			ArrayList<String[]> messageA=new ArrayList<String[]>();
			String[] rows=info.split(hang);
			for(String row: rows)
			{			
				String[] ss=row.split(lie);
				messageA.add(ss);
			}			
			return messageA;		
		}
	////将图片文件转成二进制数组
	public static byte[] getImageBytes(String picPath)
	{
		FileInputStream fis = null;
		byte[] bb = null;
		File file=new File(picPath);
		try {
			fis=new FileInputStream(file);
			bb=new byte[fis.available()];
			fis.read(bb);
		//	System.out.println("bbb===="+bb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				fis.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return bb;		
	}
	public static String[] getImageName(String id,ArrayList<String> imgePaths,String str)
	{
		String[] pro=new String[imgePaths.size()];
		int k=0;
		for(String ss:imgePaths)////为图片命名
		{
			if(ss.equals(""))
			{
				JOptionPane.showMessageDialog(null, "请将缺少的主图或过长图片补充完整");
				return null;
			}
			String end=ss.substring(ss.length()-4, ss.length());
			String head=id+str+k+end;	
			pro[k++]=head;			
		}
		return pro;
	}
	
	

}