package tomaximo;
/*KKS������ȫ���Զ�����,ȡ���߿պ�,ȡ�ַ���һλ��������һ���ո�,���в��,���δ��ƥ��������ַ���,ֱ�����ٵ�5λ,�����δ�ҵ����ÿ�
 * 
 */
//http://hi.baidu.com/jiangxinyi21/blog/item/556f2a2c98c49930d507421c.html
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import oracle.jdbc.driver.DatabaseError;

public class Kksall {
	
	public static  int error = 0;//��¼���ݸ���ʧ�ܼ�¼
	public static  int endtj=0;//ͳ���ܸ�����������
	public static  int bfb=0;//ͳ���ܽ��Ȱٷֱ�
	public static  int allbfb=0;//��Ҫ���µ�������
	public static  DecimalFormat df=new DecimalFormat("0.0000"); //" "��д��ʽ��ģʽ �籣��2λ����"0.00"����
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
				String ssupername="testtheallbakbak";//������,������
				//�ٷֱ�
	        	Statement tablebfb = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
	        	command=
					"select ID,SBMC,XGGYM,AZWZM,DDCSM,SCSYM,ERROR,TABLEZB,ERRORBAK,IDBAK " +
					"from " +
    		     	ssupername +
    		     	" WHERE xggym is not null and tablezb=60  "+
//					" and scsym='xxxxxxx'" +
					" ORDER BY xggym";
	        	System.out.println(command);
				ResultSet ablebr = tablebfb.executeQuery(command);
				ablebr.last();
	  	        int  tablebc=ablebr.getRow(); // ��õ�ǰ�кţ��˴���Ϊ����¼�� ,��ЧʱΪ0 
	        	allbfb=tablebc;//��Ҫ���µ�������
	        	bfb = tablebc/100;//ÿ�ٷ�һ������
	        	System.out.println("��Ҫ���µ��ܼ�¼���ܹ�:"+tablebc);
	        	//�ٷֱ�
//				int supernameint=20;//�ӱ���
				Statement st = con.createStatement();
				
				System.out.println(command);
				ResultSet r = st.executeQuery(command);
				String scsym="";//��ع�����ĸ������
				error = 0;//��¼���ݸ���ʧ�ܼ�¼
				endtj=0;//ͳ���ܸ�����������
				
				SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal =Calendar.getInstance();
				java.util.Date data = new java.util.Date();
				cal.setTime(data);//����ʱ��Ϊ��ǰʱ��
				long  timeOne=cal.getTimeInMillis();
				System.out.println("��ʼʱ��Ϊ:"+bartDateFormat.format(data));

				while (r.next()) {
					int idbak=r.getInt(10);//id
					String xggym =r.getString(3);//��ع�����
					scsym=xggym.substring(0,xggym.length());//�ϲ�������,�������ڹ�������ͬ
//					int theerror = r.getInt(7);//�����ʶ
					int tablezb = r.getInt(8);//רҵ��
					Boolean theok=false;//���Ϊ��,����������,Ϊ�����ÿ��ַ�
					if(scsym.length()>2){
						scsym=xggym.substring(0,xggym.length()-2);//�����ĸ�ֵ����ֵ����С����λ����
					}
					if(xggym.length()<8)
					{
						scsym=xggym.substring(0,1);//������С��8λ��ȡ��ֵ����ĸ
						theok=true;
					}else{
					for (int i = 1; scsym.length()>2; i++) {
				  	        Statement thissa = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				  	        String thiscommand=
				  	        	"select ID,SBMC,XGGYM,AZWZM,DDCSM,SCSYM,ERROR,TABLEZB,ERRORBAK,IDBAK " +
				  	        	"from " +
				  	        	ssupername +
				  	        	" WHERE xggym ='"+scsym+"' ";
//				  	      System.out.println(thiscommand);
							ResultSet thisa = thissa.executeQuery(thiscommand);
							thisa.last();
				  	        int  thisc=thisa.getRow(); // ��õ�ǰ�кţ��˴���Ϊ����¼�� ,��ЧʱΪ0 
//				  	      System.out.println(thisc);
				  	        if(thisc>0){
				  	        	theok=true;
					  	        thisa.close();//���º�رմ��߳�,��Ȼ�������ݶ��˾ͻ��쳣
					  	        thissa.close();//���º�رմ��߳�,��Ȼ�������ݶ��˾ͻ��쳣
				  	        	break;
				  	        }
				  	        thisa.close();//���º�رմ��߳�,��Ȼ�������ݶ��˾ͻ��쳣
				  	        thissa.close();//���º�رմ��߳�,��Ȼ�������ݶ��˾ͻ��쳣
							scsym=xggym.substring(0,xggym.length()-i);
					}
					}
					if (theok==false) {
						scsym="xxxxxxx";
					}
					update(con, ssupername, idbak, scsym, tablezb);//���±�����
				}

				System.out.println("��  "+ssupername+"  ���½���!");
				System.out.println("һ�����³ɹ�"+endtj+"��!");
				System.out.println("һ������ʧ��"+error+"��!");
				
				data = new java.util.Date();
				cal.setTime(data);//����ʱ��
				long  timeTwo=cal.getTimeInMillis();
				long  daysapart = (timeTwo-timeOne)/(1000*60);//����
				long  daysaparts = (timeTwo-timeOne)/1000%60;//�����������ȡ����60��������Ϊ����
				System.out.println("����ʱ��Ϊ:"+bartDateFormat.format(data)); 
				System.out.println("������ʱ��Ϊ"+daysapart+"��"+daysaparts+"��");

//				System.out.println("�س�������!");
//				Scanner scan=new Scanner(System.in); 
//		   	 	int number1=scan.nextInt(); 
				r.close();
				st.close();
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
	  //����,id,azwzm,tablezb
	  public static void update(Connection con,String table,int idbak,String azwzm,int tablezb) 
		throws SQLException
		{
			try {
				String command =
					"UPDATE " +
					table +
					" SET  "
					+"scsym = '"+ azwzm+"' "
					+" WHERE   IDbak="+idbak+" AND tablezb="+tablezb;
//				System.out.println(command);
				PreparedStatement pst = con.prepareStatement(command);
    	        pst.execute();
    	        pst.close();//���º�رմ��߳�,��Ȼ�������ݶ��˾ͻ��쳣
    	        
    	        
    	         // �������Ϣ
//    	        System.out.print(id+"\t\t");
//    	        System.out.print(stringLength(s,60));//���ַ��ܳ���Ϊ60
//    	        System.out.print(stringLength(xggym,20));
//    	        System.out.print(stringLength(scsym,20));
//    	        System.out.print(theerror+"\t\t");
//    	    	System.out.println(ssupername);
				endtj++;//ͳ���ܸ�����������

				//�ʺ��ڿ���̨����,��ʾ�ٷֱ�START
				float a=(float)endtj/allbfb*100;
				String b="��ǰ����Ϊ:       ["+String.valueOf(df.format(a))+"%]  "+String.valueOf(endtj);
				for (int k = 0; k < b.length(); k++) {
					System.out.print( "\b\b\b\b");
				}
			    System.out.print( "\b\b" + b +"  ");
			  //�ʺ��ڿ���̨����END
			    
			  //�ʺ���Eclipse������
//				if(bfb!=0)
//				{
//					if(endtj%bfb==0&&endtj/bfb<101){
//						System.out.println("��ǰ����Ϊ: "+endtj/bfb+"%");//ÿһ���ٷֵ���ʾһ�ν���
//					}
//				}
			} catch (Exception e) {
				e.printStackTrace();
				error++;
				System.out.println(error);
				// TODO: handle exception
			}
		}
}
