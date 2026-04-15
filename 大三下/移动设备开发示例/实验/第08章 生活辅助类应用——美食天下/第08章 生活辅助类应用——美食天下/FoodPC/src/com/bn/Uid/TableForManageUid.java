package com.bn.Uid;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.bn.primary.PrimaryFrame;
import com.bn.util.TextJButtonTableCellRenderer;
/*
 * 用户管理界面
 */
public class TableForManageUid  
{
	String[] heads;
	String[][] content;
	int column;
	JTable jt;
	DefaultTableModel dtm;
	
	public TableForManageUid(JTable jt,DefaultTableModel dtm,String[][] content)
	{
		heads=new String[]{"用户名","密码","性别","加入时间","菜谱","随拍","菜谱收藏","随拍收藏","关注",""};		
		dtm.setDataVector(content, heads);
		jt.setFont(new Font("宋体",Font.PLAIN,14));
		column=jt.getColumnCount();		
		jt.getColumnModel().getColumn(column-1).setCellRenderer(new TextJButtonTableCellRenderer("详细"));
		jt.getTableHeader().setFont(new Font("宋体",Font.PLAIN,14));
		jt.setRowHeight(25);
		jt.setRowMargin(2);
		for(int i=0;i<column-1;i++)
		{
			TableColumn tc=jt.getColumnModel().getColumn(i);
			if(i==0)
			{
				tc.setPreferredWidth(90);
			}
			else if(i==1)
			{
				tc.setPreferredWidth(90);
			}
			else if(i==2)
			{
				tc.setPreferredWidth(50);
			}
			else if(i==3)
			{
				tc.setPreferredWidth(150);
			}
			else
			{
				tc.setPreferredWidth(80);
			}

		}
	}
}
