package com.bn.Uid;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.bn.NetUtil.NetIO;
import com.bn.menu.MenuDetailPanel;
import com.bn.menu.TableForDetailMenu;
import com.bn.primary.PrimaryFrame;
import com.bn.random.TableForRandomDetail;
import com.bn.util.LookPicFrame;
public class UidInfoPanel extends JPanel implements ActionListener 
{
	/**
	 * 用户详细信息
	 */
	static String picPath;
	static String uid;
	JLabel jlTitle = new JLabel("用户详细信息");
	JLabel jluid=new JLabel("用户名：");
	static JTextField jluid1 = new JTextField("");

	private JLabel jlhead = new JLabel("头像：");
	static JLabel jl=new JLabel(new ImageIcon("res/img/txzs.png"));
	JLabel[] jls={			 
			new JLabel("关  注 ："),new JLabel("粉  丝："),new JLabel("菜  谱：")
			,new JLabel("随  拍 ："),new JLabel("菜谱收藏："),new JLabel("随拍收藏：")
	};
	JButton jback=new JButton("返回");
	static DefaultTableModel[] dtms;
	static JTable[] jts;
	JScrollPane[] jsps;

	public UidInfoPanel()
	{	 
		this.setLayout(null);
		jlTitle.setFont(new Font("宋体", Font.BOLD, 20));
		jlTitle.setBounds(330, 10, 150, 20);
		this.add(jlTitle);
		this.add(jluid);
		jluid.setBounds(30, 60, 80, 30);
		this.add(jluid1);
		jluid1.setBounds(90, 60, 150, 30);
		jl.setToolTipText("双击查看大图");
		jl.addMouseListener(new MyMouseAdapter());
		this.add(jlhead);
		jlhead.setBounds(290, 60, 80, 30);
		this.add(jl);
		jl.setBounds(340, 50, 50, 50);
		
		dtms=new DefaultTableModel[jls.length];
		jts=new JTable[jls.length];
		jsps=new JScrollPane[jls.length];

		int j=0;
		for(int i=0;i<jls.length;)
		{
			this.add(jls[i]);
			jls[i].setBounds(30+i%2*400, 130+j*170,100,30);
			dtms[i] = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			jts[i]=new JTable(dtms[i]);
			jsps[i]=new JScrollPane(jts[i]);
			this.add(jsps[i]);
			jsps[i].setBounds(100+i%2*400, 140+j*170,280,140);
			i++;
			if(i%2==0)
				j++;
		}
		this.add(jback);
		jback.setBounds(380, 640, 120, 30);
		jback.addActionListener(this);
		jts[0].addMouseListener(new MyAttentionAdapter());
		jts[2].addMouseListener(new MyMenuAdapter());
		jts[3].addMouseListener(new MyRandomAdapter());
		jts[4].addMouseListener(new MyMenuCAdapter());
		jts[5].addMouseListener(new MyRandomCAdapter());

	}

	public static void UserInfo()
	{
		String remsg=NetIO.getUidMessage(uid);
		System.out.println("remsg "+remsg);
		String[] msg1=remsg.split("<#>");
		jluid1.setText(uid);
		picPath=msg1[2];
		byte[] bb=NetIO.getImagebyte(picPath);
		ImageIcon icon = new ImageIcon(bb);
		icon.setImage(icon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		jl.setIcon(icon);
		new TablesForUidInfo(uid,jts,dtms);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jback)
		{
			PrimaryFrame.cl.show(PrimaryFrame.jpall, "manageUidPanel");
		}
	}
	private class MyMouseAdapter extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()==2)
			{
				ImageIcon icon=(ImageIcon) jl.getIcon();
				new LookPicFrame(icon,"用户头像");
			}			
		}
	}
	private class MyAttentionAdapter extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			Point point=e.getPoint();
			int col=jts[0].columnAtPoint(point);
			int row=jts[0].rowAtPoint(point);
			System.out.println("col "+col+"　"+row);
			if(col==1)
			{								
				String delete=(String) dtms[0].getValueAt(row, 0);
				String uid=jluid1.getText().trim();
				System.out.println("out "+uid+"　"+delete);
				if(NetIO.cancelAttention(uid,delete))
				{
					dtms[0].removeRow(row);
				}	
			}
		}		
	}
	private class MyMenuAdapter extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			Point point=e.getPoint();
			int col=jts[2].columnAtPoint(point);
			int row=jts[2].rowAtPoint(point);
			
			if(col==2)
			{
				String menuId=(String)dtms[2].getValueAt(row, 0);
				String mess=NetIO.searchMenu(menuId);
				System.out.println("out "+menuId+"　"+mess);
				new TableForDetailMenu(mess,menuId,"uidInfoPanel");			
				PrimaryFrame.cl.show(PrimaryFrame.jpall, "menuDetailPanel");
			}
		}		
	}
	private class MyRandomAdapter extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			Point point=e.getPoint();
			int row=jts[3].rowAtPoint(point);
			int col=jts[3].columnAtPoint(point);
			if(col==3)
			{
				String id=(String)dtms[3].getValueAt(row, 0);
				String content=NetIO.getRandomById(id);
				new TableForRandomDetail(content, "uidInfoPanel");
				PrimaryFrame.cl.show(PrimaryFrame.jpall, "detailRandomPanel");
			}
		}		
	}
	private class MyMenuCAdapter extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			Point point=e.getPoint();
			int row=jts[4].rowAtPoint(point);
			int col=jts[4].columnAtPoint(point);
			if(col==2)
			{
				String uid=jluid1.getText().trim();
				String menuId=(String) dtms[4].getValueAt(row, 0);
				if(NetIO.deleteMenuC(uid,menuId))
				{
					dtms[4].removeRow(row);
				}
			}
		}		
	}
	private class MyRandomCAdapter extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			Point point=e.getPoint();
			int row=jts[5].rowAtPoint(point);
			int col=jts[5].columnAtPoint(point);
			if(col==2)
			{
				String randomId=(String) dtms[5].getValueAt(row, 0);
				String uid=jluid1.getText().trim();
				if(NetIO.deleteRandomC(uid,randomId))
				{
					dtms[5].removeRow(row);
				}
			}
		}		
	}	
}