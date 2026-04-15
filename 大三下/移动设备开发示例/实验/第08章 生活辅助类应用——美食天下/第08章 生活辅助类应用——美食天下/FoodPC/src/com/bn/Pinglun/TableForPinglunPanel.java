package com.bn.Pinglun;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.bn.NetUtil.NetIO;
import com.bn.Renderer.ForbidCellRenderer;
import com.bn.util.TextJButtonTableCellRenderer;

public class TableForPinglunPanel
{
	String[] heads;
	String[][] content;
	int column;
	JTable jt;
	DefaultTableModel dtm;
	
	public TableForPinglunPanel(JTable jt,DefaultTableModel dtm,String[][] content,int type)
	{
		this.jt=jt;
		this.dtm=dtm;
		this.content=content;
		heads=new String[]{"评论编号","评论人","评论内容","加入日期","","详细"};		
		dtm.setDataVector(content, heads);
		jt.setFont(new Font("宋体",Font.PLAIN,14));
		column=jt.getColumnCount();		
		jt.getColumnModel().getColumn(column-2).setCellRenderer(new ForbidCellRenderer());	
		jt.getColumnModel().getColumn(column-1).setCellRenderer(new TextJButtonTableCellRenderer("详细"));
		jt.getTableHeader().setFont(new Font("宋体",Font.PLAIN,14));
		jt.setRowHeight(25);
		jt.setRowMargin(2);
		jt.removeMouseListener(PinglunPanel.mouseListener);
		System.out.println("i");
		if(type==0)
		{
			PinglunPanel.mouseListener=new MyMenuListener();
		}else
		{
			PinglunPanel.mouseListener=new MyRandomListener();
		}
		jt.addMouseListener(PinglunPanel.mouseListener);
		for(int i=0;i<column-1;i++)
		{
			TableColumn tc=jt.getColumnModel().getColumn(i);
			if(i==0)
			{
				tc.setPreferredWidth(80);
			}
			else if(i==1)
			{
				tc.setPreferredWidth(90);
			}
			else if(i==2)
			{
				tc.setPreferredWidth(350);
			}
			else if(i==3)
			{
				tc.setPreferredWidth(180);
			}
			else
			{
				tc.setPreferredWidth(80);
			}

		}
	}
	private class MyRandomListener extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent e) {
			Point point=e.getPoint();		
			int row=jt.rowAtPoint(point);//jt.getSelectedRow();
			int col=jt.columnAtPoint(point);
			int column=jt.getColumnCount();
			System.out.println(" column  row "+col+" "+row);
			if(col==column-1)
			{
				String picName=(String) dtm.getValueAt(row, col);
				String info=(String) dtm.getValueAt(row, 2);
				System.out.println("picName "+picName);
				new PinglunDetailJFrame(picName,info,"随拍评论详细");
			}else if(col==column-2)
			{
				String pinglunId=(String)jt.getValueAt(row, 0);
				String s=(String) dtm.getValueAt(row, col);
				Boolean flag=Boolean.parseBoolean(s);
				s=flag?"false":"true";
				dtm.setValueAt(s, row, col);
				if(flag)
				{
					NetIO.forbidRandomPinglun(pinglunId);
					System.out.println("true");
				}else
				{
					NetIO.permitRandomPinglun(pinglunId);
					System.out.println("false");
				}
			}
		}		
	}
	private class MyMenuListener extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent e) {
			Point point=e.getPoint();		
			int row=jt.rowAtPoint(point);//jt.getSelectedRow();
			int col=jt.columnAtPoint(point);
			int column=jt.getColumnCount();
			if(col==column-1)
			{
				String picName=(String) dtm.getValueAt(row, col);
				String info=(String) dtm.getValueAt(row, 2);
				System.out.println("picNameinfo"+picName+info);
				new PinglunDetailJFrame(picName,info,"菜品评论详细");
			}else if(col==column-2)
			{
				String pinglunId=(String)jt.getValueAt(row, 0);
				String s=(String) dtm.getValueAt(row, col);
				Boolean flag=Boolean.parseBoolean(s);
				s=flag?"false":"true";
				dtm.setValueAt(s, row, col);
				if(flag)
				{
					NetIO.forbidMenuPinglun(pinglunId);
					System.out.println("true");
				}else
				{
					NetIO.permitMenuPinglun(pinglunId);
					System.out.println("false");
				}
			}
		}	
	}	
}
