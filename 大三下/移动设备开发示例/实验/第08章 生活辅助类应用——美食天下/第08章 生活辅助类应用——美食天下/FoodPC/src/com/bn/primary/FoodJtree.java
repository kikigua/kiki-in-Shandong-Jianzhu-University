package com.bn.primary;
import java.awt.Point;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
public class FoodJtree implements TreeSelectionListener {
	private DefaultMutableTreeNode root = new DefaultMutableTreeNode("美食天下");// 定义根节点
	private DefaultMutableTreeNode[] hf1 = { // 定义二级子节点数组
	new DefaultMutableTreeNode("菜品管理"), new DefaultMutableTreeNode("用户信息管理"),
			new DefaultMutableTreeNode("随拍信息管理"),
			new DefaultMutableTreeNode("推荐信息管理"),
			new DefaultMutableTreeNode("评论信息管理")};
	private DefaultMutableTreeNode[] menus = { // 定义菜品三级子节点数组
	new DefaultMutableTreeNode("添加菜品"), new DefaultMutableTreeNode("菜品信息管理"), };
	private DefaultTreeModel dtm = new DefaultTreeModel(root); // //创建一个JTree模型对象
	public static JTree jt=new JTree(); // 创建一个树形结构对象
	public FoodJtree() {
		for (int i = 0; i < hf1.length; i++) // /二级子节点加入根节点
		{root.add(hf1[i]);}
		hf1[0].add(menus[0]); // 将菜品三级子节点加入菜品二级节点
		hf1[0].add(menus[1]);
		jt.setModel(dtm); //JTree设置模型
		jt.addTreeSelectionListener(this);// 给JTree添加监听
		jt.setShowsRootHandles(true); // 设置显示根节点的控制图表
	}
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode dn = (DefaultMutableTreeNode) jt
				.getLastSelectedPathComponent();
		if (dn.equals(menus[0])) {
			PrimaryFrame.cl.show(PrimaryFrame.jpall, "addMenuPanel");	//添加菜品
		} 
		else if (dn.equals(menus[1])) {
			PrimaryFrame.cl.show(PrimaryFrame.jpall, "manageMenuPanel");//查看菜品信息
		} 
		else if (dn.equals(hf1[1])) {
			PrimaryFrame.cl.show(PrimaryFrame.jpall, "manageUidPanel");	//查看用户信息
		} 
		else if (dn.equals(hf1[2])) {
			PrimaryFrame.cl.show(PrimaryFrame.jpall, "manageRandomPanel");//查看随拍信息
		} 
		else if (dn.equals(hf1[3])) {
			PrimaryFrame.cl.show(PrimaryFrame.jpall, "recommendPanel");		//查看推荐信息
		} 
		else if (dn.equals(hf1[4])) {
			PrimaryFrame.cl.show(PrimaryFrame.jpall, "pinglunPanel");		//查看推荐信息
		}
	}
}