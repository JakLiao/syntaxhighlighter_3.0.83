package tomaximo;
//将KKS写入五张表
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

public class KksLocationsid {

	public static  int error = 0;//记录数据更新失败记录
	public static  int endtj=0;//统计总更新数据条数
	public static  int bfb=0;//统计总进度百分比
	public static  int allbfb=0;//需要更新的总条数
	//locancestor
	public static  int locancestorid=360000;
	//locoper
	public static  int locoperid=260000;
	//lochierarchy
	public static  int lochierarchyid=260000;
	//locstatus
	public static  int locstatusid=260000;
	//locations
	public static  int locationsid=260000;
	
	public static  String allsiteid="里彦电厂";
	public static  String allorgid="LYDC";
	
	public static void main(String[] args) 
	{ 
		DecimalFormat df=new DecimalFormat("0.0000"); //" "内写格式的模式 如保留2位就用"0.00"即可
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
			//（1）装载并注册数据库的JDBC驱动程序    
			//载入JDBC驱动：oracle安装目录下的jdbc\lib\classes12.jar
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			System.out.println("驱动程序已加载"); 
			//注册JDBC驱动：有些地方可不用此句
			java.sql.DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

			con = DriverManager.getConnection(url, user, password);
			System.out.println("OK,成功连接到数据库"); 
			
		}
		catch(Exception ex)
		{ 
			ex.printStackTrace(); 
		} 
		try 
		{ 
				String table="testtheallbakbak";//主表名,不更改

				
	        	Statement tablebfb = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
	        	command=
	        			"select   ID,SBMC,XGGYM,AZWZM,DDCSM,SCSYM,ERROR,TABLEZB,ERRORBAK,IDBAK " +
	        			"  from " +
	        			table+
	        			" where tablezb=60 order by xggym";
				System.out.println(command);
				ResultSet ablebr = tablebfb.executeQuery(command);
				ablebr.last();
	  	        int  tablebc=ablebr.getRow(); // 获得当前行号：此处即为最大记录数 ,无效时为0 
	        	ablebr.close();
	        	tablebfb.close();
	        	allbfb=tablebc;//需要更新的总条数
	        	bfb = tablebc/100;//每百分一的条数
	        	System.out.println("需要更新的总记录数总共:"+tablebc);
	        	//百分比
//				int supernameint=20;//子表编号
				Statement st = con.createStatement();
				
				System.out.println(command);
				ResultSet r = st.executeQuery(command);
				error = 0;//记录数据更新失败记录
				
				SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal =Calendar.getInstance();
				java.util.Date data = new java.util.Date();
				cal.setTime(data);//设置时间为当前时间
				long  timeOne=cal.getTimeInMillis();
				System.out.println("开始时间为:"+bartDateFormat.format(data));

				int theerror=0;
				while(r.next()){
					String sbmc = r.getString(2);//设备名称
					String xggym = r.getString(3);//相关工艺码
					String scsym = r.getString(6);//上层索引码
					int id=r.getInt(10);//ID
					error=0;
		        	updateLocations(con, table, id, sbmc, xggym,scsym);
		        	updateLocstatus(con, table, id, sbmc, xggym,scsym);
		        	updateLoCoper(con, table, id, sbmc, xggym,scsym);
		        	updateLocHiEraRchy(con, table, id, sbmc, xggym, scsym);
					updateLoCanCesTor(con, table, id, sbmc, xggym, scsym); 
					if(error>0){
						theerror++;
					}else{
						endtj++;
						
						//适合在控制台运行,显示百分比START
						float a=(float)endtj/allbfb*100;
						String b="当前进度为:       ["+String.valueOf(df.format(a))+"%]  "+String.valueOf(endtj);
						for (int k = 0; k < b.length(); k++) {
							System.out.print( "\b\b\b\b");
						}
					    System.out.print( "\b\b" + b +"  ");
					  //适合在控制台运行END
					    
					    //适合在Eclipse下运行
//						if(endtj%bfb==0&&endtj/bfb<101){
//							System.out.println("当前进度为: "+endtj/bfb+"%");//每一个百分点显示一次进度
//						}
					}
				}
				
				

				System.out.println("表  "+table+"  更新结束!");
				System.out.println("一共更新成功"+endtj+"次!");
				System.out.println("一共更新失败"+theerror+"次!");
				
				data = new java.util.Date();
				cal.setTime(data);//设置时间
				long  timeTwo=cal.getTimeInMillis();
				long  daysapart = (timeTwo-timeOne)/(1000*60);//分钟
				long  daysaparts = (timeTwo-timeOne)/1000%60;//获得总秒数后取除以60的余数即为秒数
				System.out.println("结束时间为:"+bartDateFormat.format(data)); 
				System.out.println("共花费时间为"+daysapart+"分"+daysaparts+"秒");

//				System.out.println("回车键继续!");
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

	  //表名,id,azwzm,tablezb
	  public static void updateLocations(Connection con,String table,int id,String sbmc,String xggym,String scsym) 
		throws SQLException
		{
		  String command="";
		  locationsid++;
			try {
					command =
					"insert into " +
					"locations" +
					 " (" +
					 "Location," +
					 "Description," +
					 "Type," +
					 "ChangeBy," +
					 "ChangeDate," +
					 "Disabled," +
					 "SiteId," +
					 "Orgid," +
					 "Isdefault," +
					 "Status," +
					 "LoCatIOnSid," +
					 "UseInPopr," +
					 "Langcode," +
					 "Hasld," +
					 "Autowogen," +
					 "StatusDate," +
					 "RowStamp" +
					 " ) values( "  +			//插入数据
					 "'"+xggym +"',"+			//Location
					 "'"+sbmc +"',"+		//Description
					 "'OPERATING'," +				//Type
					 "'MAXADMIN'," +			//ChangeBy
					 "TO_DATE( '2011/11/27 16:16:16', 'YYYY-MM-DD HH24:MI:SS')," +		//ChangeDate
					 "0," +			//Disabled
					 "'"+allsiteid+"'," +			//SiteId
					 "'"+allorgid+"'," +				//Orgid
					 "0," +			//Isdefault	
					 "'OPERATING'," +			//Status
					 locationsid+"," +		//LoCatIOnSid为空即可
					 "0," +			//UseInPopr
					 "'ZH'," +			//Langcode
					 "0," +				//Hasld
					 "0," +			//Autowogen
					 "TO_DATE( '2011/11/27 16:16:16', 'YYYY-MM-DD HH24:MI:SS')," +		//StatusDate
					 "null )";			//RowStamp为空即可
//				System.out.println(command);
				PreparedStatement pst = con.prepareStatement(command);
  	        pst.execute();
  	        pst.close();//更新后关闭此线程,不然更新数据多了就会异常
			} catch (Exception e) {
				System.out.println(command);
				error++;
				e.printStackTrace();
				System.out.println(error);
				// TODO: handle exception
			}
		}

	  public static void updateLocstatus(Connection con,String table,int id,String sbmc,String xggym,String scsym) 
		throws SQLException
		{
		  String command="";
		  locstatusid++;
			try {
				command =
					"insert into " +
					"LocstAtUs" +
					 " (" +
					  "Location,"+
					  "Status,"+
					  "ChangeBy,"+
					  "ChangeDate,"+
					  "Memo,"+
					  "SiteId,"+
					  "Orgid,"+
					  "LocStatusId,"+
					  "RowStamp"+
					 " ) values( "  +			
					 "'"+xggym+"',"+		//Location
					  "'OPERATING',"+		//Status
					  "'MAXADMIN',"+		//ChangeBy
					  "TO_DATE( '2011/11/27 16:16:16', 'YYYY-MM-DD HH24:MI:SS')," +	//ChangeDate
					  "null,"+			//Memo 为空即可	
						 "'"+allsiteid+"'," +			//SiteId
						 "'"+allorgid+"'," +				//Orgid
						 locstatusid+ ","+		//LocStatusId值为空即可
					 "null )";			//RowStamp为空即可
//				System.out.println(command);
				PreparedStatement pst = con.prepareStatement(command);
				pst.execute();
				pst.close();//更新后关闭此线程,不然更新数据多了就会异常
			} catch (Exception e) {
				System.out.println(command);
				error++;
				e.printStackTrace();
				System.out.println(error);
				// TODO: handle exception
			}
		}

	  public static void updateLoCoper(Connection con,String table,int id,String sbmc,String xggym,String scsym) 
		throws SQLException
		{
		  String command ="";
		  locoperid++;
			try {
				command =
					"insert into " +
					"LoCoper" +
					 " (" +
					 "Location," +
					 "SiteId," +
					 "Orgid," +
					 "locoperid"+
					 " ) values( "  +			//插入数据
					 "'"+xggym +"',"+			//Location
					 "'"+allsiteid+"'," +			//SiteId
					 "'"+allorgid+"'," +				//Orgid
					 locoperid+//locoperid
					 " )";			//
//				System.out.println(command);
				PreparedStatement pst = con.prepareStatement(command);
				pst.execute();
				pst.close();//更新后关闭此线程,不然更新数据多了就会异常
			} catch (Exception e) {
				System.out.println(command);
				error++;
				e.printStackTrace();
				System.out.println(error);
				// TODO: handle exception
			}
		}

	  public static void updateLocHiEraRchy(Connection con,String table,int id,String sbmc,String xggym,String scsym) 
		throws SQLException
		{
		  String command ="";
		  lochierarchyid++;
		  int 	Children=0;//父子标识,如果XGGYM为子值,则为0,父值则为1
			try {
				Statement thissa = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
	  	        String thiscommand=
	  	        	"select ID,SBMC,XGGYM,AZWZM,DDCSM,SCSYM,ERROR,TABLEZB,ERRORBAK,IDBAK " +
	  	        	"from " +
	  	        	table +
	  	        	" WHERE scsym ='"+xggym+"' ";
//	  	      System.out.println(thiscommand);
				ResultSet thisa = thissa.executeQuery(thiscommand);
				thisa.last();
	  	        int  thisc=thisa.getRow(); // 获得当前行号：此处即为最大记录数 ,无效时为0 
//	  	      System.out.println(thisc);
	  	        if(thisc>0){
	  	        	Children=1;
	  	        }
	  	        thisa.close();//更新后关闭此线程,不然更新数据多了就会异常
	  	        thissa.close();//更新后关闭此线程,不然更新数据多了就会异常
				
				
				command =
					"insert into " +
					"LocHiEraRchy" +
					 " (" +
					 "Location," +
					 "Parent,"+
					 "SystemId,"+
					 "Children,"+		//父子标识,如果XGGYM为子值,则为0,父值则为1
					 "SiteId," +
					 "Orgid," +
					 "lochierarchyid"+
					 " ) values( "  +			//插入数据
					 "'"+xggym +"',"+			//Location
					 "'"+scsym +"',"+
					 "'PRIMARY',"+
					 Children+","+
					 "'"+allsiteid+"'," +			//SiteId
					 "'"+allorgid+"'," +				//Orgid
					 lochierarchyid+			//lochierarchyid
					 " )";			//
//				System.out.println(command);
				PreparedStatement pst = con.prepareStatement(command);
				pst.execute();
				pst.close();//更新后关闭此线程,不然更新数据多了就会异常
			} catch (Exception e) {
				System.out.println(command);
				error++;
				e.printStackTrace();
				System.out.println(error);
				// TODO: handle exception
			}
		}

	  public static void updateLoCanCesTor(Connection con,String table,int id,String sbmc,String xggym,String scsym) 
		throws SQLException
		{
		  String command ="";
		  String ancestor=xggym;//相关工艺码的索引,第一次写入的的索引跟相关工艺码相同
			try {
				while(1==1){
					  locancestorid++;
					command =
						"insert into " +
						"LoCanCesTor" +
						 " (" +
						 "Location," +
						 "Ancestor,"+
						 "SystemId,"+
						 "SiteId," +
						 "Orgid," +
						 "locancestorid"+
						 " ) values( "  +			//插入数据
						 "'"+xggym +"',"+			//Location
						 "'"+ancestor +"',"+
						 "'PRIMARY',"+
						 "'"+allsiteid+"'," +			//SiteId
						 "'"+allorgid+"'," +				//Orgid
						 locancestorid+			//locancestorid
						 " )";			//
//					System.out.println(command);
					PreparedStatement pst = con.prepareStatement(command);
			        pst.execute();
			        pst.close();//更新后关闭此线程,不然更新数据多了就会异常
					
					Statement thissa = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		  	        String thiscommand=
		  	        	"select ID,SBMC,XGGYM,AZWZM,DDCSM,SCSYM,ERROR,TABLEZB,ERRORBAK,IDBAK " +
		  	        	"from " +
		  	        	table +
		  	        	" WHERE xggym ='"+ancestor+"' ";
//		  	      	System.out.println(thiscommand);
					ResultSet thisa = thissa.executeQuery(thiscommand);
					thisa.last();
		  	        int  thisc=thisa.getRow(); // 获得当前行号：此处即为最大记录数 ,无效时为0 
		  	        String thisscsym=thisa.getString(6);
		  	        thisa.close();//更新后关闭此线程,不然更新数据多了就会异常
		  	        thissa.close();//更新后关闭此线程,不然更新数据多了就会异常
//		  	      	System.out.println(thisc);
		  	        if(thisc==0){
		  	        	break;
		  	        }
		  	        ancestor=thisscsym.substring(0,thisscsym.length());
		  	        if(ancestor.equals("里彦电厂")){
		  	        	break;
		  	        }
		  	        if(ancestor.equals(xggym)){
		  	        	System.out.println("进入死循环,上层索引码与相关工艺码相同!   "+ancestor);
			  	      	System.out.println(thisc);
		  	        }
				}
			} catch (Exception e) {
				System.out.println(command);
				error++;
				e.printStackTrace();
				System.out.println(error);
				// TODO: handle exception
			}
		}
}
