package com.bn.random;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.bn.NetUtil.NetIO;
import com.bn.primary.PrimaryFrame;
import com.bn.util.Constant;
/*
 * 随拍管理界面
 */
public class ManageRandomPanel extends JPanel
implements ActionListener
{
	private JLabel jlTitle = new JLabel("随拍信息管理");
	private JLabel jlSelect = new JLabel("选择查询条件：");
	private JLabel jlcity = new JLabel("所在城市：");
	private JLabel jltab = new JLabel("所属标签：");
	private JLabel jlexamine=new JLabel("状态:");

	private JComboBox jctab = new JComboBox();
	private JComboBox jccity = new JComboBox(Constant.city);
	private JComboBox jcstate=new JComboBox(Constant.state);
	private JButton jblook = new JButton("查看");
	
	private JScrollPane jsp;
	private DefaultTableModel dtm;
	private JTable jt;
	
	public ManageRandomPanel()
	{
		this.setLayout(null);
		jlTitle.setFont(new Font("宋体",Font.BOLD,20));
		jlTitle.setBounds(330, 10, 150, 20);
		jlSelect.setBounds(10, 45, 120, 20);
		this.add(jlSelect);
		this.add(jlTitle);
		
		jlcity.setBounds(130, 45, 80, 20);
		this.add(jlcity);
		jccity.setBounds(200, 40, 80, 25);
		this.add(jccity);		
		jltab.setBounds(300, 45, 100, 20);
		this.add(jltab);
		
		jlexamine.setBounds(500, 45, 80, 20);	
		this.add(jlexamine);		
		jcstate.setBounds(550, 40, 80, 25);
		this.add(jcstate);
		jctab.addItem("所有");
		for(int j=0;j<Constant.label.length;j++)
		{
			jctab.addItem(Constant.label[j]);
		}
		jctab.setBounds(370, 40, 80, 25);
		this.add(jctab);		
		jblook.setBounds(800, 40, 80, 25);
		this.add(jblook);
		jblook.addActionListener(this);		
		dtm = new DefaultTableModel()
		{
			public boolean isCellEditable(int row,int column){  
				return false;  
				}
		};
		jt = new JTable(dtm);
		jt.setAutoResizeMode(0);
		jsp = new JScrollPane(jt);
		jsp.setBounds(40, 85, 880, 500);
		this.add(jsp);		
		jt.addMouseListener(new MyMouseAdapter());
		String[][] content=NetIO.getRandomByCondition(Constant.NO_MESSAGE,Constant.NO_MESSAGE,Constant.NO_MESSAGE);
		
		if(!content[0][0].equals(Constant.NO_MESSAGE))
		new TableForRandom(jt,dtm,content);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jblook)
		{
			String label=Constant.NO_MESSAGE;
			String city=Constant.NO_MESSAGE;
			String state=Constant.NO_MESSAGE;
			if(jctab.getSelectedIndex()!=0)
			{
				label = (String)jctab.getSelectedItem();
			}
			if(jccity.getSelectedIndex()!=0)
			{
				city = (String)jccity.getSelectedItem();
			}
			if(jcstate.getSelectedIndex()!=0)
			{
				state=""+(jcstate.getSelectedIndex()==1?1:0);
			}
			String[][] content=NetIO.getRandomByCondition(city,label,state);
			if(content[0][0].equals(Constant.NO_MESSAGE))
			{
				JOptionPane.showMessageDialog(null, "查询结果为空");
				return;
			}
			new TableForRandom(jt,dtm,content);
		}
	}	
	private class MyMouseAdapter extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent e) {
			Point point=e.getPoint();		
			int row=jt.rowAtPoint(point);
			int column=jt.columnAtPoint(point);
			if(column==5)
			{
				String randomId=(String)jt.getValueAt(row, 0);
				String s=(String) dtm.getValueAt(row, column);
				Boolean flag=Boolean.parseBoolean(s);
				s=flag?"false":"true";
				if(flag)
				{
					if(NetIO.isExcellentRandom(randomId))
					{
						JOptionPane.showMessageDialog(null, "该随拍是推荐随拍,请先取消推荐后再禁止");
						return;
					}
					NetIO.forbitRandom(randomId);
					dtm.setValueAt(s, row, column);
				}else
				{
					NetIO.permitRandom(randomId);
					dtm.setValueAt(s, row, column);

				}
			}
			if(column==6)
			{
				String id=(String)jt.getValueAt(row, 0);
				String content=NetIO.getRandomById(id);
				PrimaryFrame.cl.show(PrimaryFrame.jpall, "detailRandomPanel");
				new TableForRandomDetail(content, "manageRandomPanel");
			}		
		}	
	}
}
