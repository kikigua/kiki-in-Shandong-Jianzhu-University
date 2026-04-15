package com.bn.Recommend;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.bn.util.TextJButtonTableCellRenderer;
public class TableForRandomRecommend {
	int count;
	String[] head = new String[] { "ËæÅÄ±àºÅ", "ÍÆ¼öÈÕÆÚ", "", "" };

	public TableForRandomRecommend(final JTable jt, final DefaultTableModel dm,
			final String[][] content) {
		count = jt.getColumnCount();
		for (int i = 0; i < content.length; i++) {
			String[] str = content[i];
			String RandomId = str[0];
			String times = str[1];
			content[i] = new String[] { RandomId, times };
		}
		dm.setDataVector(content, head);
		int count = jt.getColumnCount();
		initWidth(jt);
		jt.getColumnModel().getColumn(count - 2)
				.setCellRenderer(new TextJButtonTableCellRenderer("É¾³ý"));
		jt.getColumnModel().getColumn(count - 1)
				.setCellRenderer(new TextJButtonTableCellRenderer("ÏêÏ¸"));
	}

	private void initWidth(JTable jt) {
		jt.setRowHeight(25);
		jt.getTableHeader().setFont(new Font("ËÎÌå", Font.PLAIN, 14));

		for (int i = 0; i < jt.getColumnCount(); i++) {
			TableColumn tc = jt.getColumnModel().getColumn(i);
			if (i == 0) {
				tc.setPreferredWidth(40);
			} else if (i == 1) {
				tc.setPreferredWidth(360);
			} else {
				tc.setPreferredWidth(40);
			}
		}

	}
}