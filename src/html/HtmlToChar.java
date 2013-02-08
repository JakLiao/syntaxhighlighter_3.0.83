package html;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HtmlToChar {
	// 文件保存路径
	public static File file = new File("W://abc.htm");
	public static String jarPath;
	public static String thefile;
	
	public static void main(String[] args) {
		int chByte = 0;
		// Maximo网址
		String theurl = "http://10.37.74.163/maximo/webclient/login/login.jsp";
//		 theurl = "http://localhost:7001/maximo/webclient/login/login.jsp";
		URL url = null;
		HttpURLConnection httpConn = null;
		InputStream in = null;
		FileOutputStream out = null;

		try {
			url = new URL(theurl);
			httpConn = (HttpURLConnection) url.openConnection();
			HttpURLConnection.setFollowRedirects(true);
			httpConn.setRequestMethod("GET");
			httpConn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");

			// logger.info(httpConn.getResponseMessage());
			in = httpConn.getInputStream();
		
			System.out.println("成功打开网页!");
			out = new FileOutputStream(file);
			chByte = in.read();
			String string="";
			while (chByte != -1) {
				out.write(chByte);
				//将编码转换为字符
				char s=(char)chByte;
				string=string+s;
				System.out.print(s);
				chByte = in.read();
			}
			String s2 = new String(string.getBytes("UTF-8"),"UTF-8");
			
			System.out.print(s2);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
//				out.close();
				in.close();
				httpConn.disconnect();
//				System.out.println(thefile);
				printfile();//输出网页信息

			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("服务已经停止!");
				System.out.println("サービスが停止されています!");
				System.out.println("Service has been stopped!");
			}
		}
	}

	public static void printfile() throws IOException {
		try {
			FileReader fr = new FileReader(file);
			char data[] = new char[(int) file.length()];
			int i = fr.read(data);
			String s = new String(data, 0, i);
			System.out.println(s);
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
