package tomaximo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class KksUse {
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
			String tablename="test";
	      Statement st = con.createStatement();
	      ResultSet r = st.executeQuery(
	    		  "select * " +
	    		     "from " +
	    		     tablename +
	    		     " a " +
	    		    "where exists(select * " +
	    		    	"from " +
	    		    	tablename+
	    		    	" b " +
	    		       "where b.xggym=a.xggym " +
	      				  "AND a.id<>b.id)" +
	      			"ORDER BY xggym");

	        int intddcsm =100;
//	        String ddcsm = String.valueOf(intddcsm);//����ת��Ϊ�ַ���
//	        String ddcsm =Integer.toString(intddcsm);//����ת��Ϊ�ַ���
	        String ddcsm =""+intddcsm;//����ת��Ϊ�ַ���
			System.out.println(ddcsm);
	        String xggymbak="";//��ع�����Ա��ַ�
	        int error = 0;//��¼���ݸ���ʧ�ܼ�¼
	        
	      while (r.next()) {
	    	//
	    	int id=r.getInt(1);
	        s = r.getString(2);
	        String xggym =r.getString(3);
//	        ddcsm = String.valueOf(intddcsm);//����ת��Ϊ�ַ���
//	        ddcsm =Integer.toString(intddcsm);//����ת��Ϊ�ַ���
	        ddcsm =""+intddcsm;
	        if(!xggym.equals(xggymbak))//�Ƚ��ַ�����
	        {
//	        	System.out.println(xggym+"\t"+xggymbak+"\t"+xggym.equals(xggymbak));//����ר��
		        intddcsm++;
		        ddcsm =""+intddcsm;
//		        System.out.println("�����һ");//����ר��
	        }
	        try {
		        PreparedStatement pst = con.prepareStatement(
		            	"UPDATE " +
		            	tablename +
		            	" SET DDCSM = "+ddcsm+",ID ="+id+" WHERE   ID="+id);
		    	      //pst.setString(2,s);  //�ô˾����쳣
		    	        pst.execute();
		    	        pst.close();//���º�رմ��߳�,��Ȼ�������ݶ��˾ͻ��쳣
		    	        /*
		    	         * ����
		    	         */
//		    	        System.out.println("д�����\t"+xggym+"\t"+xggymbak+"\t"+xggym.equals(xggymbak)+"\t"+intddcsm);
		    	        System.out.println("д�����\t");
		    	        
		    	        System.out.println(xggym+"\t"+xggymbak+"\t"+xggym.equals(xggymbak)+"\t"+intddcsm);
				
			} catch (Exception e) {
				e.printStackTrace(); 
				error++;
				// TODO: handle exception
			}
			xggymbak="";
        	xggymbak=xggym.substring(0,xggym.length());
	      	}

	      r.close();
	      st.close();
	      con.close(); 
			System.out.println("��ѯ����!");
			System.out.println("һ������ʧ��"+error+"��!");
			
		}
		
		
		catch(Exception ex)
		{ 
			ex.printStackTrace(); 
		} 
	}

	private static String toString(int intddcsm) {
		// TODO Auto-generated method stub
		return null;
	} 
}
