package com.bn.Pinglun;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
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
import com.bn.util.Constant;

public class PinglunPanel extends JPanel implements ActionListener {

	/**
	 * 评论信息界面
	 */
	private static final long serialVersionUID = 5653198269433147099L;
	public static DefaultTableModel dtm;// =new DefaultTableModel();
	public static JTable jt;// =new JTable(dtm);
	private JScrollPane jsp;// =new JScrollPane(jt);
	public static MouseListener mouseListener;
	private JLabel jlTitle = new JLabel("评论信息管理");
	private JLabel jlSelect = new JLabel("选择查询条件：");
	private JLabel jlsex = new JLabel("类型");
	private JLabel jltime = new JLabel("评论日期：");
	private JLabel jlstate = new JLabel("状态");
	private JComboBox jcbtype = new JComboBox(new String[] { "菜品", "随拍" });
	private JComboBox[] jccs = { new JComboBox(), // 年
			new JComboBox(), // 月
			new JComboBox(), // 日
	};
	private JComboBox jcstate = new JComboBox(new String[] { "所有", "禁止", "通过" });
	private JButton jblook = new JButton("查询");

	public PinglunPanel() {
		this.setLayout(null);
		jlTitle.setFont(new Font("宋体", Font.BOLD, 20));
		jlTitle.setBounds(330, 10, 150, 20);
		this.add(jlTitle);
		jlSelect.setBounds(10, 45, 120, 20);
		this.add(jlSelect);
		jlsex.setBounds(120, 45, 60, 20);
		this.add(jlsex);
		jcbtype.setBounds(160, 40, 60, 30);
		this.add(jcbtype);
		jccs[0].setBounds(160, 40, 80, 30);
		this.add(jccs[0]);
		jltime.setBounds(265, 45, 100, 20);
		this.add(jltime);
		for (JComboBox jc : jccs) {
			jc.addItem("所有");
		}
		for (int i = 2000; i <= 2015; i++) {
			jccs[0].addItem(i + "");
		}
		for (int i = 1; i <= 12; i++) {
			if (i < 10)
				jccs[1].addItem("0" + i);
			else
				jccs[1].addItem(i + "");
		}
		for (int i = 1; i <= 31; i++) {
			if (i < 10) {
				jccs[2].addItem("0" + i);
			} else
				jccs[2].addItem(i + "");
		}
		for (int i = 0; i < jccs.length; i++) {
			jccs[i].setBounds(340 + i * 100, 40, 80, 30);
			jccs[i].addActionListener(this);
			this.add(jccs[i]);
			if (i != jccs.length - 1) {
				JLabel jline = new JLabel("-");
				jline.setBounds(430 + i * 100, 50, 10, 10);
				this.add(jline);
			}
		}
		jlstate.setBounds(640, 40, 80, 30);
		this.add(jlstate);
		jcstate.setBounds(680, 40, 80, 30);
		this.add(jcstate);
		jblook.setBounds(820, 40, 80, 30);
		this.add(jblook);
		jblook.addActionListener(this);
		dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jt = new JTable(dtm);
		jsp = new JScrollPane(jt);
		this.add(jsp);
		jt.setAutoResizeMode(0);
		jsp.setBounds(20, 80, 870, 500);
		String[][] content = NetIO.getMenuPinglun(Constant.NO_MESSAGE,
				Constant.NO_MESSAGE, Constant.NO_MESSAGE);
		if (!content[0][0].equals(Constant.NO_MESSAGE))
			new TableForPinglunPanel(jt, dtm, content,
					jcbtype.getSelectedIndex());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jblook) {
			StringBuffer dateFormate = new StringBuffer();// ;=Constant.NO_MESSAGE;
			StringBuffer date = new StringBuffer();// ;=Constant.NO_MESSAGE;
			String[] formats = new String[] { "%Y", "%m", "%d" };
			for (int i = 0; i < jccs.length; i++) {
				if (jccs[i].getSelectedIndex() != 0) {
					date.append(jccs[i].getSelectedItem() + "-");
					dateFormate.append(formats[i] + "-");
				}
			}
			String sDateformate = Constant.NO_MESSAGE;
			String sDate = Constant.NO_MESSAGE;
			String state = Constant.NO_MESSAGE;
			if (dateFormate.length() > 0) {
				sDateformate = dateFormate.substring(0,
						dateFormate.length() - 1);
				sDate = date.substring(0, date.length() - 1);
			}
			if (jcstate.getSelectedIndex() != 0) {
				state = jcstate.getSelectedIndex() - 1 + "";
			}
			String[][] contention = null;
			if (jcbtype.getSelectedIndex() == 0) {
				contention = NetIO.getMenuPinglun(sDateformate, sDate, state);
			} else {
				contention = NetIO.getRandomPinglun(sDateformate, sDate, state);
			}
			if (contention[0][0].equals(Constant.NO_MESSAGE)) {
				JOptionPane.showMessageDialog(null, "没有要查询的内容");
			} else {
				new TableForPinglunPanel(jt, dtm, contention,
						jcbtype.getSelectedIndex());
			}
		} else if (e.getSource() == jccs[1] || e.getSource() == jccs[0]) {
			String month = (String) jccs[1].getSelectedItem();
			System.out.println("set datye" + month);
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
}
