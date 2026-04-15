package com.bn.menu;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import com.bn.util.TextJButtonTableCellRenderer;

public class TableForAddMenu {
	public TableForAddMenu(JTable jtF, JTable jtV, JTable jtP) {
		String[] headsF = new String[] { "名称", "数量" };
		Object[][] contentF = new Object[][] { { "", "" },{ "", "" }};
		
		AddMenuPanel.dtmF.setDataVector(contentF, headsF);
		Object[][] contentV = new Object[][] { { "", "" }, { "", "" }};
		AddMenuPanel.dtmV.setDataVector(contentV, headsF);
		AddMenuPanel.dtmP.clearAll();
		jtP.getColumnModel().getColumn(3)
				.setCellRenderer(new TextJButtonTableCellRenderer("添加图片"));
		setWidth(jtF);
		setWidth(jtV);
		setJtPWidth(jtP);
		AddMenuPanel.menuT.setText("");
		AddMenuPanel.skilT.setText("");
		AddMenuPanel.intrT.setText("");
		AddMenuPanel.primaryP=null;
		AddMenuPanel.dtmP.addRow(new Object[] { "步骤" + 1, "", null, "" });
		AddMenuPanel.dtmP.addRow(new Object[] { "步骤" + 2, "", null, "" });
		
		ImageIcon ii = new ImageIcon("res/img/mstx.png");
		ii.setImage(ii.getImage().getScaledInstance(110, 75,
				Image.SCALE_DEFAULT));
		AddMenuPanel.jlpic.setIcon(ii);
	}

	public void setWidth(JTable jt) {
		jt.setRowHeight(25);
		jt.getTableHeader().setFont(new Font("宋体", Font.PLAIN, 14));
		for (int i = 0; i < jt.getColumnCount(); i++) {
			TableColumn tc = jt.getColumnModel().getColumn(i);
			if (i == 0) {
				tc.setPreferredWidth(160);
			} else {
				tc.setPreferredWidth(115);
			}
		}
	}
	public void setJtPWidth(JTable jt) {
		jt.setRowHeight(25);
		jt.getTableHeader().setFont(new Font("宋体", Font.PLAIN, 14));

		for (int i = 0; i < jt.getColumnCount(); i++) {
			TableColumn tc = jt.getColumnModel().getColumn(i);
			if (i == 0) {
				tc.setPreferredWidth(65);
			} else if (i == 1) {
				tc.setPreferredWidth(442);
			} else if (i == 2) {
				tc.setPreferredWidth(40);
			} else {
				tc.setPreferredWidth(117);
			}
		}
	}
}