package com.bn.random;

import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * 随拍详细界面
 */
public class  DetailRandomPanel extends JPanel 
{
	private JLabel jlTitle = new JLabel("随拍详细"); 
	JPanel jp;
	JButton jbNext;
	JButton jbPrev;
	CardLayout cl;
	JLabel order;
	public static JLabel[] labelArray={
			new JLabel(" 标	签 :"),new JLabel("上传者:"),new JLabel("所在城市:"),
			new JLabel(" 时	间 :"),new JLabel("随拍图片 :"),new JLabel("随拍说明:")
	};
	JTextField[] jtextArray={
			 new JTextField("1"),new JTextField("2"),
			 new JTextField("3"),new JTextField("4")
	};	
	JTextArea jta;
	JScrollPane jsp;
	JButton okB=new JButton("返回");	
	public DetailRandomPanel()
	{		
		this.setLayout(null);
		jlTitle.setFont(new Font("宋体", Font.BOLD, 20));	
		jlTitle.setBounds(240, 10, 150, 20);
		this.add(jlTitle);
		this.add(labelArray[0]);
		labelArray[0].setBounds(20, 45, 80, 25);
		this.add(jtextArray[0]);
		jtextArray[0].setBounds(100, 45, 150, 25);
		this.add(labelArray[1]);
		labelArray[1].setBounds(20, 85, 80, 25);
		this.add(jtextArray[1]);
		jtextArray[1].setBounds(100, 85, 150, 25);
		
		this.add(labelArray[2]);
		labelArray[2].setBounds(300, 45, 80, 25);
		this.add(jtextArray[2]);
		jtextArray[2].setBounds(380, 45, 150, 25);
		this.add(labelArray[3]);
		labelArray[3].setBounds(300,85, 80, 25);
		this.add(jtextArray[3]);
		jtextArray[3].setBounds(380, 85, 150, 25);
		
		this.add(labelArray[4]);
		labelArray[4].setBounds(20, 135, 80, 25);		
		this.add(labelArray[5]);
		labelArray[5].setBounds(20, 535, 80, 25);
		jta=new JTextArea();
		jsp=new JScrollPane(jta);
		jta.setWrapStyleWord(true);
		jta.setLineWrap(true);
		this.add(jsp);
		jsp.setBounds(90, 535, 450, 80);
	}
	
}
