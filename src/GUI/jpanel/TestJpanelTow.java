package GUI.jpanel;
//win下使用UTF-8编码会出现lable乱码
//java GUI.jpanel.TestJpanelTow
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;


/**
 * Swing 组件测试程序 测试Swing所有组件及其相应的事件
 * 
 * @author 天翼.李 2003.4.17 晚23:14
 * @link http://www.robochina.org
 * @link robococde@etang.com
 */
public class TestJpanelTow {
	JFrame frame;

	public TestJpanelTow() throws UnsupportedEncodingException {
		// 初始化所有模块
		frame = new JFrame("文本框两个测试");
		JTextPane textPane = new JTextPane();
		textPane.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		textPane.setText("java版本为:" + System.getProperty("java.version")
				+ "\n换行");
		// 设置主框架的布局
		Container c = frame.getContentPane();
		// c.setLayout(new BorderLayout())


		JScrollPane jsp = new JScrollPane(textPane);// 新建一个滚动条界面，将文本框传入
		c.add(jsp, BorderLayout.CENTER);
		
		/*
		 *  文本框二
		 */
		JTextPane textPane2 = new JTextPane();
		textPane2.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		textPane2.setText("java版本为:" + System.getProperty("java.version")
				+ "\n换行");
		c.add(textPane2, BorderLayout.SOUTH);


		/*
		 * 增加标签
		 */
		Label label = new Label("这里可以直接填写标签标题");
		String arg0="java版本为:" + System.getProperty("java.version");
		label.setText(arg0);
		c.add(label, BorderLayout.NORTH);
		
		// 利用无名内隐类，增加窗口事件
		frame.addWindowListener(new WindowAdapter() {
			public void WindowClosing(WindowEvent e) {
				// 释放资源，退出程序
				frame.dispose();
				System.exit(0);
			}
		});

		frame.setSize(700, 500);
		// 隐藏frame的标题栏,此功暂时关闭，以方便使用window事件
		// setUndecorated(true);
		frame.setLocation(200, 150);
		frame.show();
	}

	public static void main(String args[]) throws UnsupportedEncodingException {

		new TestJpanelTow();
	}

}
