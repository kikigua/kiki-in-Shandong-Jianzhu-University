package com.bn.Uid;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.bn.NetUtil.NetIO;
import com.bn.util.Constant;
import com.bn.util.TextJButtonTableCellRenderer;
import com.bn.util.TranslateUtil;

public class TablesForUidInfo
{
	private DefaultTableModel[] dtms;
	private JTable[] jts;
	Object[][] content;
	String[] head;
	int count;
	String uid;
	public TablesForUidInfo(String uid,JTable[] jts,DefaultTableModel[] dtms)
	{
		this.dtms=dtms;
		this.jts=jts;
		this.uid=uid;
		for(JTable jt:jts)
		{
			jt.setRowHeight(20);
		}		
		///¹Ø×¢
		content=null;
		head=new String[]{"ÓÃ»§Ãû³Æ","É¾³ý"};
		String msg=NetIO.getAttention(uid);
		if(!msg.equals("No Message"))
		{
			content=TranslateUtil.getContent(msg,"#","<#>");
		}		
		this.dtms[0].setDataVector(content, head);
		count=jts[0].getColumnModel().getColumnCount();
		jts[0].getColumnModel().getColumn(count-1).setCellRenderer(new TextJButtonTableCellRenderer("É¾³ý"));
		
		//·ÛË¿
		content=null;
		head=new String[]{"ÓÃ»§Ãû³Æ","ÐÔ±ð"};
		msg=NetIO.getFans("uid<#>sex<%>"+uid);
		System.out.println("msg=="+msg);
		if(!msg.equals("No Message"))
		{
			content=TranslateUtil.getContent(msg,"<%>","<#>");
		}
		dtms[1].setDataVector(content, head);
		count=jts[1].getColumnCount();
		
		///²ËÆ× 
		content=null;		
		head=new String[]{"²ËÆ×±àºÅ","Ãû³Æ","ÏêÏ¸"};
		msg=NetIO.getMenuByUid(uid);
		if(!msg.equals(Constant.NO_MESSAGE))
		{
			content=TranslateUtil.getContent(msg, "<%>", "<#>");
		}
		dtms[2].setDataVector(content, head);
		count=jts[2].getColumnCount();
		jts[2].getColumnModel().getColumn(count-1).setCellRenderer(new TextJButtonTableCellRenderer("ÏêÏ¸"));
		
		///ËæÅÄ
		content=null;		
		head=new String[]{"²ËÆ×±àºÅ","¼ò½é","±êÇ©","ÏêÏ¸"};
		msg=NetIO.getRandomByUid(uid);
		if(!msg.equals(Constant.NO_MESSAGE))
		{
			content=TranslateUtil.getContent(msg, "<%>", "<#>");
		}
		dtms[3].setDataVector(content, head);
		count=jts[3].getColumnCount();
		jts[3].getColumnModel().getColumn(count-1).setCellRenderer(new TextJButtonTableCellRenderer("¼ò½é"));
		
		//²ËÆ×ÊÕ²Ø
		content=null;
		head=new String[]{"²ËÆ×±àºÅ","Ãû³Æ","É¾³ý"};
		msg=NetIO.getMenuC(uid);
		if(!msg.equals(Constant.NO_MESSAGE))
		{
			content=TranslateUtil.getContent(msg, "<%>", "<#>");
		}
		dtms[4].setDataVector(content, head);
		count=jts[4].getColumnCount();
		jts[4].getColumnModel().getColumn(count-1).setCellRenderer(new TextJButtonTableCellRenderer("É¾³ý"));
		
		//ËæÅÄÊÕ²Ø
		content=null;
		head=new String[]{"ËæÅÄ±àºÅ","±êÌâ","É¾³ý"};
		msg=NetIO.getRandomC(uid);
		if(!msg.equals(Constant.NO_MESSAGE))
		{
			content=TranslateUtil.getContent(msg, "<%>", "<#>");
		}
		dtms[5].setDataVector(content, head);
		count=jts[5].getColumnCount();
		jts[5].getColumnModel().getColumn(count-1).setCellRenderer(new TextJButtonTableCellRenderer("É¾³ý"));
	}	
}