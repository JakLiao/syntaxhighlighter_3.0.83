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
			System.out.println("���������Ѽ���"); 
			//SQL SERVER�ĵ�½��ʽ����Ϊʹ��SQL SERVER�����½��֤��ʽ 
			//("jdbc:microsoft:sqlserver://��������ַ:���ʶ˿�","SQL�û���","SQL����"); 
			con=DriverManager.getConnection("jdbc:microsoft:sqlserver://127.0.0.1:1433","sa",""); 
			con.setCatalog("mydata");//���ݿ���
			System.out.println("OK,�ɹ����ӵ����ݿ�mydata"); 
		}
		catch(Exception ex)
		{ 
			ex.printStackTrace(); 
		} 
		try 
		{ 
			sm=con.createStatement(); 
			System.out.println("�������asus "); 
			//tableName=input.readLine(); //�ֶ��������
			//tableName="TeacherBasicInf";//���ñ���ΪTeacherBasicInf 
			tableName="asus";//���ñ���Ϊasus
			while(true)
			{ 
				System.out.println("��������(Ϊ��ʱ�������):"); 
				cName=input.readLine(); //��������name
				if(cName.equalsIgnoreCase("")) 
					break; 
				command="select "+cName+" from "+tableName; 
				rs=sm.executeQuery(command); 
				if(!rs.next()) 
					System.out.println("������������������"); 
				else
				{ 
					System.out.println("��ѯ���Ϊ:"); 
					do 
					{ 
						result=rs.getString(cName); 
						//result=new String(result.getBytes("ISO-8859-1"),"GB2312"); 
						System.out.println(rs.getString(1));//System.out.println(result); 
					}
					while(rs.next()); 
					} 
			} 
			System.out.println("��ѯ����!");
		}
		catch(Exception ex)
		{ 
			ex.printStackTrace(); 
		} 
	} 
}