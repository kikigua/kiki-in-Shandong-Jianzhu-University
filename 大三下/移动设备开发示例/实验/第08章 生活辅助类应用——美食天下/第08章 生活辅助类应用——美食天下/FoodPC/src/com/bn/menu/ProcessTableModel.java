package com.bn.menu;

/**
 * 重新定义表格模型
 */
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;

public class ProcessTableModel extends AbstractTableModel {
	public static ArrayList<Object[]> data = new ArrayList<Object[]>();// 定义一个二维数组
	String head[] = { "步骤", "说明", "图片", "" }; // 创建列表题字符串数组
	Class[] typeArray = 
	{ String.class, String.class, Icon.class, String.class };// 定义列的数值类型
	@Override
	public int getRowCount() { // 重新返回表格总行数的方法
		return data.size();
	}
	@Override
	public int getColumnCount() { // 重写返回表格总列数方法
		return head.length;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) { // 重写返回单元格值的方法
		return data.get(rowIndex)[columnIndex];
	}
	@Override
	public String getColumnName(int col) { // 重写返回列名的函数
		return head[col];
	}
	@Override
	public Class getColumnClass(int c) { // 重写返回列值类型方法
		return typeArray[c];
	}
	@Override
	public boolean isCellEditable(int r, int c) { // 除了第二列其它列不可编辑
		if (c == 1)
			return true;
		else
			return false;
	}
	@Override
	public void setValueAt(Object Value, int r, int c) { // 重写写入表格值方法
		data.get(r)[c] = Value;
		this.fireTableCellUpdated(r, c);
	}
	public void addRow(Object[] str) { // 定义表格可以添加添加行
		data.add(str);
		this.fireTableDataChanged();
	}
	public void removeRow(int r) { // 定义表格可以删除添加行
		data.remove(r);
		this.fireTableDataChanged();
	}
	public void clearAll() { // 定义表格可以清空方法
		data.clear();
	}
}
