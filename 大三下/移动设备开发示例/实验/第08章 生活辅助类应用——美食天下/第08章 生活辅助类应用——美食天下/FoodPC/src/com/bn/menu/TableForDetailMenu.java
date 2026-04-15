package com.bn.menu;

import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import com.bn.NetUtil.NetIO;
import com.bn.primary.PrimaryFrame;
import com.bn.util.TextJButtonTableCellRenderer;

public class TableForDetailMenu
{
	String[] headF;
	static String[] headV;
	String[] headP;
	String[] imagePath;
	String[] message;
	String[][] contentF;
	String[][] contentV;
	String[][] contentP;
	int columnF;
	int rowF;
	int columnV;
	int rowV;
	int rowP;
	int columnP;
	public TableForDetailMenu(String info,String id,String bacPanel){
		setContent(info,id);				
		headF=new String[]{"名称","数量"};	
		headV=new String[]{"名称","数量"};
		headP=new String[]{"步骤","步骤说明"};
		MenuDetailPanel.jtF.setRowHeight(25);							//设置列的高度
		MenuDetailPanel.jtP.setRowHeight(25);
		MenuDetailPanel.jtV.setRowHeight(25);
		MenuDetailPanel.jtF.setFont(new Font("宋体",Font.PLAIN,12));		
		MenuDetailPanel.dtmF.setDataVector(contentF, headF);				
		MenuDetailPanel.jtP.setFont(new Font("宋体",Font.PLAIN,12));
		MenuDetailPanel.jtV.setFont(new Font("宋体",Font.PLAIN,12));
		MenuDetailPanel.dtmV.setDataVector(contentV, headV);
		MenuDetailPanel.dtmP.setDataVector(contentP, headP);
		MenuDetailPanel.dtmP.addColumn("制作过程明细");					//添加列
		columnP=MenuDetailPanel.jtP.getColumnCount();
		MenuDetailPanel.jtP.getColumnModel().getColumn(columnP-1).		//设置按钮绘制器
		setCellRenderer(new TextJButtonTableCellRenderer("点击查看"));;
		MenuDetailPanel.jtP.getColumnModel().getColumn(columnP-1).		//设置按钮编辑器
		setCellEditor(new DetailMenuDetailCellEditor(message,imagePath));
		MenuDetailPanel.show=bacPanel;
	}

	public void setContent(String info,String id)
	{
		System.out.println("info "+info);		
		String[] sss=info.split("<#>");	
		PrimaryFrame.menuDetailPanel.menuT.setText(sss[2]);
		PrimaryFrame.menuDetailPanel.uidT.setText(sss[1]);
		PrimaryFrame.menuDetailPanel.flavourT.setText(sss[4]);
		PrimaryFrame.menuDetailPanel.crafT.setText(sss[5]);
		PrimaryFrame.menuDetailPanel.timeT.setText(sss[8]);
		PrimaryFrame.menuDetailPanel.toolT.setText(sss[6]);
	
		byte[] bb=NetIO.getImagebyte(sss[0]);
		ImageIcon icon = new ImageIcon(bb);
		icon.setImage(icon.getImage().getScaledInstance(80, 80,Image.SCALE_DEFAULT));
		PrimaryFrame.menuDetailPanel.jlP.setIcon(icon);
		PrimaryFrame.menuDetailPanel.bb = bb;		
		contentF=getTwoContent(sss[9]);
		contentV=getTwoContent(sss[10]);		
		contentP=setProcess(id);
		PrimaryFrame.menuDetailPanel.intrT.setText(sss[3]);
		PrimaryFrame.menuDetailPanel.skilT.setText(sss[11]);
		
	}
	public String[][] setProcess(String id)
	{
		  String mess=NetIO.getProcess(id);
		  String[] rows=mess.split("%");
		  imagePath=new String[rows.length];
		  message=new String[rows.length];
		  String[][] content=new String[rows.length][2];
		  for(int i=0;i<rows.length;i++)
		  {
			  String[] s=rows[i].split("\\|");
			  imagePath[i]=s[0];
			  content[i][0]=" 步骤 "+(i+1);
			  content[i][1]=s[1];
			  message[i]=s[1];
		  }
		  return content;
		  
	}
	public String[][] getTwoContent(String info)
	{
		System.out.println("info "+info);
		String[] ss=info.split("%");
		String[][] content=new String[ss.length][];
		for(int i=0;i<ss.length;i++)
		{
			String[] s=ss[i].split("\\|");
			content[i]=new String[]{s[0],s[s.length-1]};			
		}		
		return content;
	}
}