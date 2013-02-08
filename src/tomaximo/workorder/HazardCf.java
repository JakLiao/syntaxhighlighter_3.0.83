package tomaximo.workorder;
/*ɾ���ظ���Hazard���е�����,�����±��
 *ɾ��Description�ֶ���β��
 *ע��:�뽫Hazard����������ݲ���testworkorder����,��Ȼ�����֮���������
*/
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

public class HazardCf {

	public static  int error = 0;//��¼���ݸ���ʧ�ܼ�¼
	public static  int endtj=0;//ͳ���ܸ�����������
	public static  int bfb=0;//ͳ���ܽ��Ȱٷֱ�
	public static  int allbfb=0;//��Ҫ���µ�������
	public static  DecimalFormat df=new DecimalFormat("0.0000"); //" "��д��ʽ��ģʽ �籣��2λ����"0.00"����
	public static  DecimalFormat format = new DecimalFormat("0000");//���������ʽ����ʽ001,002


	public static  String neirong="";
	
	public static  int precautionid=1;//precaution
	public static  int hazardprecid=1;//hazardprec
	public static  String allsiteid="����糧";
	public static  String allorgid="LYDC";
	
	public static void main(String[] args)  
	{ 
		Connection con=null; 
		String command=null; 
		String url="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user="asus";
		String password="asus";
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
				String ssupername="HAZARD";//������,������


	        	Statement tablebfb = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
	        	command=
						"select   * " +
		    		     "from " +
		    		     ssupername +
		      			" WHERE hazardid IS NOT NULL ORDER BY hazardid";
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

				Boolean thisupdate;
		        int xuhao=5000;//���
				int theerror=0;
				while (r.next()) {
					neirong=r.getString(2) ;//����ȥ��β�պ�����д��
			        String kigenn = r.getString(1);//Ԥ������,���
//					String sszy =r.getString(5);//����רҵ
			        thisupdate=true;
			        
			        Statement thistable = con.createStatement();
		        	String thiscommand="" +
		        	"select   * " +
	    		     "from " +
	    		     ssupername +
	      			" WHERE HAZARDID!='"+kigenn+
	      			"' AND DESCRIPTION ='" +neirong+
	      			"' ORDER BY hazardid";
		        		;
//						System.out.println(thiscommand);
		        	ResultSet thisr = thistable.executeQuery(thiscommand);
		        		while(thisr.next()){

		        			String thiskigenn=thisr.getString(1);//Ԥ������,���
		        			int xuh=Integer.parseInt(thiskigenn.substring(1,thiskigenn.length()));
		        			int xuha=Integer.parseInt(kigenn.substring(1,kigenn.length()));
//							System.out.println("updateTestworkorder  xuh="+xuh+"  xuhao="+xuha);
		        			if(xuha>xuh){
		        				updateTestworkorder(con, thiskigenn,kigenn );
//								System.out.println("updateTestworkorder");
		        				deleteHAZARD(con, kigenn);
//								System.out.println("deleteHAZARD");
		        				thisr.close();
		        				thistable.close();
		        				thisupdate=false;
		        				break;
		        			}
		        		}
		        	if(kigenn.equals("K5888")){
		        		thisupdate=false;//K5888�ı�Ų�����
		        		System.out.println("/n"+kigenn+"/n");
		        	}
		        	if(thisupdate){
		        		//���±�־Ϊ��,��Ҫ����
						xuhao++;
						thisr.close();
						thistable.close();
				        updateHazard(con, kigenn, xuhao);//����������
				        updateTestworkorder(con, "K"+xuhao,kigenn );//���¸��ĺ�ĵ�workorder��
		        	}

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
	  public static void updateHazard(Connection con,String kigenn,int xuhao) 
		throws SQLException
		{
		  String command ="";
		  hazardprecid++;
			try {
				command =
					"update HAZARD SET HAZARDID='K" +
					xuhao +"' WHERE HAZARDID='" +kigenn+"'";
//				System.out.println(command);
				PreparedStatement pst = con.prepareStatement(command);
    	        pst.execute();
    	        pst.close();//���º�رմ��߳�,��Ȼ�������ݶ��˾ͻ��쳣
    	        
			} catch (Exception e) {
				e.printStackTrace();
				error++;
				System.out.println(command);
				System.out.println(error);
				// TODO: handle exception
			}
		}

//����,id,azwzm,tablezb
public static void updateTestworkorder(Connection con,String xuhao,String xuh) 
	throws SQLException
	{
	  String command ="";
		try {
			command =
				"UPDATE TESTWORKORDER SET KIGENN='" +
				xuhao+"' WHERE KIGENN='" +xuh+
				"'";
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
			System.out.println(command);
			error++;
			System.out.println(error);
			// TODO: handle exception
		}
	}
public static void deleteHAZARD(Connection con,String xuhao) 
throws SQLException
{
  String command ="";
	try {
		command =
			"DELETE FROM  HAZARD  WHERE HAZARDID='" +xuhao+"'";
//		System.out.println(command);
		PreparedStatement pst = con.prepareStatement(command);
        pst.execute();
        pst.close();//���º�رմ��߳�,��Ȼ�������ݶ��˾ͻ��쳣
        

         // �������Ϣ
//        System.out.print(id+"\t\t");
//        System.out.print(stringLength(s,60));//���ַ��ܳ���Ϊ60
//        System.out.print(stringLength(xggym,20));
//        System.out.print(stringLength(scsym,20));
//        System.out.print(theerror+"\t\t");
//    	System.out.println(ssupername);

	} catch (Exception e) {
		e.printStackTrace();
		System.out.println(command);
		error++;
		System.out.println(error);
		// TODO: handle exception
	}
}
}