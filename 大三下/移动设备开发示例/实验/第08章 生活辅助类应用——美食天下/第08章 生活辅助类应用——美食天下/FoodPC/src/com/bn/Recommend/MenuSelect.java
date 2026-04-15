package com.bn.Recommend;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.ScrollPaneConstants;

import javax.swing.table.DefaultTableModel;

import com.bn.NetUtil.NetIO;
import com.bn.menu.TableForMenuManage;
import com.bn.util.Constant;
import com.bn.util.TranslateUtil;

public class MenuSelect extends JFrame implements ActionListener {

	/**
	 * 菜谱推荐 选择
	 */
	private static final long serialVersionUID = -3431203577260572395L;
	public static DefaultTableModel dtm = new DefaultTableModel();
	public static JTable jt = new JTable(dtm);
	private JScrollPane jsp = new JScrollPane(jt);
	private JPanel jp;
	private JButton ok_bt=new JButton("确定");
	private JButton cancell_bt=new JButton("取消");
	private JLabel jlTitle = new JLabel("菜谱信息管理");
	private JLabel jlSelect = new JLabel("选择查询条件：");
	private JLabel jlstyle = new JLabel("菜系：");
	private JComboBox jcstyle = new JComboBox();
	private JComboBox jcflavour = new JComboBox();
	private JComboBox jcctime = new JComboBox();
	private JComboBox jcdifficuty = new JComboBox();
	
	private JComboBox[] jccs = new JComboBox[] { jcstyle, jcflavour, jcctime,
			jcdifficuty };
	String[][] items=new String[][]{Constant.style,Constant.flavour,Constant.ctime,Constant.difficulty};
	
	private JLabel jlPeople = new JLabel("口味：");
	private JLabel jlHome = new JLabel("时间：");
	private JLabel jlFoodType = new JLabel("难度：");
	JButton jblook = new JButton("查询");
	DefaultTableModel tmM;
	ManageRecommendPanel rp;
	public MenuSelect(ManageRecommendPanel rp,DefaultTableModel tmM) {
		this.rp=rp;
		this.tmM=tmM;
		jp=new JPanel(null);
		jlTitle.setFont(new Font("宋体", Font.BOLD, 20));
		jlTitle.setBounds(330, 10, 200, 25);
		jp.add(jlTitle);
		jlSelect.setBounds(10, 50, 120, 25);
		jp.add(jlSelect);
		jlstyle.setBounds(120, 50, 100, 25);
		jp.add(jlstyle);
		jcstyle.setBounds(160, 50, 80, 25);
		jp.add(jcstyle);
		jlPeople.setBounds(255, 50, 60, 25);
		jp.add(jlPeople);
		
		for(int i=0;i<jccs.length;i++)
		{
			JComboBox jc=jccs[i];
			jc.addItem("所有");
			for(int j=0;j<items[i].length;j++)
			{
				jc.addItem(items[i][j]);
			}
		}
		jcflavour.setBounds(300, 50, 60, 25);
		jp.add(jcflavour);
		jlHome.setBounds(370, 50, 70, 25);
		jp.add(jlHome);
		jcctime.setBounds(410, 50, 90, 25);
		jp.add(jcctime);
		jlFoodType.setBounds(525, 50, 70, 25);
		jp.add(jlFoodType);
		jcdifficuty.setBounds(570, 50, 70, 25);
		jp.add(jcdifficuty);

		jblook.setBounds(700, 50, 80, 25);
		jp.add(jblook);
		jblook.addActionListener(this);		
		jt.setAutoResizeMode(0);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setBounds(20, 100, 850, 300);
		jp.add(jsp);
		cancell_bt.setBounds(700, 420, 80, 25);
		this.add(cancell_bt);
		cancell_bt.addActionListener(this);
		ok_bt.setBounds(800, 420, 80, 25);
		jp.add(ok_bt);
		ok_bt.addActionListener(this);
		this.add(jp);
		this.setBounds(200, 100, 900, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setFocusable(true);
		this.setVisible(true);
		this.setIconImage(new ImageIcon("res/img/icon_launch.png").getImage());
		this.setTitle("添加主推荐");
		String[][] content=NetIO.getMenuLeft(Constant.NO_MESSAGE, Constant.NO_MESSAGE, Constant.NO_MESSAGE, Constant.NO_MESSAGE);
		new TableForMenuSelect(jt, dtm,content);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jblook) {
			String style = Constant.NO_MESSAGE;
			String flavour = Constant.NO_MESSAGE;
			String time = Constant.NO_MESSAGE;
			String difficulty = Constant.NO_MESSAGE;
			if (jcstyle.getSelectedIndex() != 0)
				style = (String) jcstyle.getSelectedItem();
			if (jcflavour.getSelectedIndex() != 0)
				flavour = (String) jcflavour.getSelectedItem();
			if (jcctime.getSelectedIndex() != 0)
				time = (String) jcctime.getSelectedItem();
			if (jcdifficuty.getSelectedIndex() != 0)
				difficulty = (String) jcdifficuty.getSelectedItem();
			String[][] content = NetIO.getMenuLeft(style, flavour, time,
					difficulty);
			if (content[0][0].equals(Constant.NO_MESSAGE)) {
				JOptionPane.showMessageDialog(null, "查询结果为空");
				return;
			}
			new TableForMenuSelect(jt, dtm, content);
		} else if (e.getSource() == ok_bt) {
			System.out.println("ok bt");
			int column = dtm.getColumnCount();
			int row = dtm.getRowCount();
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < row; i++) {
				Boolean b = (Boolean) dtm.getValueAt(i, column - 1);
				if (b != null && b) {
					String id = (String) dtm.getValueAt(i, 0);
					list.add(id);
				}
			}
			// 已推荐的 加上添加的不是双数
			if ((tmM.getRowCount() + list.size()) % 2 != 0) {
				JOptionPane.showMessageDialog(null, "请取消一个或再添加一个");
				return;
			}

			if (list.size() > 0) {
				for (String menuId : list) {
					Boolean flag = NetIO.addExcelletnMenu(menuId);
					if (!flag) {
						JOptionPane.showMessageDialog(null, "添加失败");
						return;
					}
				}
				JOptionPane.showMessageDialog(null, "添加成功");
				rp.notifactionMenu();
			}
			this.dispose();
		} else if (e.getSource() == cancell_bt) {
			this.dispose();
		}
	}
}
