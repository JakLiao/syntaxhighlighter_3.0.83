package weblogic;
/*
 * 
	   ######################################################################
	   ## Developed by Chiang Kai-shek(shoukaiseki) <jiang28555@gmail.com> ##
	   ##                 12-01-06 Tokyo japan                             ##
	   ######################################################################

执行thisjava运行监控
监控原理为访问Maximo页面,获取cooking,查找cooking中是否还有初次进入页面时的信息,默认设置为"欢迎使用 Maximo"


PATH=E:/bea/user_projects/domains/base_domain/bin
为方便执行startweblogic.cmd而设置的环境变量


java运行版本为jdk1.7.0
此监控还不算完美,有待更新
因对使用HttpClient进行账号登录web不是很了解
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class AgentTimeJTextPane {

	public static int zenkai = 0;// 前回输出的字符数
	public static SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
	public static Calendar cal =Calendar.getInstance();
	public static Date date = new Date();
	public static Date rundate = new Date();//开始正常运行时间
	
	public static String urlstring = "";
	public static File txtfile = new File("weblogic.ora");
	public static boolean bool = true;// 上次定时器检测到服务停止标志

	public static long delay=1000;
	public static long period=6000;
	public static int cmdbool = 1;// 上次定时器检测到服务停止标志
	public static String cmdstop="cmd /k start stopWebLogic.cmd";
	public static String cmdstart="cmd /k start startWebLogic.cmd";
	public static String logo="欢迎使用 Maximo";
	// Maximo网址
	public static String theurl = "http://localhost:7001/maximo/webclient/login/login.jsp";
	public static int a = 0;

	public static long status=1;//检测状态,1为服务正在启动,2为下在运行,3为停止
	
	public static String explain="";
	
	public static JFrame frame;
	public static JTextPane textPane;
	public static JTextPane textPane2;//文本框2
	public AgentTimeJTextPane() throws UnsupportedEncodingException {
		// 初始化所有模块
		frame = new JFrame("Maximo监控程序");
		textPane = new JTextPane();
		textPane.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		textPane.setText("java版本为:" + System.getProperty("java.version")
				+ "\n换行");
		// 设置主框架的布局
		Container c = frame.getContentPane();
		// c.setLayout(new BorderLayout())


		JScrollPane jsp = new JScrollPane(textPane);// 新建一个滚动条界面，将文本框传入
		c.add(jsp, BorderLayout.CENTER);
		
		/*
		 *  文本框二
		 */
		textPane2 = new JTextPane();
		textPane2.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		JScrollPane jsp2 = new JScrollPane(textPane2);// 新建一个滚动条界面，将文本框传入
		c.add(jsp2, BorderLayout.SOUTH);


		/*
		 * 增加标签
		 */
		Label label = new Label("java Version:" + System.getProperty("java.version"));
		c.add(label, BorderLayout.NORTH);
		
		// 利用无名内隐类，增加窗口事件
		frame.addWindowListener(new WindowAdapter() {
			public void WindowClosing(WindowEvent e) {
				// 释放资源，退出程序
				frame.dispose();
				System.exit(0);
			}
		});

		frame.setSize(700, 500);
		// 隐藏frame的标题栏,此功暂时关闭，以方便使用window事件
		// setUndecorated(true);
		frame.setLocation(200, 150);
		frame.show();
	}
	public static void main(String[] args) throws IOException {
		AgentTimeJTextPane agenttimjtextpane=new AgentTimeJTextPane();
		readini();

		textPane2.setText("\tChiang Kai-shek(shoukaiseki) <jiang28555@gmail.com>  " +
				"\t2012-01-06 Tokyo japan ");

		cal.setTime(date);//设置时间为当前时间
		long  timeOne=cal.getTimeInMillis();
		date = new Date();
//		System.out.println("开始监测时间为:"+bartDateFormat.format(data));
		explainaddln("开始监测时间为:"+bartDateFormat.format(date));
		explainaddln("");
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {// 实例中的的方法
				try {
						method1();
				} catch (IOException e) {
					// TODO Auto-generated catch block 
					e.printStackTrace();
				}// 定时器到后执行的方法,一般在定时器到后的内容具体在外面写
 catch (Exception e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
			}
		}, delay, period);
		/*
		 * schedule(TimerTask task, long delay) 大意是在延时delay毫秒后执行task
		 * schedule(TimerTask task, long delay, long period)
		 * 在延时delay毫秒后重复的执行task，周期是period毫秒
		 */
	}

	public static void method1() throws Exception {
		// 文件保存路径
		URL url = null;
		HttpURLConnection httpConn = null;
		InputStream in = null;

		try {
			url = new URL(theurl);
			httpConn = (HttpURLConnection) url.openConnection();
			HttpURLConnection.setFollowRedirects(true);
			httpConn.setRequestMethod("GET");
			httpConn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");

			// logger.info(httpConn.getResponseMessage());
			in = httpConn.getInputStream();
			// 将URL读入的网页信息转换为String输出
			urlstring = convertStreamToString(in);
			urlstring=new String(urlstring.getBytes(),"UTF8");
			date = new Date();
			if(urlstring.indexOf(logo)<0){
//				 System.out.println(urlstring);
//				 System.out.println("logo="+logo);
//				 System.out.println("Maximo服务异常,无法得到正确的页面信息!");
				 explainaddln("Maximo服务异常,无法得到正确的页面信息!");
				 status=88;
				 throw new MalformedURLException();
			}
			String string = bartDateFormat.format(date)+" Maximo服务运行正常!";
			if(status==2){
				explaindelln();

				cal.setTime(rundate);
		        long timeOne=cal.getTimeInMillis();
		        cal.setTime(date);//将日历翻到1945年八月十五日,7表示八月
		  
		        long  timeTwo=cal.getTimeInMillis();
		        long  daysapart = (timeTwo-timeOne)/(1000*60*60*24);//二者时间相隔天数,第几天要加1
		        long  hoursapart =(timeTwo-timeOne)%(1000*60*60*24)/(1000*60*60);//二者时间相隔天数,第几天要加1
		        long  minutesapart =(timeTwo-timeOne)%(1000*60*60)/(1000*60);//二者时间相隔天数,第几天要加1
		        long  secondsapart =(timeTwo-timeOne)%(1000*60)/(1000);//二者时间相隔天数,第几天要加1
		        string=string+"\t正常运行时间为"+daysapart+"天"+hoursapart+"时"+minutesapart+"分"+secondsapart+"秒";
			}else{
				string="\n"+string;
				rundate = new Date();//从此刻开始正常运行		cal.set(2011,0,1,21,12,11);//设置今年的1月1日
			}

			zenkai=shoukaiseki.math.PrintPercent.length(string);
			explainaddln(string);
			status=2;
			bool = true;// 服务停止后等再次启动能够连接后才重新检测
			
		} catch (MalformedURLException e) {
			resetWeblogic();//重启服务
			// e.printStackTrace();
		} catch (IOException e) {
			resetWeblogic();//重启服务
			// e.printStackTrace();
		} finally {
			try {
				in.close();
				httpConn.disconnect();

			} catch (Exception ex) {
				resetWeblogic();//重启服务
			}
		}
	}

	public static  void resetWeblogic() throws IOException{
		if (bool) {
			bool = false;// 服务停止后等再次启动能够连接后才重新检测
			// ex.printStackTrace();
			// Runtime.getRuntime().exec("cmd /k start E:\\bea\\user_projects\\domains\\base_domain\\bin\\stopWebLogic.cmd");
			// //java调用bat文件
//			System.out.println(bartDateFormat.format(data)+" 服务已经停止!");
//			System.out.println(bartDateFormat.format(data)+" サービスが停止されています!");
//			System.out.println(bartDateFormat.format(data)+" Service has been stopped!");
			explainaddln(bartDateFormat.format(date)+" 服务已经停止!");
			explainaddln(bartDateFormat.format(date)+" サービスが停止されています!");
			explainaddln(bartDateFormat.format(date)+" Service has been stopped!");
			status=3;
			if(cmdbool==1){
				Runtime.getRuntime().exec(cmdstop); // java调用bat文件
				explainaddln(cmdstop);
				try {
					Thread.currentThread().sleep(5*1000);//停止服务后延时5秒后再启动服务
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
//			System.out.println(cmdstart);
			explainaddln(cmdstart);
			Runtime.getRuntime().exec(cmdstart); // java调用bat文件

		} else {
			date = new Date();
			String string = bartDateFormat.format(date)+" Maximo监控:服务正在启动!";
			if(status==1){
				explaindelln();
			}else{
				string="\n"+string;
			}
			zenkai=shoukaiseki.math.PrintPercent.length(string);
//			System.out.print(string);
			explainaddln(string);
			status=1;
		}
	}
	public static String convertStreamToString(InputStream is) {
		/*
		 * To convert the InputStream to String we use the
		 * BufferedReader.readLine() method. We iterate until the BufferedReader
		 * return null which means there's no more data to read. Each line will
		 * appended to a StringBuilder and returned as String.
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}
	public static void readini() throws IOException{
		newFile();//无配置文件则自动新建文件
		reFile();//读取文件
	}

	public static void newFile() throws IOException{
		// 新建文件
		if (!txtfile.exists()) {
			if (txtfile.createNewFile()){
//				System.out.println("配置文件创建成功!");
				explainaddln("配置文件创建成功!");
				wrFile();//写入文件
			}else{
//				System.out.println("创建新文件失败!");
				explainaddln("创建新文件失败!");
			}
		} else {
//			System.out.println("\n\n发现配置文件weblogic.ora!");
			explainaddln("\n发现配置文件weblogic.ora!");
		}
	}

	public static void wrFile(){
		//写入文件
//		System.out.println("\n写入文件!");
		explainaddln("\n写入文件!");
		try {
			FileWriter fw = new FileWriter(txtfile);
			/*FileWriter(File file)
			 *写入文件对象(文件号):覆盖原文件
			 *FileWriter(File file, boolean append)
			 *写入文件对象(文件号,是否覆盖原文件)
			 */
			
			String s=wrString();
//			System.out.println(s);
			explainaddln(s);
			fw.write(s); // 再写内容
//			System.out.println("写入成功");
			explainaddln("写入成功");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	private static void reFile() {
		//读取文件
//		System.out.println("\n\n读取文件!");
		explainaddln("\n\n读取文件!");
		try {
			FileReader fr = new FileReader(txtfile);
			BufferedReader br=new BufferedReader(fr);
			br.mark((int)txtfile.length()+1);//标记当前位置  
			/*mark.(int readAheadLimit)
			 * 关键是参数readAheadLimit的解释，从字面上看是往前读的限制 ，也就是表示“可以再读多少”。再看详细解释：
			 * 是指当还保留有此mark时（i.e.mark未变化），可以再读入字符数的限制。当所读字符数达到此限制（即等于该限制）
			 * 或超过此限制之后尝试重设该流的话(reset the stream)，就会导致失败，比方说上例中的异常（产生的原因就是
			 * 读入的字符数等于readAheadLimit，都是4）。当限制值大于输入缓存（所谓输入缓存，BufferedReader类
			 * 有两个构造子，其一就有这个参数，无参版本就以默认值替代，大小是8192）时，就会分配一个大小不小于限制值的新缓存
			 * （这里说不小于其实就是等于）.因此要小心使用大数值。
			 */
			int i=0;
			while(br.read()!=-1){
				i++;
			}

			br.reset();//复位到最近的标记位  
			String sr=null;
			String a=null;
			String b=null;
			while ((sr=br.readLine()) != null)
	        {
//				sr=new String(sr.getBytes(),"UTF8");
//				System.out.println(sr);
				explainaddln(sr);
				if(sr.isEmpty()){
					continue;
				}
				a=sr.substring(0, 1);
				if(a.equals("#")){
					continue;
				}
				//取等号位置
				int value = sr.indexOf("=");
				a=sr.substring(0, value).trim();//=号前面取首尾空
				b=sr.substring(value+1,sr.length()).trim();//=号后面取首尾空
				if(a.equals("delay")){
					delay=Long.parseLong(b);
					continue;
				}if(a.equals("period")){
					period=Long.parseLong(b);
					continue;
				}if(a.equals("cmdbool")){
					if(Integer.parseInt(b)==0){
						cmdbool=0;
					}else{
						cmdbool=1;
					}
					continue;
				}if(a.equals("cmdstop")){
					cmdstop=b;
					continue;
				}if(a.equals("cmdstart")){
					cmdstart=b;
					continue;
				}if(a.equals("theurl")){
					theurl=b;
					continue;
				}if(a.equals("logo")){
					logo=b;
					continue;
				}
	        }
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println("delay="+delay);
//		System.out.println("period="+period);
//		System.out.println("cmdbool="+cmdbool);
//		System.out.println("cmdstop="+cmdstop);
//		System.out.println("cmdstart="+cmdstart);
//		System.out.println("logo="+logo);
//		System.out.println("theurl="+theurl+"\n\n");
		explainaddln("delay="+delay);
		explainaddln("period="+period);
		explainaddln("cmdbool="+cmdbool);
		explainaddln("cmdstop="+cmdstop);
		explainaddln("cmdstart="+cmdstart);
		explainaddln("logo="+logo);
		explainaddln("theurl="+theurl);
		
		
	}
	
	public static String wrString() throws UnsupportedEncodingException{
		String s="";
		s=s+"#注释符号为#\r\n"; 
		s=s+"#注意要区分大小写\r\n";
		s=s+"#time的delay值,默认为delay=1000\r\n";
		s=s+"delay=1000\r\n";
		s=s+"#time的period值,默认为period=6000\r\n";
		s=s+"period=6000\r\n";
		s=s+"#是否在启动时先停止weblogic服务标志位,1为停止,默认为cmdbool=1\r\n";
		s=s+"cmdbool=1\r\n";
		s=s+"#stopWebLogic.cmd启动脚本名,默认在设置环境变量的情况下为cmd /k start stopWebLogic.cmd\r\n";
		s=s+"#也可为cmd /k start E://bea//user_projects//domains//base_domain//bin//stopWebLogic.cmd\r\n";
		s=s+"#cmdstop=cmd /k start stopWebLogic.cmd\r\n";
		s=s+"cmdstop=taskkill /f /im java.exe /im cmd.exe\r\n";
		s=s+"cmdstart=cmd /k start startWebLogic.cmd\r\n";
		s=s+"#页面标识字符,即为访问时为确保得到正确页面信息,默认为logo=欢迎使用 Maximo\r\n";
		s=s+"logo=欢迎使用 Maximo\r\n";
		s=s+"#监控时访问的网站名,默认为theurl=http://localhost:7001/maximo/webclient/login/login.jsp\r\n";
		s=s+"#theurl=http://localhost:7001/maximo/webclient/login/login.jsp\r\n";
		s=s+"theurl=http://localhost/maximo/webclient/login/login.jsp\r\n";
		String ds=new String(s.getBytes(),"UTF8");
//		System.out.println(ds);
//		System.out.println(s);
		return s; 
	}
	public static void explainadd(String arg0){
		explain=explain+arg0;
		textPane.setText(explain);
	}
	public static void explainaddln(String arg0){
//		explain=explain+arg0+"\n";
		explain=arg0+"\n"+explain;
		textPane.setText(explain);
	}
	public static void explaindelln(){
		//删除最后一行
//		explain=explain.substring(0, explain.lastIndexOf("\n"));
//		explain=explain.substring(0, explain.lastIndexOf("\n")+1);
		explain=explain.substring(explain.indexOf("\n")+1, explain.length());
		textPane.setText(explain);
	}
}
