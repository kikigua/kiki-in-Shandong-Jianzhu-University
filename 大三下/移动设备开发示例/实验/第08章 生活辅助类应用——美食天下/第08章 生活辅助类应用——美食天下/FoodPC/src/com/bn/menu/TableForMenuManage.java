package com.bn.menu;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.bn.NetUtil.NetIO;
import com.bn.primary.PrimaryFrame;
import com.bn.util.TextJButtonTableCellRenderer;

public class TableForMenuManage{					
	public TableForMenuManage(JTable jt,DefaultTableModel dtm,String[][] content){
		String[] heads=new String[]					//列标题
		{"编号","菜名","上传者","菜系","口味","工艺","时间","主要厨具","难度","","",""};
		dtm.setDataVector(content, heads);			//设置表格内容
		jt.setFont(new Font("宋体",Font.PLAIN,14));
		int column=jt.getColumnCount();		
		jt.getColumnModel().getColumn(column-1).
		setCellRenderer(new TextJButtonTableCellRenderer("删除"));	//设置删除按钮的绘制器
		jt.getColumnModel().getColumn(column-2).
		setCellRenderer(new TextJButtonTableCellRenderer("详细"));	//设置详细按钮的绘制器
		jt.getColumnModel().getColumn(column-3).
		setCellRenderer(new ManageMenuForbidCellRenderer());
		jt.setRowHeight(25);						//设置行高度
		jt.setRowMargin(2);							//设置行间隔
		for(int i=0;i<column;i++){					//设置列宽
			TableColumn tc=jt.getColumnModel().getColumn(i);
			if(i==0){
				tc.setPreferredWidth(50);
			}
			else if(i==1){
				tc.setPreferredWidth(120);
			}
			else if(i==2||i==3){
				tc.setPreferredWidth(80);
			}else if(i==4||i==5||i==8){
				tc.setPreferredWidth(60);
			}
			else {
				tc.setPreferredWidth(80);
			}
		}
	}	
}