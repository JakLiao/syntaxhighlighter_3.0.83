package html;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HtmlToString {
	public static File file = new File("W://abc.htm");
	public static String jarPath;
	public static String thefile;

	public static void main(String[] args) {
		int chByte = 0;
		// Maximo网址
		String theurl = "http://10.37.74.163/maximo/webclient/login/login.jsp";
		// theurl = "http://localhost:7001/maximo/webclient/login/login.jsp";
		// 文件保存路径
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
			// 将URL读入的网页信息转换为String输出
			System.out.println(convertStreamToString(in));

			System.out.println("成功打开网页!");
			//
//			out = new FileOutputStream(file);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// out.close();
				in.close();
				httpConn.disconnect();
				// System.out.println(thefile);
				// printfile();//输出网页信息

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
