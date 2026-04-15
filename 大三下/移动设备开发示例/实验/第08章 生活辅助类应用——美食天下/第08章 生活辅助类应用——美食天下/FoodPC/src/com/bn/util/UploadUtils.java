package com.bn.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.bn.NetUtil.NetIO;
import com.bn.menu.TableForAddMenu;

public class UploadUtils{
	public static String upLoadPic(String path) {
		File file = null;				//定义文件对象
		FileInputStream fis = null;		//定义文件流	
		byte[] bb = null;				//定义数组
		String name = null;				//定义字符串
		try {
			file = new File(path);		//创建文件对象
			fis = new FileInputStream(file);	//创建文件输入流
			bb = new byte[fis.available()];		//创建字节数组
			fis.read(bb);				//将图片存入字节数组中
			name = NetIO.insertPic(bb);	//图片在服务器端的名字
		} catch (Exception e) {e.printStackTrace();return null;}
		return name;
	}
	public static Boolean upLoadMenu
	(String primary,String content,List<String> process,List<String> picNames){
		StringBuffer sb=new StringBuffer();					//创建StringBuffer对象
		for(int i=0;i<picNames.size();i++){
			String name=upLoadPic(picNames.get(i));			//循环插入所有过程图片
			if(name==null||name.equals(Constant.NO_MESSAGE)){
				return false;
			}
			sb.append(name+"|"+process.get(i)+"<#>");		//将图片在数据库中的名字存入StringBuffer对象
		}
		int id=NetIO.insertProcess(sb.substring(0, sb.length()-3));//添加制作过程
		primary=upLoadPic(primary);									//添加菜品主图
		if(NetIO.insertMenu(id+"<%>"+primary+"<#>"+content)){		//添加菜品
			JOptionPane.showMessageDialog(null, "菜谱添加成功");		//提示添加成功
			return true;
		}
		else{
			JOptionPane.showMessageDialog(null, "菜谱添加失败");		//提示添加失败
		}
		return  false;
	}
}