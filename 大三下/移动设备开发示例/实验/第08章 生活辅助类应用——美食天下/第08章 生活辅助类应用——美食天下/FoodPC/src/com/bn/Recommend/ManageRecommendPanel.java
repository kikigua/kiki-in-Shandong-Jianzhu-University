package com.bn.Recommend;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.bn.NetUtil.NetIO;
import com.bn.menu.ProcessJFrame;
import com.bn.menu.TableForDetailMenu;
import com.bn.primary.PrimaryFrame;
import com.bn.random.TableForRandomDetail;
import com.bn.util.Constant;
import com.bn.util.LookPicFrame;
/*
 * 推荐管理界面
 */
public class ManageRecommendPanel extends JPanel implements ActionListener {
	JLabel jlTitle = new JLabel("推荐信息管理");
	JLabel primary = new JLabel("主推荐:");
	JLabel menuL = new JLabel("精品菜品:");
	JLabel randomL = new JLabel("精品随拍:");
	JButton addPrimary = new JButton("添加");
	JButton addmenu = new JButton("添加");
	JButton addrandom = new JButton("添加");
	PrimaryRecommendTableModel prtm;
	DefaultTableModel tmM;
	DefaultTableModel tmR;
	JTable jtP;
	JTable jtM;
	JTable jtR;
	JScrollPane jspP;
	JScrollPane jspM;
	JScrollPane jspR;
	static List<String[]>  priContent;
	public ManageRecommendPanel() {
		this.setLayout(null);
		jlTitle.setFont(new Font("宋体", Font.BOLD, 20));
		jlTitle.setBounds(350, 10, 150, 20);
		this.add(jlTitle);
		primary.setBounds(60, 60, 80, 20);
		this.add(primary);
		addPrimary.setBounds(750, 55, 80, 20);
		this.add(addPrimary);
		prtm = new PrimaryRecommendTableModel();
		jtP = new JTable(prtm);
		jspP = new JScrollPane(jtP);
		jspP.setBounds(120, 80, 700, 160);
		this.add(jspP);
		menuL.setBounds(50, 280, 100, 20);
		this.add(menuL);
		addmenu.setBounds(750, 270, 80, 20);
		this.add(addmenu);
		tmM = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jtM = new JTable(tmM);
		jspM = new JScrollPane(jtM);
		jspM.setBounds(120, 300, 700, 160);
		this.add(jspM);

		randomL.setBounds(50, 490, 100, 20);
		this.add(randomL);
		addrandom.setBounds(750, 480, 80, 20);
		this.add(addrandom);
		tmR = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}

		};
		jtR = new JTable(tmR);
		jspR = new JScrollPane(jtR);
		jspR.setBounds(120, 510, 700, 160);
		this.add(jspR);

		
		jtP.addMouseListener(new MyPrimaryMouseAdapter());
		jtM.addMouseListener(new MyMenuMouseAdapter());
		jtR.addMouseListener(new MyRandomMouseAdapter());
		priContent = NetIO.getPrimaryRecomment();
		if (!priContent.get(0)[0].equals(Constant.NO_MESSAGE))
			new TableForPrimary(prtm, jtP, priContent);
		String[][] menuInfo = NetIO.getExcellentMenuPC();
		if (!menuInfo[0][0].equals(Constant.NO_MESSAGE))
			new TableForMenuRecommend(jtM, tmM, menuInfo);
		String[][] randomInfo = NetIO.getExcellentRandomPC();
		if (!randomInfo[0][0].equals(Constant.NO_MESSAGE))
			new TableForRandomRecommend(jtR, tmR, randomInfo);
		addPrimary.addActionListener(this);
		addmenu.addActionListener(this);
		addrandom.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addPrimary) {
			new AddPrimaryRecommend(this);
		} else if (e.getSource() == addmenu) {
			new MenuSelect(this, tmM);
		} else if (e.getSource() == addrandom) {
			new RandomSelect(this, tmR);
		}
	}

	public void notifacationPrimry() {
		priContent = NetIO.getPrimaryRecomment();
		new TableForPrimary(prtm, jtP, priContent);
	}

	public void notifactionMenu() {
		String[][] menuInfo = NetIO.getExcellentMenuPC();
		if (!menuInfo[0][0].equals(Constant.NO_MESSAGE))
			new TableForMenuRecommend(jtM, tmM, menuInfo);
	}

	public void notifactionRandom() {
		String[][] randomInfo = NetIO.getExcellentRandomPC();
		if (!randomInfo[0][0].equals(Constant.NO_MESSAGE))
			new TableForRandomRecommend(jtR, tmR, randomInfo);
	}

	private class MyMenuMouseAdapter extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			Point mousepoint = e.getPoint();
			int column = jtM.columnAtPoint(mousepoint);
			int row = jtM.rowAtPoint(mousepoint);
			if (column == 4) {
				String id = (String) jtM.getValueAt(row, 0);
				String mess = NetIO.searchMenu(id);
				new TableForDetailMenu(mess, id, "recommendPanel");
				PrimaryFrame.cl.show(PrimaryFrame.jpall, "menuDetailPanel");
			} else if (column == 3) {
				String menuId = (String) jtM.getValueAt(row, 0);
				if (NetIO.deleteExcelletnMenu(menuId)) {
					tmM.removeRow(row);
					if (tmM.getRowCount() % 2 != 0) {
						JOptionPane
								.showMessageDialog(null, "删除成功,请再删除一个,或添加一个");

					} else {
						JOptionPane.showMessageDialog(null, "删除成功");
					}
				} else {
					JOptionPane.showMessageDialog(null, "删除失败");
				}
			}
		}
	}

	private class MyRandomMouseAdapter extends MouseAdapter {
		public void mouseClicked(java.awt.event.MouseEvent e) {
			Point mousepoint = e.getPoint();
			int column = jtR.columnAtPoint(mousepoint);
			int row = jtR.rowAtPoint(mousepoint);
			if (column == 3) {
				String id=(String)jtR.getValueAt(row, 0);
				String content=NetIO.getRandomById(id);
				new TableForRandomDetail(content, "recommendPanel");
				PrimaryFrame.cl.show(PrimaryFrame.jpall, "detailRandomPanel");
			} else if (column == 2) {
				String randomId=(String)jtR.getValueAt(row, 0);
				if(NetIO.deleteExcelletnRandom(randomId))
				{	
					tmR.removeRow(row);
					if(jtR.getRowCount()%2!=0)
					{
						JOptionPane.showMessageDialog(null, "删除成功,请再删除一个,或添加一个");
					}else
					{
						JOptionPane.showMessageDialog(null, "删除成功");
					}
				}else{
					JOptionPane.showMessageDialog(null, "删除失败");
				}
			}				
		}
	}
	private class MyPrimaryMouseAdapter extends MouseAdapter {
		public void mouseClicked(java.awt.event.MouseEvent e) {
			Point mousepoint = e.getPoint();
			int column = jtP.columnAtPoint(mousepoint);
			int row = jtP.rowAtPoint(mousepoint);
			if (column == 1) {
				String picName=(String) prtm.getValueAt(row, 5);
				byte[] bb=NetIO.getImagebyte(picName);
				ImageIcon ii=new ImageIcon(bb);
				if(ii!=null)
				new LookPicFrame(ii, "推荐图片展示");
			} else if (column == 5) {
				String picName = (String) priContent.get(row)[1];
				byte[] bb = NetIO.getImagebyte(picName);
				ImageIcon ii = new ImageIcon(bb);
				String info = (String) prtm.getValueAt(row, 2);
				new ProcessJFrame(ii, info, "推荐详情");
			} else if (column == 4) {
				String id = (String) prtm.getValueAt(row, 0);
				if (NetIO.deletePrimaryRecommend(id)) {
					prtm.removeRow(row);
					if (prtm.getRowCount()<=4) {
						JOptionPane.showMessageDialog(null, "删除成功,推荐太少，请添加一个");
					} else {
						JOptionPane.showMessageDialog(null, "删除成功");
					}
				}
			}
		}
	}
}
