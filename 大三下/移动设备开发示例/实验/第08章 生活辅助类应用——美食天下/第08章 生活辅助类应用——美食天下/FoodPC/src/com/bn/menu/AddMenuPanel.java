package com.bn.menu;

/**
 * 添加菜品
 */
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import com.bn.util.Constant;
import com.bn.util.LookPicFrame;
import com.bn.util.MyPicFileChooser;
import com.bn.util.UploadUtils;

public class AddMenuPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	static String primaryP;
	private JLabel jlTitle = new JLabel("添加菜品");
	private JLabel menuL = new JLabel("菜谱名称：");
	static JTextField menuT = new JTextField("");
	private JLabel picL = new JLabel("主    图：");
	public static JLabel jlpic = new JLabel();
	private JButton addPicB = new JButton("添加主图");
	private MyPicFileChooser jfc = new MyPicFileChooser();
	private JLabel[] labelArray = { new JLabel("菜   系："), new JLabel("口   味："),
			new JLabel("工   艺："), new JLabel("时   间："), new JLabel("厨   具："),
			new JLabel("难   度：") };
	JComboBox[] jcbbArray = { new JComboBox(Constant.style),
			new JComboBox(Constant.flavour), new JComboBox(Constant.craft),
			new JComboBox(Constant.ctime), new JComboBox(Constant.tool),
			new JComboBox(Constant.difficulty),

	};
	private JLabel foodL = new JLabel("主    料：");
	private JLabel vicL = new JLabel("配料调料：");
	private JLabel processL = new JLabel("制作步骤：");
	private JLabel intrL = new JLabel("简    介：");
	private JLabel skillL = new JLabel("温馨提示：");

	static DefaultTableModel dtmF = new DefaultTableModel();
	static DefaultTableModel dtmV = new DefaultTableModel();
	static ProcessTableModel dtmP = new ProcessTableModel();
	JTable jtF = new MyJTable(dtmF);
	JTable jtV = new MyJTable(dtmV);
	JTable jtP = new MyJTable(dtmP);
	public static JTextArea skilT = new JTextArea("");
	public static JTextArea intrT = new JTextArea("");
	private JScrollPane jspP = new JScrollPane(jtP);
	private JScrollPane jspI = new JScrollPane(intrT);
	private JScrollPane jspS = new JScrollPane(skilT);
	private JScrollPane jspF = new JScrollPane(jtF);
	private JScrollPane jspV = new JScrollPane(jtV);
	MyPicFileChooser mfc;
	JButton[] jbArray = { new JButton("增加"), new JButton("增加"),
			new JButton("增加"), new JButton("减少"), new JButton("减少"),
			new JButton("减少"), };
	private JButton okB = new JButton("提交");

	public AddMenuPanel() {
		mfc = new MyPicFileChooser();
		this.setLayout(null);
		jlTitle.setFont(new Font("宋体", Font.BOLD, 20));
		jlTitle.setBounds(350, 10, 150, 20);
		jspF.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jspP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jspV.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		skilT.setLineWrap(true); // 激活自动换行功能
		skilT.setWrapStyleWord(true); // 激活断行不断字功能
		intrT.setLineWrap(true); // 激活自动换行功能
		intrT.setWrapStyleWord(true); // 激活断行不断字功能

		this.add(jlTitle);
		menuL.setBounds(10, 50, 90, 20);
		this.add(menuL);
		menuT.setBounds(90, 45, 120, 25);
		this.add(menuT);
		picL.setBounds(380, 50, 90, 20);
		this.add(picL);
		jlpic.setToolTipText("双击查看大图");
		jlpic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (e.getClickCount() == 2&&primaryP!=null) {
					new LookPicFrame(primaryP, "主图");
				}else if(e.getClickCount() == 2&&primaryP==null){
					JOptionPane.showMessageDialog(null, "还没有选择主图");
				}
			}

		});
		jlpic.setBounds(450, 35, 110, 75);
		this.add(jlpic);
		addPicB.setBounds(665, 45, 100, 30);
		this.add(addPicB);
		addPicB.addActionListener(this);
		for (int i = 0; i < labelArray.length / 2; i++) {
			labelArray[i * 2].setBounds(420, 120 + 2 * i * 30, 90, 30);
			this.add(labelArray[i * 2]);
			labelArray[i * 2 + 1].setBounds(600, 120 + 2 * i * 30, 90, 30);
			this.add(labelArray[i * 2 + 1]);
			jcbbArray[i * 2].setBounds(500, 120 + 2 * i * 30, 80, 30);
			this.add(jcbbArray[i * 2]);
			jcbbArray[i * 2 + 1].setBounds(680, 120 + 2 * i * 30, 80, 30);
			this.add(jcbbArray[i * 2 + 1]);
		}
		foodL.setBounds(10, 125, 90, 20);
		this.add(foodL);
		jspF.setBounds(80, 125, 300, 90);
		this.add(jspF);
		jbArray[0].setBounds(250, 95, 70, 25);
		this.add(jbArray[0]);
		jbArray[3].setBounds(330, 95, 70, 25);
		this.add(jbArray[3]);
		vicL.setBounds(10, 250, 90, 20);
		this.add(vicL);
		jspV.setBounds(80, 250, 300, 90);
		this.add(jspV);
		jbArray[1].setBounds(250, 220, 70, 25);
		this.add(jbArray[1]);
		jbArray[4].setBounds(330, 220, 70, 25);
		this.add(jbArray[4]);
		jbArray[2].setBounds(630, 320, 70, 25);
		this.add(jbArray[2]);
		jbArray[5].setBounds(705, 320, 70, 25);
		this.add(jbArray[5]);
		processL.setBounds(10, 350, 90, 20);
		this.add(processL);
		jspP.setBounds(80, 350, 685, 85);
		this.add(jspP);
		intrL.setBounds(10, 450, 90, 20);
		this.add(intrL);
		jspI.setBounds(80, 450, 685, 70);
		this.add(jspI);
		skillL.setBounds(10, 550, 90, 20);
		this.add(skillL);
		jspS.setBounds(80, 550, 685, 70);
		this.add(jspS);
		okB.setBounds(370, 630, 80, 30);
		this.add(okB);
		okB.addActionListener(this);
		jtF.setAutoResizeMode(0);
		jtP.setAutoResizeMode(0);
		jtV.setAutoResizeMode(0);
		for (JButton jb : jbArray) {
			jb.addActionListener(this);
		}
		jtP.addMouseListener(new MyListener());
		new TableForAddMenu(jtF, jtV, jtP);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okB) {
			int rowCount = dtmP.getRowCount(); // 获取过程表格总行数
			if (primaryP == null || primaryP.length() <= 0) { // 如果没有选择主图，提示选择主图
				JOptionPane.showMessageDialog(null, "请选择主图");
				return;
			}
			List<String> picNames = new ArrayList<String>(); // 创建字符串集合
			for (int i = 0; i < rowCount; i++) { // 循环获取过程图片
				String path = (String) dtmP.getValueAt(i, 3);
				if (path == null || path.length() <= 0) { // 如果没有图片，提示补充图片
					JOptionPane.showMessageDialog(null, "请将图片补充完整");
					return;
				}
				picNames.add(path); // 将图片路径存入字符串集合
			}
			List<String> pross = getProcess(); // 获取制作过程简介
			String content = getContent(Constant.manager);
			if (pross == null || content == null) {
				return;
			}
			if (UploadUtils.upLoadMenu(primaryP, content, pross, picNames)) // 添加菜品
				new TableForAddMenu(jtF, jtV, jtP);
		} else if (e.getSource() == addPicB) {
			jfc.showDialog(new Frame(), "选择主图");
			if (jfc.getSelectedFile() != null) {
				primaryP = jfc.getCurrentDirectory() + "\\"
						+ jfc.getSelectedFile().getName();
				ImageIcon ii = new ImageIcon(primaryP);
				ii.setImage(ii.getImage().getScaledInstance(90, 60,
						Image.SCALE_DEFAULT));
				jlpic.setIcon(ii);
			}
		} else if (e.getSource() == jbArray[0]) {
			dtmF.addRow(new Object[] { "", "", "", "" });
		} else if (e.getSource() == jbArray[1]) {
			dtmV.addRow(new Object[] { "", "" });

		} else if (e.getSource() == jbArray[2]) {
			int rowcount = dtmP.getRowCount();
			if (rowcount >= 20) {
				JOptionPane.showMessageDialog(null, "最多有20个步骤，请合理安排");
				return;
			}
			dtmP.addRow(new Object[] { "步骤" + (rowcount + 1), "", null, "" });
		} else if (e.getSource() == jbArray[3]) {
			if ((dtmF.getRowCount()) > 0)
				dtmF.removeRow(dtmF.getRowCount() - 1);
		} else if (e.getSource() == jbArray[4]) {
			if ((dtmV.getRowCount()) > 0)
				dtmV.removeRow(dtmV.getRowCount() - 1);
		} else if (e.getSource() == jbArray[5]) {
			int c = 0;
			if ((c = dtmP.getRowCount()) > 0) {
				dtmP.removeRow(c - 1);
			}
		}
	}

	public String getContent(String uid) {
		StringBuffer sb = new StringBuffer(uid + "<#>");
		if (menuT.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "请将资料补充完整后再提交");
			return null;
		}
		sb.append(menuT.getText().trim() + "<#>");
		for (JComboBox jcbb : jcbbArray) {
			sb.append(jcbb.getSelectedItem().toString().trim() + "<#>");
		}
		String skil = skilT.getText().trim();
		String intr = intrT.getText().trim();
		sb.append(skil + "<#>");
		sb.append(intr + "<#>");
		for (int i = 0; i < dtmF.getRowCount(); i++) {
			String name = (String) dtmF.getValueAt(i, 0);
			String unit = (String) dtmF.getValueAt(i, 1);
			if (name.length() < 1 || unit.length() < 1) {
				JOptionPane.showMessageDialog(null, "请将主料表格补充完整");
				return null;
			}
			sb.append(name + "|");
			sb.append(unit + "%");
		}
		sb = sb.replace(sb.length() - 1, sb.length(), "<#>");
		for (int i = 0; i < dtmV.getRowCount(); i++) {
			String name = (String) dtmV.getValueAt(i, 0);
			String unit = (String) dtmV.getValueAt(i, 1);
			if (name.length() < 1 || unit.length() < 1) {
				JOptionPane.showMessageDialog(null, "请将辅料表格补充完整");
				return null;
			}
			sb.append(name + "|");
			sb.append(unit + "%");
		}
		return sb.substring(0, sb.length() - 1);
	}

	public List<String> getProcess() {
		List<String> list = new ArrayList<String>();
		int rowCount = dtmP.getRowCount();
		for (int k = 0; k < rowCount; k++) {
			String introduce = (String) dtmP.getValueAt(k, 1);
			if (introduce.length() < 1) {
				JOptionPane.showMessageDialog(null, "请将制作过程补充完整");
				return null;
			}
			list.add(introduce);
		}
		return list;
	}

	public class MyListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			Point point = e.getPoint(); // 鼠标坐标
			int column = jtP.columnAtPoint(point); // 鼠标点击位置所在列
			int row = jtP.rowAtPoint(point); // 鼠标点击位置所在行
			if (column == 2) {
				String path = (String) jtP.getValueAt(row, 3); // 获取图片路径
				if (path != null && path.length() > 0) {
					new LookPicFrame(path, "步骤" + (row + 1)); // 创建新窗口，查看大图
				}
			} else if (column == 3) {
				mfc.showDialog(new JFrame(), "选择图片"); // 打开图片选择器,选择图片
				String path = null;
				if (mfc.getSelectedFile() != null) {
					path = mfc.getCurrentDirectory() + "\\" // 如果选择了图片，得到图片路径
							+ mfc.getSelectedFile().getName();
					row = jtP.getSelectedRow();
					ImageIcon icon = new ImageIcon(path); // 创建ImageIcon对象
					icon = new ImageIcon(icon.getImage().getScaledInstance(45,
							45, Image.SCALE_DEFAULT)); // 保证图片不变形
					AddMenuPanel.dtmP.setValueAt(icon, row, 2);
					AddMenuPanel.dtmP.setValueAt(path, row, column); // 设置图片到表格中
				}
			}
		}
	}
}
