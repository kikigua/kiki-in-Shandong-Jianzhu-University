package com.bn.Uid;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
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

public class ManageUidPanel extends JPanel
implements ActionListener
{	
	/**
	 *	用户管理界面
	 */
	private static final long serialVersionUID = 5653198269433147099L;
	public static DefaultTableModel dtm;//=new DefaultTableModel();
	public static JTable jt;
	private JScrollPane jsp;
	
	private JLabel jlTitle = new JLabel("用户信息管理");
	private JLabel jlSelect = new JLabel("选择查询条件：");
	private JLabel jlsex = new JLabel("性别：");
	private JLabel jltime= new JLabel("加入日期：");
	private JComboBox jccsex=new JComboBox(new String[]{"所有","男","女","保密"});
	private JComboBox[] jccs = 
			{	
				new JComboBox(),	//年、月、日
				new JComboBox(),	
				new JComboBox()
			};
	private JButton jblook = new JButton("查询");	
	public ManageUidPanel()
	{		
		this.setLayout(null);
		jlTitle.setFont(new Font("宋体",Font.BOLD,20));
		jlTitle.setBounds(330, 10, 150, 20);
		this.add(jlTitle);
		jlSelect.setBounds(10, 45, 120, 20);
		this.add(jlSelect);
		jlsex.setBounds(120, 45, 60, 20);
		this.add(jlsex);
		jccsex.setBounds(160, 40, 80, 30);
		this.add(jccsex);
		jltime.setBounds(265, 45, 100, 20);
		this.add(jltime);
		for(int i=0;i<jccs.length;i++)
		{
			jccs[i].addItem("所有");
		}
		for(int i=2000;i<=2015;i++)
		{
			jccs[0].addItem(i);
		}
		for(int i=1;i<=12;i++)
		{
			if(i<10)
				jccs[1].addItem("0"+i);
			else				
			jccs[1].addItem(i+"");
		}
		for(int i=1;i<=31;i++)
		{
			if(i<10)
			{
				jccs[2].addItem("0"+i);
			}else				
			jccs[2].addItem(i+"");
		}

		for(int i=0;i<jccs.length;i++)
		{
			jccs[i].setBounds(340+i*100, 40, 80, 30);
			jccs[i].setSelectedItem("");
			jccs[i].addActionListener(this);
			this.add(jccs[i]);
			if(i!=jccs.length-1)
			{
				JLabel jline = new JLabel("-");
				jline.setBounds(430+i*100, 50, 10, 10);
				this.add(jline);
			}			
		}
		jblook.setBounds(670, 40, 80, 30);
		this.add(jblook);
		jblook.addActionListener(this);
		dtm=new DefaultTableModel()
		{
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}			
		};
		jt=new JTable(dtm);
		jsp=new JScrollPane(jt);
		this.add(jsp);
		jt.setAutoResizeMode(0);
		jsp.setBounds(20, 80, 860, 500);
		String[][] message = NetIO.getUser(Constant.NO_MESSAGE,Constant.NO_MESSAGE,Constant.NO_MESSAGE);
		if(!message[0][0].endsWith(Constant.NO_MESSAGE))
		new TableForManageUid(jt,dtm,message);
		jt.addMouseListener(new MyMouseAdapter());
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jblook)
		{
			String[] formats = new String[] { "%Y", "%m", "%d" };
			StringBuffer datebf=new StringBuffer();
			StringBuffer dateformatebf=new StringBuffer();
			for(int i = 0; i < jccs.length; i++)
			{
				if(jccs[i].getSelectedIndex()!=0)
				{
					datebf.append(jccs[i].getSelectedItem()+"-");
					dateformatebf.append(formats[i]+"-");
				}
			}
			String date=Constant.NO_MESSAGE;
			String dateformate=Constant.NO_MESSAGE;
			String sex=Constant.NO_MESSAGE;
			if(jccsex.getSelectedIndex()!=0)
			{
				sex=(String) jccsex.getSelectedItem();
			}
			if(datebf.length()>0)
			{
				date=datebf.substring(0, datebf.length()-1);
				dateformate=dateformatebf.substring(0, dateformatebf.length()-1);
			}
			String[][] message = NetIO.getUser(sex, dateformate, date);
			System.out.println(message);
			if(message[0][0].equals("No Message")) {
				JOptionPane.showMessageDialog(this, "没有相关信息！");
				return;
			}else {
				new TableForManageUid(jt,dtm,message);
			}
		}else if(e.getSource()==jccs[0]||e.getSource()==jccs[1])
		{
			String month=(String) jccs[1].getSelectedItem();
			System.out.println("month "+month);
			setDays(month);
		}
	}
	void setDays(String month) {
		List<String> ls = new ArrayList<String>();
		ls.add("0" + 2);
		ls.add("0" + 4);
		ls.add("0" + 6);
		ls.add("0" + 9);
		ls.add("11");
		if (ls.contains(month)) {
			while (jccs[2].getItemCount() < 31) {
				int next = Integer.parseInt((String) jccs[2].getItemAt(jccs[2]
						.getItemCount() - 1)) + 1;
				jccs[2].insertItemAt(next + "", jccs[2].getItemCount());
			}
			while (jccs[2].getItemCount() > 31) {
				jccs[2].removeItemAt(jccs[2].getItemCount() - 1);
			}
		} else if (month.equals("02")) {
			Integer nowyear = Integer.parseInt((String) jccs[0]
					.getSelectedItem());
			if (nowyear % 4 != 0) {
				while (jccs[2].getItemCount() > 29) {
					System.out.println("4 jctime[2].getItemCount() "
							+ jccs[2].getItemCount());
					jccs[2].removeItemAt(jccs[2].getItemCount() - 1);
				}
			} else {
				while (jccs[2].getItemCount() > 30) {
					jccs[2].removeItemAt(jccs[2].getItemCount() - 1);
				}
				while (jccs[2].getItemCount() < 30) {
					int next = Integer.parseInt((String) jccs[3]
							.getItemAt(jccs[2].getItemCount() - 1)) + 1;
					jccs[2].insertItemAt(next + "", jccs[2].getItemCount());
				}
			}
		} else {
			while (jccs[2].getItemCount() < 32) {
				int next = Integer.parseInt(((String) jccs[2].getItemAt(jccs[2]
						.getItemCount() - 1))) + 1;
				jccs[2].insertItemAt(next + "", jccs[2].getItemCount());
			}
		}
	}
	private class MyMouseAdapter extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent e) {
			Point point=e.getPoint();		
			int row=jt.rowAtPoint(point);
			int col=jt.columnAtPoint(point);
			int column=jt.getColumnCount();
			if(col==column-1)
			{
				
				PrimaryFrame.cl.show(PrimaryFrame.jpall, "uidInfoPanel");		
				final String uid=(String) jt.getValueAt(row, 0);
				System.out.println(uid);
				new Thread()
				{
					@Override
					public void run()
					{
						UidInfoPanel.uid = uid;
						UidInfoPanel.UserInfo();
					}
				}.start();

			}		
		}
	}
}
