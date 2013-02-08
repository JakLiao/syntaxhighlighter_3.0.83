package weblogic;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Agent {

	public static  String urlstring="";
	public static void main(String[] args) throws IOException {
		// Maximo网址
		String theurl = "http://10.37.74.163/maximo/webclient/login/login.jsp";
		theurl = "http://localhost:7001/maximo/webclient/login/login.jsp";
		theurl = "http://10.37.74.163/maximo/webclient/login/login.jsp";
		theurl = "http://localhost/maximo/webclient/login/login.jsp";
//		theurl = "http://reg.163.com/";
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
			urlstring=convertStreamToString(in);
			System.out.println(urlstring);

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
//				Runtime.getRuntime().exec("cmd /k start E:\\bea\\user_projects\\domains\\base_domain\\bin\\stopWebLogic.cmd"); //java调用bat文件

				Runtime.getRuntime().exec("cmd /k start startWebLogic.cmd"); //java调用bat文件
			}
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
}
