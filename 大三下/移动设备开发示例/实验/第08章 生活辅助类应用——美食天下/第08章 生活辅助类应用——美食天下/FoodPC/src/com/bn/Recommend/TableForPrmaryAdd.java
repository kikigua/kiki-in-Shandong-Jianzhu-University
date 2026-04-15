//package com.bn.Recommend;
//import java.awt.Font;
//import java.util.List;
//
//import javax.swing.JLabel;
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableColumn;
//
//import com.bn.Util.TextJButtonTableCellRenderer;
//
//public class TableForPrmaryAdd
//{
//	public TableForPrmaryAdd(JTable jt,DefaultTableModel dtm,String[][] content)
//	{
//		String[] heads=new String[]{"编号","菜名","上传者","菜系","口味","工艺","时间","主要厨具","难度",""};
//		dtm.setDataVector(content, heads);
//		jt.setFont(new Font("宋体",Font.PLAIN,14));
//		int column=jt.getColumnCount();		
//		jt.getColumnModel().getColumn(column-1).setCellRenderer(new TextJButtonTableCellRenderer(""));
////		jt.getColumnModel().getColumn(column-1).setCellEditor(new MenuManageDeleteCellEditor(jt));
//		jt.setRowHeight(25);
//		jt.setRowMargin(2);
//		for(int i=0;i<column;i++)
//		{
//			TableColumn tc=jt.getColumnModel().getColumn(i);
//			if(i==0)
//			{
//				tc.setPreferredWidth(50);
//			}
//			else if(i==1)
//			{
//				tc.setPreferredWidth(100);
//			}
//			else if(i==2||i==3)
//			{
//				tc.setPreferredWidth(100);
//			}
//			else 
//			{
//				tc.setPreferredWidth(80);
//			}
//		}
//	}
//}