package com.bn.Recommend;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;
public class PrimaryRecommendTableModel extends AbstractTableModel
{
	/**
	 * 重新定义表格模型
	 */
	public static ArrayList<Object[]> data=new  ArrayList<Object[]>();
	//定义一个二维数组		
	String head[]={"推荐编号","图片","推荐语","推荐时间","删除","详细"};				//创建列表题字符串数组
	//创建表示各个列类型的类型数组
	@SuppressWarnings("rawtypes")
	Class[] typeArray={String.class,Icon.class,String.class,String.class,Object.class,Object.class};
	//重写getRowCount()方法
	@Override
	public int getRowCount() 
	{
		return data.size();
	}
	//重写getRowCount()方法
	@Override
	public int getColumnCount() {
		return head.length;
	}
	//重写getValueAt()方法
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data.get(rowIndex)[columnIndex];
	}
	//重写getColumnName()方法
	@SuppressWarnings({ })
	@Override
	public String getColumnName(int col)
	{
		return head[col];
	}
	//重写getColumnClass()方法
	@Override
	public Class getColumnClass(int c)
	{
		return typeArray[c];
	}
	//重写isCellEditable()方法
	@Override
	public boolean isCellEditable(int r,int c)
	{
		return false;
	}
	//重写setValueAt()方法
	@Override
	public void setValueAt(Object Value,int r,int c)
	{
		data.get(r)[c]=Value;
		this.fireTableCellUpdated(r, c);
	}
	public void addRow(Object[] str)
	{
		data.add(str);
		this.fireTableDataChanged();		
	}
	public void removeRow(int r)
	{
		data.remove(r);
		this.fireTableDataChanged();
	}
	
}
