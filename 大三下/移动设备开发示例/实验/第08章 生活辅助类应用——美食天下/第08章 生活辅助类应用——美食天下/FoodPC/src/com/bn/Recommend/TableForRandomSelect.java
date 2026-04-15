package com.bn.Recommend;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
public class TableForRandomSelect
{
	static String[] heads;
	int column;
	JTable jt;
	DefaultTableModel dtm;
	public TableForRandomSelect(JTable jt,DefaultTableModel dtm,String[][] content)
	{
		this.jt = jt;
		this.dtm = dtm;
		heads=new String[]{"编号","标签","所在城市","上传者","简介",""};
		dtm.setDataVector(content, heads);
		jt.setFont(new Font("宋体",Font.PLAIN,14));
		column=jt.getColumnCount();		
		TableColumn   aColumn   =   jt.getColumnModel().getColumn(column-1);
		aColumn.setCellEditor(jt.getDefaultEditor(Boolean.class));   
		aColumn.setCellRenderer(jt.getDefaultRenderer(Boolean.class));
		jt.getTableHeader().setFont(new Font("宋体",Font.PLAIN,14));
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