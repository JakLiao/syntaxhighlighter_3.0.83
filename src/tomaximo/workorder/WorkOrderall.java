package tomaximo.workorder;
//����Ʊ,д�뵽3����,��������
//select * from HAZARD ;
//select * from HAZARDPREC;
//select * from PRECAUTION ;
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

public class WorkOrderall {
 
	public static  int error = 0;//��¼���ݸ���ʧ�ܼ�¼
	public static  int endtj=0;//ͳ���ܸ�����������
	public static  int bfb=0;//ͳ���ܽ��Ȱٷֱ�
	public static  int allbfb=0;//��Ҫ���µ�������
	public static  DecimalFormat df=new DecimalFormat("0.0000"); //" "��д��ʽ��ģʽ �籣��2λ����"0.00"����
	public static  DecimalFormat format = new DecimalFormat("0000");//���������ʽ����ʽ001,002


	public static  String neirong="";
	
	public static  int precautionid=0;//precaution
	public static  int precautionrowstamp=0;//rowstamp
	public static  int hazardprecid=0;//hazardprec
	public static  int hazardprecrowstamp=0;//rowstamp
	
	public static  String allsiteid="����糧";
	public static  String allorgid="LYDC";
	
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
		      			" WHERE KIGENN IS NOT NULL ORDER BY NEIRONG,KIGENN";
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

				
		        String neirongbak="";//���ݱȽ��ı�
				String kigennbak="";
		        int xuhao=1000;//���
		        String testxuhao="";//����רҵ+Ԥ�����ͱ���
				int theerror=0;
				while (r.next()) {
//					int id=r.getInt(1);//id
					neirong=r.getString(3) ;//����ȥ��β�պ�����д��
			        String kigenn = r.getString(4);//Ԥ������
//					String sszy =r.getString(5);//����רҵ

					
//					System.out.println(sszykigenn+"=="+sszykigennbak);
			        if(neirong.equals(neirongbak)&&kigenn.equals(kigennbak)){
			        	continue;//�������Σ�����Ͷ���ͬ�������˴�ѭ��
			        }
			        if(!neirong.equals(neirongbak))//�Ƚ��ַ�����
			        {
			        	xuhao++;//���ϴε����ݲ�ͬ�����+1,���Ҳ��뵽��PRECAUTION
			        	updatePrecaution(con, xuhao);
			        }
			        kigennbak="";
			        kigennbak=kigenn.substring(0,kigenn.length());
			        neirongbak="";
			        neirongbak=neirong.substring(0,neirong.length());
			        testxuhao=format.format(xuhao);//д���ֵ
			        updateHazardprec(con, kigenn, xuhao);//����������

					if(error>0){
						theerror++;
					}else{
						endtj++;
						
						//�ʺ��ڿ���̨����,��ʾ�ٷֱ�START
						float a=(float)endtj/allbfb*100;
						String b="��ǰ����Ϊ:       ["+String.valueOf(df.format(a))+"%]  "+String.valueOf(endtj);
						for (int k = 0; k < b.length(); k++) {
							System.out.print( "\b\b\b\b");
						}
					    System.out.print( "\b\b" + b +"  ");
					}

				}

				System.out.println("��  "+ssupername+"  ���½���!");
				System.out.println("һ�����³ɹ�"+endtj+"��!");
				System.out.println("һ������ʧ��"+theerror+"��!");
				
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
	  public static void updateHazardprec(Connection con,String kigenn,int xuhao) 
		throws SQLException
		{
		  String command ="";
		  hazardprecid++;
		  hazardprecrowstamp++;
			try {
				command =
					"insert into HAZARDPREC(" +
					"HAZARDID," +
					"PRECAUTIONID," +
					"ORGID," +
					"SITEID," +
					"HAZARDPRECID," +
					"ROWSTAMP"+
					")values("
					+"'"+kigenn+"',"		//:HAZARDID,	//Σ��������
					+"'"+xuhao+"',"			//:PRECAUTIONID,//Ԥ����ʩ�±��
					+"'"+allorgid+"',"  	//:ORGID,
					+"'"+allsiteid+"',"  	//:SITEID,
					+"'"+hazardprecid+"',"  	//:HAZARDPRECID,
					+hazardprecrowstamp
					+")";
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
    	        
			} catch (Exception e) {
				e.printStackTrace();
				error++;
				System.out.println(command);
				System.out.println(error);
				// TODO: handle exception
			}
		}

//����,id,azwzm,tablezb
public static void updatePrecaution(Connection con,int xuhao) 
	throws SQLException
	{
	  String command ="";
	  precautionid++;
	  precautionrowstamp++;
		try {
			command =
				"insert into PRECAUTION(" +
				"PRECAUTIONID," +
				"THISDESCRIPTION," +
				"PREC10," +
				"ORGID," +
				"SITEID," +
				"PRECAUTIONUID," +
				"LANGCODE," +
				"HASLD," +
				"ROWSTAMP"+
				")values("
				+"'"+xuhao+"',"			//:PRECAUTIONID,//Ԥ����ʩ�±��
				+"'"+neirong+"',"  		//:DESCRIPTION,//����
				+"'"+0+"',"  			//:PREC10,
				+"'"+allorgid+"',"  	//:ORGID,
				+"'"+allsiteid+"',"  	//:SITEID,
				+"'"+precautionid+"',"   //:PRECAUTIONUID,
				+"'ZH',"  				//:LANGCODE,
				+"'0',"					//:HASLD
				+precautionrowstamp
				+")";
//			System.out.println(command);
			PreparedStatement pst = con.prepareStatement(command);
	        pst.execute();
	        pst.close();//���º�رմ��߳�,��Ȼ�������ݶ��˾ͻ��쳣
	        
	        
	         // �������Ϣ
//	        System.out.print(id+"\t\t");
//	        System.out.print(stringLength(s,60));//���ַ��ܳ���Ϊ60
//	        System.out.print(stringLength(xggym,20));
//	        System.out.print(stringLength(scsym,20));
//	        System.out.print(theerror+"\t\t");
//	    	System.out.println(ssupername);

		} catch (Exception e) {
			e.printStackTrace();
			error++;
			System.out.println(command);
			System.out.println(error);
			// TODO: handle exception
		}
	}
}
