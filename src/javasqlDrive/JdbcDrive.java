package javasqlDrive;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
public class JdbcDrive //��������
{
	public static void main(String args[])
	{
		GUIA gui=new GUIA(); //������GUI�Ķ���
		gui.pack(); //װ��ִ��GUI��
	}
}
class GUIA extends Frame implements ActionListener
{
	TextArea text;Panel panel;TextField sno; Button btn;
	GUIA() //���췽��
	{
		super("���������ѯ");setLayout(new BorderLayout());
		setBackground(Color.cyan);
		setVisible(true);
		text=new TextArea();
		btn=new Button("��ѯ");//���ذ�ť
		sno=new TextField(16);//�����������16
		panel=new Panel();
		panel.add(new Label("���뱻��ѯ�����ʱ�ţ�"));
		panel.add(sno);
		panel.add(btn);
		add("North",panel);
		add(text,"Center");
		text.setEditable(false);
		btn.addActionListener(this);
		addWindowListener(new WindowAdapter()
		{
				public void windowClosing(WindowEvent e)
				{
					setVisible(false);
					System.exit(0);
				}
		});
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btn) //���û����²�ѯ��ťʱ
		{
			text.setText("��ѯ���"+'\n'); //��ʾ��ʾ��Ϣ
			try
			{
				Liststudent();
			}
			catch(SQLException ee) { }
		}
	}
	public void Liststudent() throws SQLException //������ݿ�Ĳ���
	{
		Connection con=null;//������ʽ
		String bh,mc,xh,lb,dw,sj;
		int sl; float dj,je;
		try
		{
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");//����Դ��ʽ
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver"); 
			//SQL SERVER�ĵ�½��ʽ����Ϊʹ��SQL SERVER�����½��֤��ʽ 
			//("jdbc:microsoft:sqlserver://��������ַ:���ʶ˿�","SQL�û���","SQL����"); 
			con=DriverManager.getConnection("jdbc:microsoft:sqlserver://127.0.0.1:1433","sa",""); 
			con.setCatalog("mydata");//���ݿ���
		}
		catch(ClassNotFoundException e) { } 
		//Connection con=DriverManager.getConnection("jdbc:odbc:wzgl");//wzgl������Դ����
		Statement sql=con.createStatement(); //����Statement����
		ResultSet rs=sql.executeQuery("select * from wuzi");
		while(rs.next()) //�������ѯ�����
		{
			bh=rs.getString("���ʱ��");
			mc=rs.getString("��������");
			xh=rs.getString("����ͺ�");
			lb=rs.getString("���");
			dw=rs.getString("������λ");
			sl=rs.getInt("����");
			dj=rs.getFloat("����");
			je=rs.getFloat("���");
			sj=rs.getDate("ʱ��").toString();
			if(bh.trim().equals(sno.getText().trim()))
			{
				text.append('\n'+"���ʱ��"+" "+"��������"+" "+"����ͺ�"+" "+"���"+" "+"������λ"+" "+"����"+" "+"����"+" "+"���"+" "+"ʱ��"+'\n');
				text.append('\n'+bh+" "+mc+" "+xh+" "+lb+" "+dw+" "+sl+" "+dj+" "+je+" "+sj+" "+'\n');
			}
		}
	}
}