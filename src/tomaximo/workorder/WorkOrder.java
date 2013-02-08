package tomaximo.workorder;
//����Ʊ
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WorkOrder {

	public static  int error = 0;//��¼���ݸ���ʧ�ܼ�¼
	public static  int endtj=0;//ͳ���ܸ�����������
	public static  int bfb=0;//ͳ���ܽ��Ȱٷֱ�
	public static  int allbfb=0;//��Ҫ���µ�������
	public static  DecimalFormat df=new DecimalFormat("0.0000"); //" "��д��ʽ��ģʽ �籣��2λ����"0.00"����
	public static  DecimalFormat format = new DecimalFormat("000");//���������ʽ����ʽ001,002

	public static  int bianhao=0;//���±��
	public static  String neirong="";//����ȥ��β�պ�����д��
	public static  String weix="";//Σ������,����ȥ��β�պ�����д��
	public static  String xgzy="";//���רҵ,����ȥ��β�պ�����д��
	
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
		try 
		{ 
				String ssupername="testworkorder";//������,������


	        	Statement tablebfb = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
	        	command=
						"select   * " +
		    		     "from " +
		    		     ssupername +
		      			" WHERE KIGENN IS NOT NULL ORDER BY ID";
				System.out.println(command);
				ResultSet ablebr = tablebfb.executeQuery(command);
				ablebr.last();
	  	        int  tablebc=ablebr.getRow(); // ��õ�ǰ�кţ��˴���Ϊ����¼�� ,��ЧʱΪ0 
	        	allbfb=tablebc;//��Ҫ���µ�������
	        	bfb = tablebc/100;//ÿ�ٷ�һ������
	        	System.out.println("��Ҫ���µ��ܼ�¼���ܹ�:"+tablebc);
	        	System.out.println("ÿ�ٷ�һ������:"+bfb);
	        	//�ٷֱ�
//				int supernameint=20;//�ӱ���
				Statement st = con.createStatement();
				
				System.out.println(command);
				ResultSet r = st.executeQuery(command);

				error = 0;//��¼���ݸ���ʧ�ܼ�¼
				endtj=0;//ͳ���ܸ�����������
				
				SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal =Calendar.getInstance();
				java.util.Date data = new java.util.Date();
				cal.setTime(data);//����ʱ��Ϊ��ǰʱ��
				long  timeOne=cal.getTimeInMillis();
				System.out.println("��ʼʱ��Ϊ:"+bartDateFormat.format(data));

				bianhao=0;
		        String sszykigennbak="";//��ع�����Ա��ַ�
		        int xuhao=0;//���
		        String sszykigenn="";//����רҵ+Ԥ�����ͱ���
				while (r.next()) {
					int id=r.getInt(1);//id
					neirong=r.getString(3) ;//����ȥ��β�պ�����д��
					if(neirong!=null){
						neirong=neirong.trim();//�ֶβ�Ϊ��ʱȥ��β��
					}
			        String kuigenn = r.getString(4);//Ԥ������
					String sszy =r.getString(5);//����רҵ


					Statement thistable=con.createStatement();
					String thiscommand="SELECT * FROM NEIRONG WHERE BIANHAO ='" +
					kuigenn+"' OR BIANHAO='"+sszy+"'";
					ResultSet thisr=thistable.executeQuery(thiscommand);
					weix="";
					xgzy="";
					while(thisr.next()){
						String j=thisr.getString(2);
						String t=thisr.getString(3);
						if(j.equals(kuigenn)){
							weix=t;
							if(t!=null){
								weix=weix.trim();
							}
						}
						if(j.equals(sszy)){
							xgzy=t;
							if(t!=null){
								xgzy=xgzy.trim();
							}
						}
					}
					thisr.close();
					thistable.close();
					sszykigenn=sszy+kuigenn;
					xuhao++;
					bianhao++;//���±��
//					System.out.println(sszykigenn+"=="+sszykigennbak);
			        if(!sszykigenn.equals(sszykigennbak))//�Ƚ��ַ�����
			        {
			        	xuhao=1;//���ϴε�����רҵ��Σ�շ��಻ͬ�������1
			        }
			        sszykigennbak="";
			        sszykigennbak=sszykigenn.substring(0,sszykigenn.length());
			        sszykigenn=sszykigenn+"C"+format.format(xuhao);//д���ֵ
							update(con, ssupername, id, sszykigenn);//���±�����
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

	
	  //����,id,azwzm,tablezb
	  public static void update(Connection con,String table,int id,String xuhao) 
		throws SQLException
		{
		  String command ="";
			try {
				command =
					"UPDATE " +
					table +
					" SET  id= "+bianhao
					+",neirong='"+neirong+"'"
					+",weix='"+weix+"'"
					+",xgzy='"+xgzy+"'"
					+",xuhao = '"+ xuhao+"'"
					+" WHERE   id="+id;
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
				System.out.println(command);
				System.out.println(error);
				// TODO: handle exception
			}
		}
}
