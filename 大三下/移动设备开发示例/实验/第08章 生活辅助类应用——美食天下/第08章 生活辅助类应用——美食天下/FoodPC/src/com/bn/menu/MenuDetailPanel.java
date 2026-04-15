package com.bn.menu;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.bn.primary.PrimaryFrame;
import com.bn.util.LookPicFrame;
import com.bn.util.TextJButtonTableCellRenderer;

public class MenuDetailPanel extends JPanel 
implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JLabel jlTitle = new JLabel("美食详细信息");
	private	JLabel menuL=new JLabel("菜谱名称:");
	private	JLabel foodL=new JLabel(" 主  料:");
	private	JButton backB=new JButton("返回");
	private	JLabel vicL=new JLabel(" 配  料:");
	private	JLabel uidL=new JLabel("上 传 者:");
	public static DefaultTableModel dtmF=new DefaultTableModel();
	public static DefaultTableModel dtmV=new DefaultTableModel();
	public static DefaultTableModel dtmP=new DefaultTableModel();
	public static JTable jtF=new JTable(dtmF);
	public static JTable jtV=new JTable(dtmV);
	public static MyJTable jtP=new MyJTable(dtmP);
	private	JScrollPane jspP=new JScrollPane(jtP);
	private	JScrollPane jspF=new JScrollPane(jtF);
	private	JScrollPane jspV=new JScrollPane(jtV);
	private	JLabel flavourL=new JLabel("菜品口味:");
	private	JLabel waysL=new JLabel("烹饪方式:");
	private	JLabel timeL=new JLabel("烹饪时间:");
	private	JLabel toolsL=new JLabel("主要厨具:");
	private	JLabel skillL=new JLabel("温馨提示:");
	JTextArea skilT=new JTextArea("温馨提示");
	private	JScrollPane jspS=new JScrollPane(skilT);
	private	JLabel intrL=new JLabel("简   介:");
	JTextArea intrT=new JTextArea("简    介");
	private	JScrollPane jspI=new JScrollPane(intrT);
	private	JLabel processL=new JLabel("制作步骤:");
	JTextField menuT=new JTextField();
	JTextField uidT=new JTextField();
	JTextField flavourT=new JTextField("口味");
	JTextField crafT=new JTextField("工艺");
	JTextField timeT=new JTextField("时间");
	JTextField toolT=new JTextField("工具");
	public static String show;
	JLabel jlP=new JLabel();
	JLabel jlprimary=new JLabel("主图");
	byte[] bb;
	public MenuDetailPanel()
	{
		this.setLayout(null);
		jlTitle.setFont(new Font("宋体", Font.BOLD, 20));
		jlTitle.setBounds(280, 10, 150, 20);
		this.add(jlTitle);
		skilT.setWrapStyleWord(true);
		skilT.setLineWrap(true);
		skilT.setFont(new Font("宋体",Font.PLAIN,12));
		intrT.setWrapStyleWord(true);
		intrT.setLineWrap(true);
		intrT.setFont(new Font("宋体",Font.PLAIN,12));
		this.add(menuL);
		menuL.setBounds(40, 40, 80, 30);
		this.add(menuT);
		menuT.setBounds(110, 40, 100, 30);		
		this.add(flavourL);
		flavourL.setBounds(40, 80, 80, 30);
		this.add(flavourT);
		flavourT.setBounds(110, 80, 100, 30);
		this.add(waysL);
		waysL.setBounds(240, 80, 100, 30);
		this.add(crafT);
		crafT.setBounds(320, 80, 100, 30);		
		this.add(timeL);
		timeL.setBounds(40, 130, 80, 30);
		this.add(timeT);
		timeT.setBounds(110, 130, 100, 30);
		this.add(toolsL);
		toolsL.setBounds(240, 130, 100, 30);
		this.add(toolT);
		toolT.setBounds(320, 130, 100, 30);	
		this.add(foodL);
		foodL.setBounds(40, 135, 100, 100);
		this.add(jspF);
		jspF.setBounds(110, 180, 500, 90);
		this.add(vicL);
		vicL.setBounds(40, 290, 80, 30);
		this.add(jspV);
		jspV.setBounds(110, 300, 500, 90);
		this.add(processL);
		processL.setBounds(40, 400, 80, 30);
		this.add(jspP);
		jspP.setBounds(110, 410, 500, 90);		
		this.add(intrL);
		intrL.setBounds(40, 490, 500, 90);
		this.add(jspI);
		jspI.setBounds(110, 530, 500, 50);		
		this.add(skillL);
		skillL.setBounds(40, 560, 500, 90);
		this.add(jspS);
		jspS.setBounds(110, 600, 500, 50);		
		this.add(backB);
		backB.setBounds(310, 655, 80, 30);
		this.add(uidL);
		backB.addActionListener(this);
		uidL.setBounds(240, 40, 100, 30);
		this.add(uidT);
		uidT.setBounds(320, 40, 100, 30);
		jlP.setIcon(new ImageIcon("res/img/icon_launch.png"));
		jlP.setBounds(480, 40, 80, 80);
		this.add(jlP);
		jlprimary.setBounds(500, 130, 60, 30);
		this.add(jlprimary);
		jlP.setToolTipText("双击查看完整图片");
		jlP.addMouseListener(new MyMouseAdapter());
		dtmP.addColumn("明细");
		jtP.getColumnModel().getColumn(jtP.getColumnModel().getColumnCount()-1).setCellRenderer(new TextJButtonTableCellRenderer("详细"));
		this.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("show "+show);
		if(e.getSource()==backB)
		{
			PrimaryFrame.cl.show(PrimaryFrame.jpall, show);
		}
	}
	private class MyMouseAdapter extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseClicked(e);
			if(e.getClickCount()==2)
			{
				new LookPicFrame(bb,null);
			}
		}		
	}	
}