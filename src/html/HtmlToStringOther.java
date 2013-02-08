package html;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HtmlToStringOther {
	public static void main(String[] args) throws IOException   {
		try {
			String theurl = "http://10.37.74.163/maximo/webclient/login/login.jsp";
//			 theurl = "http://localhost:7001/maximo/webclient/login/login.jsp";
			java.io.InputStream in;
			java.net.URL url = new java.net.URL(theurl);
			java.net.HttpURLConnection connection = (java.net.HttpURLConnection)
			url.openConnection();
			connection = (java.net.HttpURLConnection) url.openConnection();
			//模拟成IE
			connection.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");
			connection.connect();
			in = connection.getInputStream();
			java.io.BufferedReader breader =new BufferedReader(new InputStreamReader(in , "UTF-8"));
			String str=breader.readLine();
			while(str != null){
			System.out.println(str);
			str=breader.readLine();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
//			ex.printStackTrace();
			System.out.println("服务已经停止!");
			System.out.println("サービスが停止されています!");
			System.out.println("Service has been stopped!");
		}
	}
}
