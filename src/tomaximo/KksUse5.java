package tomaximo;
//װ45������һ���ܱ�
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class KksUse5 {

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
			//��1��װ�ز�ע�����ݿ��JDBC��������    
			//����JDBC������oracle��װĿ¼�µ�jdbc\lib\classes12.jar
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			System.out.println("���������Ѽ���"); 
			//ע��JDBC��������Щ�ط��ɲ��ô˾�
			java.sql.DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

			con = DriverManager.getConnection(url, user, password);
			System.out.println("OK,�ɹ����ӵ����ݿ�"); 
			
		}
		catch(Exception ex)
		{ 
			ex.printStackTrace(); 
		} 
	    char[] carray = new char[1000];
//	    Arrays.fill(carray, "");
	    String s = new String(carray);
		try 
		{ 
			/*
			 * ��������
			 */
//			 PreparedStatement pst = con.prepareStatement(
//	          "insert into test12(ID,SBMC) values(?,?)");
//				System.out.println("Ҫ5");
//	      pst.setString(1, "131");
//			System.out.println("Ҫ4");
//
//			System.out.println("Ҫ2");
////	      pst.setCharacterStream(2,
////	                             new InputStreamReader(new ByteArrayInputStream(s.
////	          getBytes())), s.length());
//	      pst.setString(2,"�л���");
//
//	      //pst.setString(2,s);  //�ô˾����쳣
//	      pst.execute();

			/*
			 * ��ѯ����
			 */
			String tableallname="TESTTHEALLBAKBAK";//������,������
//			int supernameint=20;//�ӱ���
			
			//�ӵ�һ�ű�ʼ
			for(int supernameint=1;supernameint<46;supernameint++)
			{
			String tablename=Tablename(supernameint);//ȡ�ӱ���
			String ssupername=""+supernameint;//H0LK�ĸ�����,���
	      Statement st = con.createStatement();
	      ResultSet r = st.executeQuery(
	    		  "select * " +
	    		     "from " +
	    		     tablename +
	      			" ORDER BY ID");
	        String scsym="";//��ع�����ĸ������
	        int error = 0;//��¼���ݸ���ʧ�ܼ�¼
	        int endtj=0;//ͳ���ܸ�����������
	      while (r.next()) {
	    	//
	    	int id=r.getInt(1);//id
	        s = r.getString(2);//�豸����
		    String xggym =r.getString(3);//��ع�����
		    scsym=r.getString(4);//�ϲ�������,��ع�����ĸ������
		    int theerror = r.getInt(7);//�����ʶ

	        try {
				 PreparedStatement pst = con.prepareStatement(
						 "insert into " +
						 tableallname +
						 "(ID," +
						 "SBMC," +
						 "XGGYM," +
						 "AZWZM," +
						 "ERROR," +
				 		 "TABLEZB) values(?,?,?,?,?,?)");
				 pst.setInt(1, id);
				 pst.setString(2, s);
				 pst.setString(3, xggym);
				 pst.setString(4, scsym);
				 pst.setInt(5, theerror);
				 pst.setString(6, ssupername);
		    	      //pst.setString(2,s);  //�ô˾����쳣
		    	        pst.execute();
		    	        pst.close();//���º�رմ��߳�,��Ȼ�������ݶ��˾ͻ��쳣
		    	        
		    	         // �������Ϣ
//		    	        System.out.print(id+"\t\t");
//		    	        System.out.print(stringLength(s,60));//���ַ��ܳ���Ϊ60
//		    	        System.out.print(stringLength(xggym,20));
//		    	        System.out.print(stringLength(scsym,20));
//		    	        System.out.print(theerror+"\t\t");
//		    	    	System.out.println(ssupername);
		    	        endtj++;//ͳ���ܸ�����������
			} catch (Exception e) {
				e.printStackTrace(); 
				
				error++;
				System.out.println(error);
				// TODO: handle exception
			}
	      	}

			System.out.println("��  "+tablename+"  ���½���!");
			System.out.println("һ�����³ɹ�"+endtj+"��!");
			System.out.println("һ������ʧ��"+error+"��!");
			System.out.println("�˱�ı���Ϊ: "+supernameint);
			
//			System.out.println("�س�������!");
//			Scanner scan=new Scanner(System.in); 
//		    int number1=scan.nextInt(); 
		      r.close();
		      st.close();
			}
		      con.close(); 
		}
		
		
		catch(Exception ex)
		{ 
			ex.printStackTrace(); 
		} 
	}

	/* ����ΪstringLength(�ַ���,����ܳ���)
	 * ���뷽��2,����ֻ�����ո���ַ���
	 */
	public static String stringLength(String s,int count) {
		  String str=s;
		  int j=0;
		  int i=0;
	        if (str!=null) {//�ַ�Ϊ���򳤶�Ϊ��
	        	j=length(str);
			}
		  for ( i = 0; i < count-j; i++) {
		   str+=" ";
		  }
		  
		  return str;
	}
	 
	 /** 
	  *   �ж�һ���ַ���Ascill�ַ����������ַ����纺���գ������ַ��� 
	  *   @param   char   c,   ��Ҫ�жϵ��ַ� 
	  *   @return   boolean,   ����true,Ascill�ַ� 
	  */ 
	  public static boolean isLetter(char c)   { 
		  int k = 0x80; 
		  return c/k == 0?true:false; 
	  } 
	  /** 
	  *   �õ�һ���ַ����ĳ���,��ʾ�ĳ���,һ�����ֻ��պ��ĳ���Ϊ2,Ӣ���ַ�����Ϊ1 
	  *   @param   String   s   ,��Ҫ�õ����ȵ��ַ��� 
	  *   @return   int,   �õ����ַ������� 
	  */ 
	  public static int length(String s){ 
		  char[] c=s.toCharArray(); 
		  int len=0; 
		  for(int i=0;i<c.length;i++) 
		  { 
			  len++; 
			  if(!isLetter(c[i])) 
			  { 
				  len++; 
			  } 
		  } 
		  return   len; 
	  } 
		public static String Tablename(int ssupername)
		{
		 	String kyo="";
			switch(ssupername)
			{				
				case 1:
					kyo="testkyjxt";
					break;
				case 2:
					kyo="test56dcc";//#5#6�������е����KKS�����
					break;
				case 3:
					kyo="test14chz";	
					break;
				case 4:
					kyo="test12tl";
					break;
				case 5:
					kyo="test34tl";
					break;
				case 6:
					kyo="test56shsss";
					break;
				case 7:
					kyo="test3qj";
					break;
				case 8:
					kyo="test4qj";
					break;
				case 9:
					kyo="test34qjgy";
					break;
				case 10:
					kyo="test5qj";
					break;
				case 11:
					kyo="test6qj";
					break;
				case 12:
					kyo="test56qjgy";
					break;
				case 13:
					kyo="test34hx";
					break;
				case 14:
					kyo="test56hx";
					break;
				case 15:
					kyo="testwscl";
					break;
				case 16:
					kyo="testsm";
					break;
				case 17:
					kyo="testsm1";
					break;
				case 18:
					kyo="test12dcc";//#1#2�������е����KKS�����
					break;
				case 19:
					kyo="test34dcc";//#3#4�������е����KKS�����
					break;
				case 20:
					kyo="test34glall";//3��4��¯רҵKKS������ܱ�
					break;
				case 21:
					kyo="test34glall4";
					break;
				case 22:
					kyo="test34glallgg";
					break;
				case 23:
					kyo="test56gl";
					break;
				case 24:
					kyo="test56gl5";
					break;
				case 25:
					kyo="test56glgg";
					break;
				case 26:
					kyo="test56glall";
					break;
				case 27:
					kyo="test56glallgys";
					break;
				case 28:
					kyo="testrg34glall";
					break;
				case 29:
					kyo="testrg34glall4";
					break;
				case 30:
					kyo="testrg34glallgg";
					break;
				case 31:
					kyo="testrg34gl";
					break;
				case 32:
					kyo="testrg34gl3";
					break;
				case 33:
					kyo="testrg34glgg";
					break;
				case 34:
					kyo="testrghxdccpd";
					break;
				case 35:
					kyo="testrghxdccpd34scl";
					break;
				case 36:
					kyo="testrghxdccpd56scl";
					break;
				case 37:
					kyo="testrghxdccpd34hxsq";
					break;
				case 38:
					kyo="testrghxdccpd5dcc";
					break;
				case 39:
					kyo="testrghxdccpd6dcc";
					break;
				case 40:
					kyo="testrghxdccpdpd";
					break;
				case 41:
					kyo="testrglyrgbm";
					break;
				case 42:
					kyo="testrglyrgbm6dzj";
					break;
				case 43:
					kyo="testrglyrgbm5jz";
					break;
				case 44:
					kyo="testrglyrgbm4djz";
					break;
				case 45:
					kyo="testrglyrgbm3djz";
					break;
			}
			return kyo;
		}
}
