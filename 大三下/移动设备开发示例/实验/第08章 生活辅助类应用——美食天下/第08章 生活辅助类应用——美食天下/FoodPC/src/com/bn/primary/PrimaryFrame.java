package com.bn.primary;
/**
 * 主界面
 */
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import com.bn.Pinglun.PinglunPanel;
import com.bn.Recommend.ManageRecommendPanel;
import com.bn.Uid.ManageUidPanel;
import com.bn.Uid.UidInfoPanel;
import com.bn.landframe.*;
import com.bn.menu.AddMenuPanel;
import com.bn.menu.ManageMenu;
import com.bn.menu.MenuDetailPanel;
import com.bn.random.*;
public class PrimaryFrame extends JFrame 
{	
	public static PrimaryFrame pf;
	private JScrollPane jspl;
	public static CardLayout cl;
	public static JPanel jpall;
	public static MenuDetailPanel menuDetailPanel;
	private JPanel emptyPanel;
	public static AddMenuPanel addMenuPanel;
	private ManageMenu manageMenuPanel;
	public static DetailRandomPanel detailRandomPanel;
	private ManageRandomPanel manageRandomPanel;
	ManageRecommendPanel recommendPanel;
	ManageUidPanel manageUidPanel;
	UidInfoPanel uidInfoPanel;	
	JScrollPane jspr;	
	JSplitPane jsl;
	 
	public PrimaryFrame()
	{
		new FoodJtree();
		jspl=new JScrollPane(FoodJtree.jt);
		cl=new CardLayout();
		jpall=new JPanel();
		menuDetailPanel=new MenuDetailPanel();
		emptyPanel=new JPanel();
		addMenuPanel = new AddMenuPanel();
		manageMenuPanel=new ManageMenu();
		recommendPanel=new ManageRecommendPanel();
		detailRandomPanel=new DetailRandomPanel();
		manageUidPanel=new ManageUidPanel();
		uidInfoPanel = new UidInfoPanel();		
		manageRandomPanel = new ManageRandomPanel();
		PinglunPanel pinglunPanel =new PinglunPanel();
		jspr=new JScrollPane(jpall);
		jsl=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jspl,jspr);
		this.add(jsl);
		jsl.setDividerSize(5);
		jsl.setDividerLocation(200);
		jpall.setLayout(cl);
		jpall.add(emptyPanel,"emptyPanel");		
		jpall.add(addMenuPanel,"addMenuPanel");
		jpall.add(manageMenuPanel,"manageMenuPanel");
		jpall.add(menuDetailPanel,"menuDetailPanel");		
		jpall.add(manageRandomPanel,"manageRandomPanel");
		jpall.add(detailRandomPanel,"detailRandomPanel");		
		jpall.add(manageUidPanel,"manageUidPanel");		
		jpall.add(uidInfoPanel,"uidInfoPanel");	
		jpall.add(recommendPanel,"recommendPanel");
		jpall.add(pinglunPanel,"pinglunPanel");
		this.setTitle("美食天下管理程序");
		Dimension   screenSize   =   Toolkit.getDefaultToolkit().getScreenSize();  
		this.setBounds(0,0,screenSize.width,screenSize.height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setIconImage(new ImageIcon("res/img/icon_launch.png").getImage());
		LandFrame.lf.dispose();
	}
}