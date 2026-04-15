package com.bn.Pinglun;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.bn.NetUtil.NetIO;
import com.bn.util.LookPicFrame;;
public class PinglunDetailJFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel jp;
	JLabel jl=new JLabel();
	JTextArea jta;
	JScrollPane jsp;
	JButton jbback;
	int x;
	int y;
	public PinglunDetailJFrame(String picName, String info, String order) {
		jp = new JPanel(null);
		jl.setHorizontalAlignment(JLabel.CENTER);
		jl.setVerticalAlignment(JLabel.CENTER);
		jl.setBounds(170, 30, 250, 200);
		jp.add(jl);
		jta = new JTextArea(info);
		jta.setWrapStyleWord(true);
		jta.setLineWrap(true);
		jta.setFont(new Font("宋体", Font.PLAIN, 12));
		jsp = new JScrollPane(jta);
		jp.add(jsp);
		jbback = new JButton("返回");
		jp.add(jbback);
		this.add(jp);
		System.out.println("picName " + picName);
		Toolkit toolkit=Toolkit.getDefaultToolkit();                 		//创建一个Toolkit对象
		Dimension d=toolkit.getScreenSize();                  			//获得Dimension对象
		x=(int) ((d.width-600)/2);                    				//登录界面在屏幕中x坐标
		y=(int) ((d.height-500)/2);              			//登录界面在屏幕中y坐标
		if (picName != null && !picName.equals("null")) {
			final ImageIcon ii = new ImageIcon(NetIO.getImagebyte(picName));
			ii.setImage(ii.getImage().getScaledInstance(250, 200,
					Image.SCALE_DEFAULT));
			jl.setIcon(ii);
			jl.setToolTipText("双击查看大图");
			jl.addMouseListener(
					new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					super.mouseClicked(arg0);
					if(arg0.getClickCount()==2)
					{
						new LookPicFrame(ii,"评论图片");
					}
				}				
			});
			jsp.setBounds(50, 250, 500, 150);
			jbback.setBounds(250, 420, 80, 30);
			this.setBounds(x, y, 600, 500);
		} else {
			jsp.setBounds(50, 30, 500, 150);
			jbback.setBounds(250, 220, 80, 30);
			this.setBounds(x, y, 600, 300);
		}
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setFocusable(true);
		this.setVisible(true);
		jbback.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PinglunDetailJFrame.this.dispose();
			}
		});
		this.setIconImage(new ImageIcon("res/img/icon_launch.png").getImage());
		this.setTitle(order);
	}
}