package GUI.jpanel;

//带滚动条文本框
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TestJpanelScrollPane {
	public TestJpanelScrollPane() {
		JFrame jf = new JFrame("TestJpanelScrollPane-测试文本框滚动条");
		JPanel jp = new JPanel();
		JTextArea jta = new JTextArea(20, 80);
		JScrollPane jsp = new JScrollPane(jta);// 新建一个滚动条界面，将文本框传入
		jp.add(jsp);// 注意：将滚动条界面添加到组建中，而不是添加文本框了
		jf.add(jp);

		jf.pack();
		jf.setLocation(300, 300);// 启动时的位置
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new TestJpanelScrollPane();
	}
}
