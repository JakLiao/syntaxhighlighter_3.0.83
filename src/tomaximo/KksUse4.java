package tomaximo;
//��Ӹ�����
//String tablename="testhszy";
//String ssupername="2";//H0LK�ĸ�����,���
//int ssupernameint=5;//����ַ�Ϊ5λ,���ø�����Ϊssupername
//int supernameint=5;//
//int superint=7;//���Ƶ��������ַ���,��������ַ���������ͬ����ǰ'supernameint'λ��Ϊ������
//				//�ַ�����ͬ����ǰ7λ��Ϊ������

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class KksUse4 {

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
			String tablename="testrglyrgbm3djz";
			String ssupername="45";//H0LK�ĸ�����,���
			int ssupernameint=5;//����ַ�Ϊ5λ,���ø�����Ϊssupername
			int supernameint=5;//
			int superint=7;//���Ƶ��������ַ���,��������ַ���������ͬ����ǰ'supernameint'λ��Ϊ������
							//�ַ�����ͬ����ǰ7λ��Ϊ������
	      Statement st = con.createStatement();
	      ResultSet r = st.executeQuery(
	    		  "select * " +
	    		     "from " +
	    		     tablename +
	      			" ORDER BY xggym");
	        int intddcsm =10000;
	        int intddcsmbak=10000;
	        String xggymbak="";//��ع�����Ա��ַ�
	        String sbmcbak="";
	        String scsym="";//��ع�����ĸ������
	        int error = 0;//��¼���ݸ���ʧ�ܼ�¼
	        int endtj=0;//ͳ���ܸ�����������
	      while (r.next()) {
	    	//
	    	int id=r.getInt(1);
	        s = r.getString(2);
	    	if (s==null) {
	    		s ="";
			} 
		        String xggym =r.getString(3);//ȥ�ո���replaceAll���滻�ո�
		        scsym="";//��ع�����ĸ������
	    	if (xggym==null) {
	    		xggym ="";
			}else {
				xggym =xggym.replaceAll(" ", "");//ȥ�ո���replaceAll���滻�ո�
				if(xggym.length()==ssupernameint)//�Ƚ��ַ�
				{
					scsym=ssupername.substring(0,ssupername.length());
				}else {
			    	if(xggym.length()==superint){
						scsym=xggym.substring(0,supernameint);
			    	} else {
			    		if(xggym.length()>superint){
				    		scsym =xggym.substring(0,superint);
			    		}else {
			    			scsym ="";
						}
					}
				}
			}
	        try {
		        PreparedStatement pst = con.prepareStatement(
		            	"UPDATE " +
		            	tablename +
		            	" SET  "
		            	+"azwzm = '"+ scsym+"' "
		            	+",ID ="+id
		            	+" WHERE   ID="+id);
		    	      //pst.setString(2,s);  //�ô˾����쳣
		    	        pst.execute();
		    	        pst.close();//���º�رմ��߳�,��Ȼ�������ݶ��˾ͻ��쳣
		    	        /*
		    	         * ���xggym�Ա���Ϣ
		    	         */
//		    	        System.out.println("д�����\t"
//		    	        		+stringLength(xggym,40)//�ַ�����Ϊ40
//		    	        		+stringLength(xggymbak,40)//�ַ�����Ϊ40
//		    	        		+xggym.equals(xggymbak)+"\t"+intddcsm);
		    	        /*
		    	         * �������Ϣ
		    	         */
		    	        System.out.print(id+"\t\t");
		    	        System.out.print(stringLength(s,60));//���ַ��ܳ���Ϊ60
		    	        System.out.print(stringLength(xggym,20));
		    	    	System.out.println("\t\t"+scsym);
		    	        endtj++;//ͳ���ܸ�����������
			} catch (Exception e) {
				e.printStackTrace(); 
				
				error++;
				System.out.println(error);
				// TODO: handle exception
			}
	      	}

	      r.close();
	      st.close();
	      con.close(); 
			System.out.println("��  "+tablename+"  ���½���!");
			System.out.println("һ�����³ɹ�"+endtj+"��!");
			System.out.println("һ������ʧ��"+error+"��!");
			
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
}
