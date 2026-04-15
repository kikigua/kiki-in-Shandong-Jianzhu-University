package com.bn.menu;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
public class ManageMenuForbidCellRenderer 
implements TableCellRenderer{				//实现绘制当前Cell单元数值内容接口
	JButton jlpic=new JButton();		//创建一个编辑的按钮对象
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Boolean flag=Boolean.parseBoolean((String) value);		//当前菜品状态
		jlpic.setText(flag?"禁止":"通过");	//设置按钮显示内容
		return jlpic;						//将当前的单元格设置为编辑按钮
	}
}
