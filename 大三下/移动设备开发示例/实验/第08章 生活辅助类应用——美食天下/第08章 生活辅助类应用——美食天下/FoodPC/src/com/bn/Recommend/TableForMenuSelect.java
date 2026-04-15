package com.bn.Recommend;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
public class TableForMenuSelect
{
	JTable jt;
	DefaultTableModel dtm;
	public TableForMenuSelect(JTable jt,DefaultTableModel dtm,String[][] content)
	{
		this.jt=jt;
		this.dtm=dtm;
//		DefaultTableCellRenderer thr = new DefaultTableCellRenderer();
//	    thr.setHorizontalAlignment(JLabel.CENTER);
//	    jt.getTableHeader().setDefaultRenderer(thr);					//列名居中
//	    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
//	    tcr.setHorizontalAlignment(JLabel.CENTER);
//	    jt.setDefaultRenderer(Object.class, tcr);						//内容居中
//	   jt.getTableHeader().setReorderingAllowed(false);   //不可整列移动    缩放列宽
//	   jt.getTableHeader().setResizingAllowed(false); //不可拉动表格	移动位置
	    
		String[] heads=new String[]{"编号","菜名","上传者","菜系","口味","工艺","时间","主要厨具","难度",""};
		dtm.setDataVector(content, heads);
		jt.setFont(new Font("宋体",Font.PLAIN,14));
		int column=jt.getColumnCount();		
		jt.setRowHeight(25);	
		TableColumn   aColumn   =   jt.getColumnModel().getColumn(column-1);   
		aColumn.setCellEditor(jt.getDefaultEditor(Boolean.class));   
		aColumn.setCellRenderer(jt.getDefaultRenderer(Boolean.class));
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
				tc.setPreferredWidth(100);
			}
			else if(i==2||i==3)
			{
				tc.setPreferredWidth(100);
			}
			else 
			{
				tc.setPreferredWidth(80);
			}
		}
	}
}