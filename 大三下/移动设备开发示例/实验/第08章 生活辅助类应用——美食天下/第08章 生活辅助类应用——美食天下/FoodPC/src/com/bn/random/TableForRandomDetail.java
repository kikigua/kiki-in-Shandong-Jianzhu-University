package com.bn.random;

import java.awt.CardLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.bn.NetUtil.NetIO;
import com.bn.primary.PrimaryFrame;
import com.bn.util.LookPicFrame;

public class TableForRandomDetail implements ActionListener {
	String show;
	int order = 1;
	int count = 1;
	public static ExecutorService threadPool;

	public TableForRandomDetail(String info, String show) {
		// 李四<#>2015-05-17
		// 00:08:53.0<#>考虑考虑<#>0<#>0<#>0<#>一人食<#><#>1431793041808.jpeg%1431793041980.jpeg%1431793042222.jpeg%1431793042369.jpeg%1431793042540.jpeg%1431793042836.jpeg%1431793042953.jpeg%1431793043067.jpeg%1431793043304.jpeg%1431793043560.jpeg%1431793043683.jpeg%1431793043860.jpeg%1431793044063.jpeg%1431793044119.jpeg%1431793044297.jpeg%1431793044368.jpeg%1431793044537.jpeg%1431793044734.jpeg%1431793044973.jpeg%1431793045221.jpeg
		// <#>1431432224552.jpeg
		this.show = show;
		String[] sss = info.split("<#>");
		PrimaryFrame.detailRandomPanel.jtextArray[0].setText(sss[6]);
		PrimaryFrame.detailRandomPanel.jtextArray[1].setText(sss[0]);
		PrimaryFrame.detailRandomPanel.jtextArray[2].setText(sss[7]);
		int index1 = sss[1].lastIndexOf(":");
		PrimaryFrame.detailRandomPanel.jtextArray[3].setText(sss[1].substring(0, index1));

		PrimaryFrame.detailRandomPanel.jta.setText(sss[2]);
		String[] ss = sss[8].split("%");
		setJP(ss);
	}

	public void setJP(final String[] paths) {
		if (PrimaryFrame.detailRandomPanel.jp != null) {
			PrimaryFrame.detailRandomPanel.remove(PrimaryFrame.detailRandomPanel.jp);
			PrimaryFrame.detailRandomPanel.remove(PrimaryFrame.detailRandomPanel.jbNext);
			PrimaryFrame.detailRandomPanel.remove(PrimaryFrame.detailRandomPanel.jbPrev);
			PrimaryFrame.detailRandomPanel.remove(PrimaryFrame.detailRandomPanel.okB);
			PrimaryFrame.detailRandomPanel.remove(PrimaryFrame.detailRandomPanel.order);
		}
		count = paths.length;
		PrimaryFrame.detailRandomPanel.cl = new CardLayout();
		PrimaryFrame.detailRandomPanel.jp = new JPanel(PrimaryFrame.detailRandomPanel.cl);
		if (threadPool == null || threadPool.isShutdown()) {
			threadPool = Executors.newSingleThreadExecutor();
		}
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < paths.length; i++) {
					byte[] bb = NetIO.getImagebyte(paths[i]);
					final ImageIcon icon = new ImageIcon(bb);
					icon.setImage(icon.getImage().getScaledInstance(300, 300,
							Image.SCALE_DEFAULT));
					JLabel jl = new JLabel(icon);
					jl.setBounds(0, 0, 300, 300);
					jl.setToolTipText("双击查看大图");
					jl.addMouseListener(new MouseAdapter()
					{

						@Override
						public void mouseClicked(MouseEvent arg0) {
							// TODO Auto-generated method stub
							super.mouseClicked(arg0);
							new LookPicFrame(icon,"随拍图片");
						}
						
					});
					PrimaryFrame.detailRandomPanel.jp.add(jl, i + "");
				}
			}
		});
		PrimaryFrame.detailRandomPanel.order = new JLabel(order + "/" + count);
		PrimaryFrame.detailRandomPanel.order.setBounds(290, 450, 80, 25);
		PrimaryFrame.detailRandomPanel.add(PrimaryFrame.detailRandomPanel.order);
		PrimaryFrame.detailRandomPanel.jbPrev = new JButton("上一张");
		PrimaryFrame.detailRandomPanel.jbPrev.setBounds(190, 480, 80, 25);
		PrimaryFrame.detailRandomPanel.add(PrimaryFrame.detailRandomPanel.jbPrev);
		PrimaryFrame.detailRandomPanel.jbNext = new JButton("下一张");
		PrimaryFrame.detailRandomPanel.jbNext.setBounds(320, 480, 80, 25);
		PrimaryFrame.detailRandomPanel.add(PrimaryFrame.detailRandomPanel.jbNext);
		PrimaryFrame.detailRandomPanel.jbNext.addActionListener(this);
		PrimaryFrame.detailRandomPanel.jbPrev.addActionListener(this);

		PrimaryFrame.detailRandomPanel.okB = new JButton("返回");
		PrimaryFrame.detailRandomPanel.add(PrimaryFrame.detailRandomPanel.okB);
		PrimaryFrame.detailRandomPanel.okB.setBounds(250, 635, 90, 30);
		PrimaryFrame.detailRandomPanel.okB.addActionListener(this);

		PrimaryFrame.detailRandomPanel.add(PrimaryFrame.detailRandomPanel.jp);
		PrimaryFrame.detailRandomPanel.jp.setBounds(150, 140, 300, 300);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == PrimaryFrame.detailRandomPanel.okB) {
			PrimaryFrame.cl.show(PrimaryFrame.jpall, show);
		} else if (e.getSource() == PrimaryFrame.detailRandomPanel.jbPrev) {
			order = ((order - 1) <= 0 ? count : (order - 1));
			PrimaryFrame.detailRandomPanel.order.setText(order + "/" + count);
			PrimaryFrame.detailRandomPanel.cl.previous(PrimaryFrame.detailRandomPanel.jp);
		} else if (e.getSource() == PrimaryFrame.detailRandomPanel.jbNext) {
			order = (order + 1)>count ? 1 : (order + 1);
			PrimaryFrame.detailRandomPanel.order.setText(order + "/" + count);
			PrimaryFrame.detailRandomPanel.cl.next(PrimaryFrame.detailRandomPanel.jp);
		}
	}
}