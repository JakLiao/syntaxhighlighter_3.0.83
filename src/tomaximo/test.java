package tomaximo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class test {

	public static  int error = 0;//记录数据更新失败记录
	public static  int endtj=0;//统计总更新数据条数
	public static  int bfb=0;//统计总进度百分比
	public static void main(String[] args) 
	{ 
		Connection con=null; 
		Statement sm=null; 
		String command=null; 
		ResultSet rs=null; 
		String tableName=null; 
		String cName=null; 
		String result=null; 
		String url="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
//		mxe.db.driver=oracle.jdbc.OracleDriver
		String user="asus";
		String password="asus";
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in)); 
		try 
		{ 
			//（1）装载并注册数据库的JDBC驱动程序    
			//载入JDBC驱动：oracle安装目录下的jdbc\lib\classes12.jar
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			System.out.println("驱动程序已加载"); 
			//注册JDBC驱动：有些地方可不用此句
			java.sql.DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

			con = DriverManager.getConnection(url, user, password);
			System.out.println("OK,成功连接到数据库"); 
			
		}
		catch(Exception ex)
		{ 
			ex.printStackTrace(); 
		} 
		try 
		{ 
				String ssupername="testtheallbakbak";//主表名,不更改

	        	Statement tablebfb = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
	        	command=
	        			"select * from testtheallbakbak " +
	        			"a where xggym LIKE '4%' AND " +
	        			" exists (select * from testtheallbakbak b where b.xggym = a.xggym " +
	        			" and a.id <> b.id and xggym is not null) order by xggym,sbmc";
				System.out.println(command);
				int  tablebc=1;
				while(tablebc>0){
					ResultSet ablebr = tablebfb.executeQuery(command);
					ablebr.last();
		  	         tablebc=ablebr.getRow(); // 获得当前行号：此处即为最大记录数 ,无效时为0 
		        	System.out.println("需要更新的总记录数总共:"+tablebc);
		        	  try
		              {
		                Thread.sleep(10000);//毫秒数
		                 } 
		               catch(InterruptedException e)
		               {
		                System.out.println("休眠异常!");
		               }
		            
		        	ablebr.close();
				}
	        	tablebfb.close();
		      	con.close(); 
		}
	    		catch(Exception ex)
	    		{ 
	    			ex.printStackTrace(); 
	    		} 
		}
}
