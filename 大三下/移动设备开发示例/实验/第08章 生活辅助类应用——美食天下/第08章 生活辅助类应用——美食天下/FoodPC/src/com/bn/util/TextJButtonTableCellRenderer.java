package com.bn.util;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class TextJButtonTableCellRenderer implements TableCellRenderer
{

	JButton unitJCB;
	public TextJButtonTableCellRenderer(String info)
	{
		unitJCB=new JButton(info);
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {		
		return unitJCB;
	}
	
}