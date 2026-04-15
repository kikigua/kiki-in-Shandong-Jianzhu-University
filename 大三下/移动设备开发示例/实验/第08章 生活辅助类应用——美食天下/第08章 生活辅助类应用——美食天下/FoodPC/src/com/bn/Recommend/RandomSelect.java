package com.bn.Recommend;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.bn.NetUtil.NetIO;
import com.bn.random.TableForRandom;
import com.bn.util.Constant;
import com.bn.util.TranslateUtil;

public class RandomSelect extends JFrame
implements ActionListener
{
	private static final long serialVersionUID = 2460738954379744517L;
	
	private JLabel jlTitle = new JLabel("随机信息管理");
	private JLabel jlSelect = new JLabel("选择查询条件：");
	private JLabel jlcity = new JLabel("所在城市：");
	private JLabel jltab = new JLabel("所属标签：");

	private JComboBox jctab = new JComboBox();
	private JComboBox jccity = new JComboBox(Constant.city);

	
	private JButton jblook = new JButton("查看");
	
	private JScrollPane jsp;
	private DefaultTableModel dtm;
	private JTable jt;
	private JPanel jp;
	JButton jb_ok=new JButton("确定");
	JButton jb_cancel=new JButton("取消");
	DefaultTableModel tmR;
	ManageRecommendPanel rp;
	public RandomSelect(ManageRecommendPanel rp,DefaultTableModel tmR)
	{
		this.rp=rp;
		this.tmR=tmR;
		jp=new JPanel(null);
		jlTitle.setFont(new Font("宋体",Font.BOLD,20));
		jlTitle.setBounds(330, 10, 150, 20);
		jlSelect.setBounds(10, 45, 120, 20);
		jp.add(jlSelect);
		jp.add(jlTitle);
		
		jlcity.setBounds(130, 45, 80, 20);
		jp.add(jlcity);
		jccity.setBounds(200, 40, 80, 25);
		jp.add(jccity);		
		jltab.setBounds(300, 45, 100, 20);
		jp.add(jltab);
		jctab.addItem("所有");
		for(String s:Constant.label)
		{
			jctab.addItem(s);
		}
		jctab.setBounds(370, 40, 120, 25);
		jp.add(jctab);		
		jblook.setBounds(700, 40, 80, 25);
		jp.add(jblook);
		jblook.addActionListener(this);		
		dtm = new DefaultTableModel();
		jt = new JTable(dtm);
		jt.setAutoResizeMode(0);
		jsp = new JScrollPane(jt);
		jsp.setBounds(40, 85, 810, 300);
		jb_cancel.setBounds(750, 400, 80, 25);
		jp.add(jb_cancel);
		jb_cancel.addActionListener(this);
		jb_ok.setBounds(850, 400, 80, 25);
		jp.add(jb_ok);
		jb_ok.addActionListener(this);
		jp.add(jsp);		
		this.add(jp);
		this.setBounds(100,100,950,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		String[][] content=NetIO.getRandomLeft(Constant.NO_MESSAGE,Constant.NO_MESSAGE);
		new TableForRandomSelect(jt,dtm,content);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jblook)
		{
			String label=Constant.NO_MESSAGE;
			String city=Constant.NO_MESSAGE;
			if(jctab.getSelectedIndex()!=0)
			{
				label = (String)jctab.getSelectedItem();
			}
			if(jccity.getSelectedIndex()!=0)
			{
				city = (String)jccity.getSelectedItem();
			}				
			String[][] content=NetIO.getRandomLeft(city, label);
			if(content[0][0].equals(Constant.NO_MESSAGE))
			{
				JOptionPane.showMessageDialog(null, "查询结果为空");
				return;
			}
			new TableForRandomSelect(jt,dtm,content);
		}else if(e.getSource()==jb_cancel)
		{
			this.dispose();
		}else if(e.getSource()==jb_ok)
		{
			int column=dtm.getColumnCount();
			int row=dtm.getRowCount();
			List<String> list=new ArrayList<String>();
			for(int i=0;i<row;i++)
			{
				Boolean flag=(Boolean) dtm.getValueAt(i, column-1);
				if(flag!=null&&flag)
				{
					String id=(String) dtm.getValueAt(i, 0);
					list.add(id);
				}
			}
			//已推荐的 加上添加的不是双数
			if((tmR.getRowCount()+list.size())%2!=0)
			{
				JOptionPane.showMessageDialog(null, "请取消一个或再添加一个");
				return;
			}
			if(list.size()==0)
			{
				return;
			}
			for(String randomId:list)
			{
				Boolean flag=NetIO.addExcelletnRandom(randomId);
				if(!flag)
				{
					JOptionPane.showMessageDialog(null, "添加失败");
					return;
				}				
			}
			JOptionPane.showMessageDialog(null, "添加成功");
			rp.notifactionRandom();
			this.dispose();
		}
	}		
}
