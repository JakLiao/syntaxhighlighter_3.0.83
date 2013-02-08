package GUI.jpanel;
//win��ʹ��UTF-8��������lable����
//java GUI.jpanel.TestJpanelTow
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;


/**
 * Swing ������Գ��� ����Swing�������������Ӧ���¼�
 * 
 * @author ����.�� 2003.4.17 ��23:14
 * @link http://www.robochina.org
 * @link robococde@etang.com
 */
public class TestJpanelTow {
	JFrame frame;

	public TestJpanelTow() throws UnsupportedEncodingException {
		// ��ʼ������ģ��
		frame = new JFrame("�ı�����������");
		JTextPane textPane = new JTextPane();
		textPane.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		textPane.setText("java�汾Ϊ:" + System.getProperty("java.version")
				+ "\n����");
		// ��������ܵĲ���
		Container c = frame.getContentPane();
		// c.setLayout(new BorderLayout())


		JScrollPane jsp = new JScrollPane(textPane);// �½�һ�����������棬���ı�����
		c.add(jsp, BorderLayout.CENTER);
		
		/*
		 *  �ı����
		 */
		JTextPane textPane2 = new JTextPane();
		textPane2.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		textPane2.setText("java�汾Ϊ:" + System.getProperty("java.version")
				+ "\n����");
		c.add(textPane2, BorderLayout.SOUTH);


		/*
		 * ���ӱ�ǩ
		 */
		Label label = new Label("�������ֱ����д��ǩ����");
		String arg0="java�汾Ϊ:" + System.getProperty("java.version");
		label.setText(arg0);
		c.add(label, BorderLayout.NORTH);
		
		// �������������࣬���Ӵ����¼�
		frame.addWindowListener(new WindowAdapter() {
			public void WindowClosing(WindowEvent e) {
				// �ͷ���Դ���˳�����
				frame.dispose();
				System.exit(0);
			}
		});

		frame.setSize(700, 500);
		// ����frame�ı�����,�˹���ʱ�رգ��Է���ʹ��window�¼�
		// setUndecorated(true);
		frame.setLocation(200, 150);
		frame.show();
	}

	public static void main(String args[]) throws UnsupportedEncodingException {

		new TestJpanelTow();
	}

}
