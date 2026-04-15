package com.bn.Recommend;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.bn.NetUtil.NetIO;
import com.bn.primary.PrimaryFrame;
import com.bn.util.Constant;
import com.bn.util.MyPicFileChooser;
import com.bn.util.UploadUtils;
/**
 * 添加主推荐
 */
public class AddPrimaryRecommend extends JFrame implements ActionListener
{	
	JPanel jp;
	JLabel jl;
	ImageIcon ii;
	JTextArea jta;
	JScrollPane jsp;
	JButton jbback;
	JButton jbok;
	String path;
	ManageRecommendPanel rp;
	public AddPrimaryRecommend(ManageRecommendPanel rp)
	{
		this.rp=rp;
		ImageIcon ii=new ImageIcon("res/img/mstx.png");
		jl=new JLabel();
		jl.setHorizontalAlignment(JLabel.CENTER);
		jl.setVerticalAlignment(JLabel.CENTER);
		ii.setImage(ii.getImage().
		getScaledInstance(250, 200, Image.SCALE_DEFAULT));
		jl.setIcon(ii);
		jl.setToolTipText("双击添加图片");
		jl.addMouseListener(new MyMouseAdapter());
		jl.setBounds(170, 30, 250, 200);
		jta=new JTextArea();
		jta.setWrapStyleWord(true);
		jta.setLineWrap(true);
		jta.setFont(new Font("宋体", Font.PLAIN, 12));
		jsp=new JScrollPane(jta);
		jp=new JPanel(null);
		jp.add(jl);
		jp.add(jsp);
		jsp.setBounds(50, 250, 500, 150);
		jbok=new JButton("确定");
		jbback = new JButton("取消");
		jbok.setBounds(200, 420, 80, 30);
		jbback.setBounds(300, 420, 80, 30);		
		jp.add(jbback);
		jp.add(jbok);
		this.add(jp);
		this.setBounds(200, 100, 600, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setFocusable(true);
		this.setVisible(true);
		jbok.addActionListener(this);
		jbback.addActionListener(this);
		this.setIconImage(new ImageIcon("res/img/icon_launch.png").getImage());
		this.setTitle("sdaf ");
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbok)
		{
			String content=jta.getText().trim();
			if(path==null||path.length()<1)
			{
				JOptionPane.showMessageDialog(null, "请选择图片后确定");
				return;
			}else if(content.length()<1)
			{
				JOptionPane.showMessageDialog(null, "请添加推荐语");
				return;
			}
			String picName=UploadUtils.upLoadPic(path);
			if(picName!=null&&!picName.equals(Constant.NO_MESSAGE))
			{
				if(NetIO.UploadPrimaryRecommend(picName, content))
				{
					rp.notifacationPrimry();
					if(PrimaryRecommendTableModel.data.size()<4)
					{
						JOptionPane.showMessageDialog(null,"添加成功,数量太少,请再添加一个");

					}else
					{
						JOptionPane.showMessageDialog(null,"添加成功");
					}
					
					this.dispose();
				}else{
					JOptionPane.showMessageDialog(null,"添加失败");
				}
			}
			
		}else if(e.getSource()==jbback)
		{
			PrimaryFrame.cl.show(PrimaryFrame.jpall, "recommendPanel");
			this.dispose();
		}

	}
	private class MyMouseAdapter extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource()==jl)
			{
				MyPicFileChooser mfc=new MyPicFileChooser ();
				mfc.showDialog(new JFrame(), "图片选择");
				path = null;
				if(mfc.getSelectedFile()!=null)
				{
					path=mfc.getCurrentDirectory()+"\\"+mfc.getSelectedFile().getName();
					System.out.println("path "+path);
					ImageIcon ii=new ImageIcon(path);
					jl.setIcon(ii);
				}	
			}
			
		}
	}
	
}