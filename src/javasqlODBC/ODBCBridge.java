package javasqlODBC;
import java.io.BufferedReader;
import java.io.InputStreamReader; 
import java.sql.*;

public class ODBCBridge {
	public static void main(String[] args) { 
		String url="jdbc:odbc:wzgl"; //wzgl为数据源名称
		Statement sm=null; 
		String command=null; 
		ResultSet rs=null; 
		String tableName=null; 
		String cName=null; 
		String result=null; 
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in)); 
		try { 
			try { 
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); //加载驱动 
			}
			catch(ClassNotFoundException e){ 
				System.out.println("Can not load Jdbc-Odbc Bridge Driver"); 
				System.err.print("ClassNotFoundException:"); 
				System.err.println(e.getMessage()); 
			} 
			Connection con=DriverManager.getConnection(url,"USER","PASSWORD"); //使用SQL-SERVER2000认证 
			DatabaseMetaData dmd=con.getMetaData(); //DMD为连接的相应情况 
			System.out.println("连接的数据库:"+dmd.getURL()); 
			System.out.println("驱动程序:"+dmd.getDriverName()); 
			sm=con.createStatement(); 
			System.out.println("输入表名"); //
			tableName=input.readLine(); 
			while(true) { 
				System.out.println("输入列名(为空时程序结束):"); 
				cName=input.readLine(); 
				if(cName.equalsIgnoreCase("")) 
					break; 
				command="select "+cName+" from "+tableName; 
				rs=sm.executeQuery(command); //执行查询 
				if(!rs.next()) 
					System.out.println("表名或列名输入有误"); 
				else { 
					System.out.println("查询结果为:"); 
					do 
					{ 
						result=rs.getString(cName); 
						//数据库语言设置为中文，不用转换编码 
						//result=new String(result.getBytes("ISO-8859-1"),"GB2312"); 
						System.out.println(result); 
					}while(rs.next()); 
				} 
			} 
		}catch(SQLException ex) { 
			System.out.println("SQLException:"); 
			while(ex!=null) { 
				System.out.println("Message:"+ex.getMessage()); 
				ex=ex.getNextException(); 
			} 
		}catch(Exception e) { 
			System.out.println("IOException"); 
		} 
	} 
} 

