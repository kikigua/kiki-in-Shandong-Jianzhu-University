package com.bn.menu;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.bn.NetUtil.NetIO;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
/**
 * 编辑器
 */
public class DetailMenuDetailCellEditor extends AbstractCellEditor implements
		TableCellEditor, ActionListener {	
	JButton unitJCB = new JButton("点击查看");		//定义一个按钮
	String[] message;								//声明制一个字符串数组
	String[] imagePath;								//声明制一个字符串数组
	public DetailMenuDetailCellEditor(String[] message, String[] imagePath) {
		this.message = message;
		this.imagePath = imagePath;
		unitJCB.addActionListener(this);		//给按钮添加监听
	}
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		return unitJCB;						//将当前单元格的内容设置为编辑按钮
	}
	@Override
	public Object getCellEditorValue() {		//返回单元格编辑时显示的值
		return unitJCB;
	}
	@Override
	public void actionPerformed(ActionEvent e) {	
		int row = MenuDetailPanel.jtP.getSelectedRow();//获取选择的行数
		String order = (String) MenuDetailPanel.jtP.getValueAt(row, 0);//获取选择的制作步骤
		byte[] bb = NetIO.getImagebyte(imagePath[row]);//获取菜品制该步骤的图片
		new ProcessJFrame(new ImageIcon(bb), message[row],order);//创建新窗口显示步骤详情
	}
}
