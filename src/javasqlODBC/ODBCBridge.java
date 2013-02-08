package javasqlODBC;
import java.io.BufferedReader;
import java.io.InputStreamReader; 
import java.sql.*;

public class ODBCBridge {
	public static void main(String[] args) { 
		String url="jdbc:odbc:wzgl"; //wzglΪ����Դ����
		Statement sm=null; 
		String command=null; 
		ResultSet rs=null; 
		String tableName=null; 
		String cName=null; 
		String result=null; 
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in)); 
		try { 
			try { 
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); //�������� 
			}
			catch(ClassNotFoundException e){ 
				System.out.println("Can not load Jdbc-Odbc Bridge Driver"); 
				System.err.print("ClassNotFoundException:"); 
				System.err.println(e.getMessage()); 
			} 
			Connection con=DriverManager.getConnection(url,"USER","PASSWORD"); //ʹ��SQL-SERVER2000��֤ 
			DatabaseMetaData dmd=con.getMetaData(); //DMDΪ���ӵ���Ӧ��� 
			System.out.println("���ӵ����ݿ�:"+dmd.getURL()); 
			System.out.println("��������:"+dmd.getDriverName()); 
			sm=con.createStatement(); 
			System.out.println("�������"); //
			tableName=input.readLine(); 
			while(true) { 
				System.out.println("��������(Ϊ��ʱ�������):"); 
				cName=input.readLine(); 
				if(cName.equalsIgnoreCase("")) 
					break; 
				command="select "+cName+" from "+tableName; 
				rs=sm.executeQuery(command); //ִ�в�ѯ 
				if(!rs.next()) 
					System.out.println("������������������"); 
				else { 
					System.out.println("��ѯ���Ϊ:"); 
					do 
					{ 
						result=rs.getString(cName); 
						//���ݿ���������Ϊ���ģ�����ת������ 
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

