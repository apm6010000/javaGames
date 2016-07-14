package com.sf.startmenu;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.sf.boxmaster.*;
import com.sf.cardgame.StartCardGame;
import com.sf.minesweeper.frame.*;

public class Start extends JFrame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new Start();

	}

	public Start() {
		super("欢迎");
		Container container = getContentPane();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		setSize(160, 270);
		setVisible(true);
		setResizable(false);
		int w = (int) (toolkit.getScreenSize().getWidth() - getWidth()) / 2;
		int h = (int) (toolkit.getScreenSize().getHeight() - getHeight()) / 2;
		setLocation(w, h);
		container.setBackground(new Color(240, 240, 240));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		setLayout(new FlowLayout(1, 10, 16));

		JLabel jLabel = new JLabel();
		jLabel.setIcon(new ImageIcon("image/mytitle.png"));
		jLabel.setPreferredSize(new Dimension(120, 40));
		jLabel.setHorizontalAlignment(SwingConstants.CENTER);
		container.add(jLabel);

		JButton button1 = new JButton("24点");
		button1.setPreferredSize(new Dimension(120, 32));
		add(button1);
		JButton button2 = new JButton("扫雷");
		button2.setPreferredSize(new Dimension(120, 32));
		add(button2);
		JButton button3 = new JButton("推箱子");
		button3.setPreferredSize(new Dimension(120, 32));
		add(button3);
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				setVisible(false);
				new StartCardGame();
			}
		});
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				setVisible(false);
				new myboxmaster();
			}
		});
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				setVisible(false);
				new StartFrame();
			}
		});
		SwingUtilities.updateComponentTreeUI(this);
	}
}
