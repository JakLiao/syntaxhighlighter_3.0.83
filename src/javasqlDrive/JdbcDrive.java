package javasqlDrive;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
public class JdbcDrive //定义主类
{
	public static void main(String args[])
	{
		GUIA gui=new GUIA(); //创建类GUI的对象
		gui.pack(); //装载执行GUI类
	}
}
class GUIA extends Frame implements ActionListener
{
	TextArea text;Panel panel;TextField sno; Button btn;
	GUIA() //构造方法
	{
		super("物资情况查询");setLayout(new BorderLayout());
		setBackground(Color.cyan);
		setVisible(true);
		text=new TextArea();
		btn=new Button("查询");//加载按钮
		sno=new TextField(16);//加载输入框宽带16
		panel=new Panel();
		panel.add(new Label("输入被查询的物资编号："));
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
		if(e.getSource()==btn) //当用户按下查询按钮时
		{
			text.setText("查询结果"+'\n'); //显示提示信息
			try
			{
				Liststudent();
			}
			catch(SQLException ee) { }
		}
	}
	public void Liststudent() throws SQLException //针对数据库的操作
	{
		Connection con=null;//驱动方式
		String bh,mc,xh,lb,dw,sj;
		int sl; float dj,je;
		try
		{
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");//数据源方式
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver"); 
			//SQL SERVER的登陆方式必须为使用SQL SERVER密码登陆认证方式 
			//("jdbc:microsoft:sqlserver://服务器地址:访问端口","SQL用户名","SQL密码"); 
			con=DriverManager.getConnection("jdbc:microsoft:sqlserver://127.0.0.1:1433","sa",""); 
			con.setCatalog("mydata");//数据库名
		}
		catch(ClassNotFoundException e) { } 
		//Connection con=DriverManager.getConnection("jdbc:odbc:wzgl");//wzgl是数据源名称
		Statement sql=con.createStatement(); //创建Statement对象
		ResultSet rs=sql.executeQuery("select * from wuzi");
		while(rs.next()) //输出被查询的情况
		{
			bh=rs.getString("物资编号");
			mc=rs.getString("物资名称");
			xh=rs.getString("规格型号");
			lb=rs.getString("类别");
			dw=rs.getString("计量单位");
			sl=rs.getInt("数量");
			dj=rs.getFloat("单价");
			je=rs.getFloat("金额");
			sj=rs.getDate("时间").toString();
			if(bh.trim().equals(sno.getText().trim()))
			{
				text.append('\n'+"物资编号"+" "+"物资名称"+" "+"规格型号"+" "+"类别"+" "+"计量单位"+" "+"数量"+" "+"单价"+" "+"金额"+" "+"时间"+'\n');
				text.append('\n'+bh+" "+mc+" "+xh+" "+lb+" "+dw+" "+sl+" "+dj+" "+je+" "+sj+" "+'\n');
			}
		}
	}
}