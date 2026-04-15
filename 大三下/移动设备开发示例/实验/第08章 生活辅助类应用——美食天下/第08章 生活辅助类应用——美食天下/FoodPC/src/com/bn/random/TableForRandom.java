package com.bn.random;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.bn.NetUtil.NetIO;
import com.bn.Renderer.ForbidCellRenderer;
import com.bn.primary.PrimaryFrame;
import com.bn.util.TextJButtonTableCellRenderer;

public class TableForRandom{
	static String[] heads;
	int column;
	JTable jt;
	DefaultTableModel dtm;
	public TableForRandom(JTable jt,DefaultTableModel dtm,Object[][] content)
	{
		this.jt = jt;
		this.dtm = dtm;
		heads=new String[]{"编号","标签","所在城市","上传者","简介","",""};
		dtm.setDataVector(content, heads);
		jt.setFont(new Font("宋体",Font.PLAIN,14));
		column=jt.getColumnCount();		
		jt.getColumnModel().getColumn(column-1).setCellRenderer(new TextJButtonTableCellRenderer("详细"));	
		jt.getTableHeader().setFont(new Font("宋体",Font.PLAIN,14));
	
		jt.getColumnModel().getColumn(column-2).setCellRenderer(new ForbidCellRenderer());	
		jt.setRowHeight(25);
		jt.setRowMargin(2);
		for(int i=0;i<column;i++)
		{
			TableColumn tc=jt.getColumnModel().getColumn(i);
			if(i==0)
			{
				tc.setPreferredWidth(50);
			}
			else if(i==1)
			{
				tc.setPreferredWidth(120);
			}
			else if(i==2)
			{
				tc.setPreferredWidth(90);
			}
			else if(i==4)
			{
				tc.setPreferredWidth(370);
			}
		}		
	}	
}