package com.bn.menu;

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
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import com.bn.NetUtil.NetIO;
import com.bn.primary.PrimaryFrame;
import com.bn.util.Constant;

public class ManageMenu extends JPanel implements ActionListener {

	/**
	 * 管理美食的界面
	 */
	public DefaultTableModel dtm;
	public JTable jt;
	private JScrollPane jsp;

	private JLabel jlTitle = new JLabel("菜品信息管理");
	private JLabel jlSelect = new JLabel("选择查询条件：");
	private JLabel jlstyle = new JLabel("菜系：");
	private JComboBox jcstyle = new JComboBox();
	private JComboBox jcflavour = new JComboBox();
	private JComboBox jcctime = new JComboBox();
	private JComboBox jcdifficuty = new JComboBox();
	private JComboBox[] jccs = new JComboBox[] { jcstyle, jcflavour, jcctime,
			jcdifficuty };
	String[][] items = new String[][] { Constant.style, Constant.flavour,
			Constant.ctime, Constant.difficulty };
	private JComboBox jcState = new JComboBox(Constant.state);
	private JLabel jlPeople = new JLabel("口味：");
	private JLabel jlHome = new JLabel("时间：");
	private JLabel jlFoodType = new JLabel("难度：");
	private JLabel jlState = new JLabel("状态: ");
	JButton jblook = new JButton("查询");

	public ManageMenu() {
		this.setLayout(null);
		jlTitle.setFont(new Font("宋体", Font.BOLD, 20));
		jlTitle.setBounds(330, 10, 200, 25);
		this.add(jlTitle);
		jlSelect.setBounds(10, 50, 120, 25);
		this.add(jlSelect);
		jlstyle.setBounds(120, 50, 100, 25);
		this.add(jlstyle);
		jcstyle.setBounds(160, 50, 80, 25);
		this.add(jcstyle);
		jlPeople.setBounds(255, 50, 60, 25);
		this.add(jlPeople);
		jcflavour.setBounds(300, 50, 60, 25);
		this.add(jcflavour);
		jlHome.setBounds(370, 50, 70, 25);
		this.add(jlHome);
		for (int i = 0; i < jccs.length; i++) {
			JComboBox jc = jccs[i];
			jc.addItem("所有");
			for (int j = 0; j < items[i].length; j++) {
				jc.addItem(items[i][j]);
			}
		}
		jcctime.setBounds(410, 50, 90, 25);
		this.add(jcctime);
		jlFoodType.setBounds(525, 50, 70, 25);
		this.add(jlFoodType);
		jcdifficuty.setBounds(570, 50, 70, 25);
		this.add(jcdifficuty);

		jlState.setBounds(670, 50, 70, 25);
		this.add(jlState);
		jcState.setBounds(720, 50, 70, 25);
		this.add(jcState);
		jblook.setBounds(850, 50, 80, 25);
		this.add(jblook);
		jblook.addActionListener(this);
		dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jt = new JTable(dtm);
		jt.setAutoResizeMode(0);
		jsp = new JScrollPane(jt);
		jt.addMouseListener(new MyMouseAdapter());
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setBounds(20, 100, 910, 500);
		this.add(jsp);
		String[][] content = NetIO.getMsgSelect(Constant.NO_MESSAGE + "<#>"
				+ Constant.NO_MESSAGE + "<#>" + Constant.NO_MESSAGE + "<#>"
				+ Constant.NO_MESSAGE);
		if (!content[0][0].equals(Constant.NO_MESSAGE))
			new TableForMenuManage(jt, dtm, content);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jblook) {					
			StringBuffer sb = new StringBuffer();		//创建查询添加字符串
			for (JComboBox jc : jccs) {					//获取查询条件
				if (jc.getSelectedIndex() == 0) {	
					sb.append(Constant.NO_MESSAGE + "<#>");	
				} else {
					sb.append(jc.getSelectedItem() + "<#>");
				}}
			if (jcState.getSelectedIndex() != 0) {		//获取菜品状态
				sb.append(jcState.getSelectedIndex() == 1 ? "1" : "0");
			} else {
				sb.append(Constant.NO_MESSAGE);
			}											//得到查询结果
			String[][] content = NetIO.getMsgSelect(sb.toString().trim());
			if (content[0][0].equals(Constant.NO_MESSAGE)) {
				JOptionPane.showMessageDialog(null, "查询结果为空");//查询结果提示
				return;
			}
			new TableForMenuManage(jt, dtm, content);		//将查询结果设置到表格中
		}
	}
	private class MyMouseAdapter extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			Point point = e.getPoint();
			int row = jt.rowAtPoint(point);
			int column = jt.columnAtPoint(point);
			if (column == 9) {						//禁止菜品
				String menuId = (String) jt.getValueAt(row, 0);
				String s = (String) dtm.getValueAt(row, column);
				Boolean flag = Boolean.parseBoolean(s);
				s = flag ? "false" : "true";			
				if (flag) {							//如果是推荐菜品，提示不能禁止
					if (NetIO.isExcellentMenu(menuId)) {
						JOptionPane.showMessageDialog(null,
								"该菜品是精品菜品，请先取消推荐后再禁止");
						return;
					}
					NetIO.forbitMenu(menuId);
					dtm.setValueAt(s, row, column);
				} else {
					NetIO.permitMenu(menuId);
					dtm.setValueAt(s, row, column);
				}	
			} else if (column == 10) {				//根据编号查看详细菜品信息
				String id = (String) jt.getValueAt(row, 0);
				String mess = NetIO.searchMenu(id);
				if (mess.equals("No Message")) {
					JOptionPane.showMessageDialog(null, "记录不完整，请补充");
					return;
				}
				PrimaryFrame.cl.show(PrimaryFrame.jpall, "menuDetailPanel");//切换到菜品详细信息界面
				new TableForDetailMenu(mess, id, "manageMenuPanel");	//设置菜品详细信息
			} else if (column == 11) {							//提示删除菜品信息
				int bool = JOptionPane	
						.showConfirmDialog(jt, "是否要删除？", "提示！",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
				if (bool == 0) {
					String id = (String) dtm.getValueAt(row, 0);	//删除菜品
					String uid = (String) dtm.getValueAt(row, 2);
					int v = NetIO.deleteMenu(id + "<#>" + uid);
					if (v >= 0) {
						JOptionPane.showMessageDialog(null, "已删除" + v + "条记录");
						dtm.removeRow(row);
					} else {
						JOptionPane.showMessageDialog(null, "删除失败");
					}
				}

			}

		}
	}
}
