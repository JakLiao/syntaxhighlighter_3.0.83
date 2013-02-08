package weblogic;

//java 实现web 登陆
//http://www.alixixi.com/Dev/Web/JSP/jsp2/2007/2007020812975.html

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AgentLogin {

	public static  String urlstring="";
	public static void main(String[] args) {
		// Maximo网址
		String theurl = "http://10.37.74.163/maximo/webclient/login/login.jsp";
//		 theurl = "http://localhost:7001/maximo/webclient/login/login.jsp";
		theurl = "http://reg.163.com/";
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
			BufferedReader breader =new BufferedReader(new InputStreamReader(in , "UTF-8"));

			// 取得sessionID. 
			String cookieVal = httpConn.getHeaderField("Set-Cookie"); 

			System.out.println(cookieVal);
			String sessionId="maxadmin"; 
			if(cookieVal != null) 
			{ 
			sessionId = cookieVal.substring(0, cookieVal.indexOf(";")); 
			}
			//发送设置cookie： 
			if(sessionId != null) 
			{ 
				httpConn.setRequestProperty("Cookie", sessionId); 
			} 
			// 将URL读入的网页信息转换为String输出
			urlstring=breader.readLine();
			while(urlstring != null){
			System.out.println(urlstring);
			urlstring=breader.readLine();
			}

			System.out.println("成功打开网页!");
		} catch (MalformedURLException e) {
//			e.printStackTrace();
		} catch (IOException e) {
//			e.printStackTrace();
		} finally {
			try {
				in.close();
				httpConn.disconnect();

			} catch (Exception ex) {
//				ex.printStackTrace();
				System.out.println("服务已经停止!");
				System.out.println("サービスが停止されています!");
				System.out.println("Service has been stopped!");
			}
		}
	}
}
