package com.bn.Recommend;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.bn.NetUtil.NetIO;
import com.bn.menu.TableForDetailMenu;
import com.bn.primary.PrimaryFrame;
import com.bn.util.TextJButtonTableCellRenderer;

public class TableForMenuRecommend
{
	int count;
	String[] head=new String[]{"²ËÆ×±àºÅ","²ËÃû","ÍÆ¼öÈÕÆÚ","",""};
	public TableForMenuRecommend( JTable jt,DefaultTableModel dm,String[][] content)
	{
		for(int i=0;i<content.length;i++)
		{
			String[] str=content[i];
			String menuId=str[0];
			String menuName=str[1];
			String times=str[2];
			content[i]=new String[]{menuId,menuName,times};
		}
		dm.setDataVector(content, head);
		initWidth(jt);		
		count=jt.getColumnCount();
		jt.getColumnModel().getColumn(count-2).setCellRenderer(new TextJButtonTableCellRenderer("É¾³ý"));
		jt.getColumnModel().getColumn(count-1).setCellRenderer(new TextJButtonTableCellRenderer("ÏêÏ¸"));
		
	}
	private void initWidth(JTable jt) {
		jt.setRowHeight(25);
		jt.getTableHeader().setFont(new Font("ËÎÌå", Font.PLAIN, 14));
		for (int i = 0; i < jt.getColumnCount(); i++) {
			TableColumn tc = jt.getColumnModel().getColumn(i);
			if (i == 0) {
				tc.setPreferredWidth(40);
			} else if (i == 1) {
				tc.setPreferredWidth(100);
			} else if (i == 2) {
				tc.setPreferredWidth(250);
			} else 
			{
				tc.setPreferredWidth(60);
			}
		}

	}
}