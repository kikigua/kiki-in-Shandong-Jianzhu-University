package com.bn.Recommend;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import com.bn.NetUtil.NetIO;
import com.bn.menu.AddMenuPanel;
import com.bn.menu.ProcessJFrame;
import com.bn.util.Constant;
import com.bn.util.LookPicFrame;
import com.bn.util.TextJButtonTableCellRenderer;

public class TableForPrimary
{
	public TableForPrimary(PrimaryRecommendTableModel dtm,JTable jt,List<String[]> content)
	{
		dtm.data.clear();		
		for(int i=0;i<content.size();i++)
		{
			String[] str=content.get(i);
			String id=str[0];
			String picName=str[1];
			String introduce=str[2];
			String times=str[3];
			byte[] bb=NetIO.getImagebyte(picName);
			ImageIcon ic=new ImageIcon(bb);
			ic.setImage(ic.getImage().getScaledInstance(45, 30,Image.SCALE_DEFAULT));

			Object[] value=new Object[]{id,ic,introduce,times,"",picName};
			dtm.addRow(value);		
		}
		int column=jt.getColumnCount();		
		jt.getColumnModel().getColumn(column-2).setCellRenderer(new TextJButtonTableCellRenderer("É¾³ý"));
		jt.getColumnModel().getColumn(column-1).setCellRenderer(new TextJButtonTableCellRenderer("ÏêÏ¸"));
		initWidth(jt);
	}

	private void initWidth(JTable jt) {
		jt.setRowHeight(25);
		jt.getTableHeader().setFont(new Font("ËÎÌå", Font.PLAIN, 14));

		for (int i = 0; i < jt.getColumnCount(); i++) {
			TableColumn tc = jt.getColumnModel().getColumn(i);
			if (i == 0) {
				tc.setPreferredWidth(60);
			} else if (i == 1) {
				tc.setPreferredWidth(20);
			} else if (i == 2) {
				tc.setPreferredWidth(150);
			} else if (i == 3) {
				tc.setPreferredWidth(180);
			} else if(i==4){
				tc.setPreferredWidth(80);
			}
		}

	}
}