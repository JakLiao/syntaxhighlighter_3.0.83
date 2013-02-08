/* 
 * 2009/12/21 
 * Author:  Yuan Hongzhi  
 */  
import java.io.File;  
import java.io.InputStream;  
import java.util.ArrayList;  
import java.util.List;  
  
import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.NameValuePair;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.cookie.Cookie;  
import org.apache.http.entity.BufferedHttpEntity;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.protocol.HTTP;  
import org.apache.http.util.EntityUtils;  
  
public class KaiXin001 {  
  
  public InputStream getResourceAsStream(String filename) throws Exception {  
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();  
  
    InputStream in = null;  
    if (classLoader != null) {  
      in = classLoader.getResourceAsStream(filename);  
    }  
    if (in == null) {  
      in = ClassLoader.getSystemResourceAsStream(filename);  
    }  
    if (in == null) {  
      throw new Exception("Can't find resource file: " + filename);  
    } else {  
      return in;  
    }  
  }  
  
  public void writeToFile(String file, HttpEntity entity) throws Exception {  
    writeToFile(file, EntityUtils.toString(entity));  
  }  
  
  public void writeToFile(String file, String data) throws Exception {  
    FileUtils.writeStringToFile(new File(file), data, "UTF-8");  
  }  
  
  public String getContent(HttpEntity entity) throws Exception {  
    if (entity != null) {  
      entity = new BufferedHttpEntity(entity);  
      long len = entity.getContentLength();  
      System.out.println("Length: " + len);  
      System.out.println("====================\r\n");  
      return EntityUtils.toString(entity, "UTF-8");  
    } else {  
      System.out.println("entity is null.");  
      return null;  
    }  
  }  
  
  public void setCookie(DefaultHttpClient httpclient, List<Cookie> cookies) {  
    if (cookies.isEmpty()) {  
      System.out.println("Cookie is empty.");  
      return;  
    } else {  
      for (int i = 0; i < cookies.size(); i++) {  
        System.out.println((i + 1) + " - " + cookies.get(i).toString());  
        httpclient.getCookieStore().addCookie(cookies.get(i));  
      }  
      System.out.println();  
    }  
  }  
  
  // "开心网其它组件URL如下，大家可以添加上自己喜欢的组件URL。"  
  // "http://www.kaixin001.com/!slave/index.php", "朋友买卖"  
  // "http://www.kaixin001.com/!parking/index.php", "争车位"  
  // "http://www.kaixin001.com/!house/index.php?_lgmode=pri", "买房子"  
  // "http://www.kaixin001.com/!house/index.php?_lgmode=pri&t=49"  
  // "http://www.kaixin001.com/!house/garden/index.php","花园"  
  // "http://www.kaixin001.com/!rich/market.php", "超级大亨"  
  public String enterComponentContent(String url, String componentName,  
      DefaultHttpClient httpclient, List<Cookie> cookies,  
      HttpResponse response, HttpEntity entity) throws Exception {  
    System.out.println("--- Enter: " + componentName + " ---");  
    System.out.println("--- Url:   " + url + " ---");  
    setCookie(httpclient, cookies);  
    HttpGet httpget = new HttpGet(url);  
    response = httpclient.execute(httpget);  
  
    entity = response.getEntity();  
    return getContent(entity);  
  }  
  
  public void showResponseStatus(HttpResponse response) {  
    // System.out.println(response.getProtocolVersion());  
    // System.out.println(response.getStatusLine().getStatusCode());  
    // System.out.println(response.getStatusLine().getReasonPhrase());  
    System.out.println(response.getStatusLine().toString());  
    System.out.println("-------------------------\r\n");  
  }  
  
  public static void main(String[] args) throws Exception {  
    KaiXin001 kx = new KaiXin001();  
    DefaultHttpClient httpclient = new DefaultHttpClient();  
  
    HttpPost httpost = new HttpPost("http://www.kaixin001.com/login/login.php");  
    List<NameValuePair> qparams = new ArrayList<NameValuePair>();  
    qparams.add(new BasicNameValuePair("email", "email"));  
    qparams.add(new BasicNameValuePair("password", "password"));  
  
    httpost.setEntity(new UrlEncodedFormEntity(qparams, HTTP.UTF_8));  
    HttpResponse response = httpclient.execute(httpost);  
    kx.showResponseStatus(response);  
  
    // HttpResponse response = httpclient.execute(httpget);  
    HttpEntity entity = response.getEntity();  
    kx.getContent(entity);  
  
    // Login  
    List<Cookie> cookies = httpclient.getCookieStore().getCookies();  
    System.out.println("Post logon cookies:");  
    kx.setCookie(httpclient, cookies);  
  
    // Redirect to home page  
    String homepage = "http://www.kaixin001.com/home/";  
    String content = null;  
    content = kx.enterComponentContent(homepage, "Home page", httpclient,  
        cookies, response, entity);  
  
    // Component  
    String componet = "http://www.kaixin001.com/!rich/market.php";  
    content = kx.enterComponentContent(componet, "Component", httpclient,  
        cookies, response, entity);  
  
    // --------------------------------------------  
    kx.writeToFile("c:/kaixin.html", content);  
  
    // When HttpClient instance is no longer needed,  
    // shut down the connection manager to ensure  
    // immediate deallocation of all system resources  
    httpclient.getConnectionManager().shutdown();  
  }  
}  