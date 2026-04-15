package com.bn.Renderer;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
public class ForbidCellRenderer implements TableCellRenderer
{
	JButton jlpic=new JButton();
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Boolean flag=Boolean.parseBoolean((String) value);
		jlpic.setText(flag?"½ûÖ¹":"Í¨¹ý");
		return jlpic;
	}
}
