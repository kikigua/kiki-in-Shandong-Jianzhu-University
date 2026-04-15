package com.bn.menu;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class MyJTable extends JTable
{
	public MyJTable(TableModel dataModel)
	{
		super.setModel(dataModel);
	}
	public void changeSelection(int rowIndex, int columnIndex,
			boolean toggle, boolean extend) {
		super.changeSelection(rowIndex, columnIndex, toggle, extend);
		super.editCellAt(rowIndex, columnIndex, null);
	}
}