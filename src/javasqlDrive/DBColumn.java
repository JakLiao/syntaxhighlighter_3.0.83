package javasqlDrive;
import java.sql.*;
import java.io.*; 
public class DBColumn 
{
	public static void main(String[] args) 
	{ 
		Connection con=null; 
		Statement sm=null; 
		String command=null; 
		ResultSet rs=null; 
		String tableName=null; 
		String cName=null; 
		String result=null; 
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in)); 
		try 
		{ 
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver"); 
			System.out.println("驱动程序已加载"); 
			//SQL SERVER的登陆方式必须为使用SQL SERVER密码登陆认证方式 
			//("jdbc:microsoft:sqlserver://服务器地址:访问端口","SQL用户名","SQL密码"); 
			con=DriverManager.getConnection("jdbc:microsoft:sqlserver://127.0.0.1:1433","sa",""); 
			con.setCatalog("mydata");//数据库名
			System.out.println("OK,成功连接到数据库mydata"); 
		}
		catch(Exception ex)
		{ 
			ex.printStackTrace(); 
		} 
		try 
		{ 
			sm=con.createStatement(); 
			System.out.println("输入表名asus "); 
			//tableName=input.readLine(); //手动输入表名
			//tableName="TeacherBasicInf";//设置表名为TeacherBasicInf 
			tableName="asus";//设置表名为asus
			while(true)
			{ 
				System.out.println("输入列名(为空时程序结束):"); 
				cName=input.readLine(); //输入列名name
				if(cName.equalsIgnoreCase("")) 
					break; 
				command="select "+cName+" from "+tableName; 
				rs=sm.executeQuery(command); 
				if(!rs.next()) 
					System.out.println("表名或列名输入有误"); 
				else
				{ 
					System.out.println("查询结果为:"); 
					do 
					{ 
						result=rs.getString(cName); 
						//result=new String(result.getBytes("ISO-8859-1"),"GB2312"); 
						System.out.println(rs.getString(1));//System.out.println(result); 
					}
					while(rs.next()); 
					} 
			} 
			System.out.println("查询结束!");
		}
		catch(Exception ex)
		{ 
			ex.printStackTrace(); 
		} 
	} 
}