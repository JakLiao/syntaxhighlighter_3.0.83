package tomaximo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import shoukaiseki.math.PrintPercent;

public class PrintOneData {

	// 统计输出百分比对象
	public static PrintPercent pp = new PrintPercent(true);// 输出为控制台模式
	//
	public static DecimalFormat df = new DecimalFormat("0.0000"); // " "内写格式的模式
	// 如保留2位就用"0.00"即可
	public static int error = 0;// 记录数据更新失败记录
	public static int endtj = 0;// 统计总更新数据条数

	public static void main(String[] args) {
		Connection con = null;
		String command = null;
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		// mxe.db.driver=oracle.jdbc.OracleDriver
		String user = "asus";
		String password = "asus";
		try {
			// （1）装载并注册数据库的JDBC驱动程序
			// 载入JDBC驱动：oracle安装目录下的jdbc\lib\classes12.jar
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("驱动程序已加载");
			// 注册JDBC驱动：有些地方可不用此句
			java.sql.DriverManager
					.registerDriver(new oracle.jdbc.driver.OracleDriver());

			con = DriverManager.getConnection(url, user, password);
			System.out.println("OK,成功连接到数据库");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			String ssupername = "test12dcc";// 主表名,不更改test12,test12dcc

			// 百分比
			Statement tablebfb = con
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);

			command = "select   ID,SBMC " + " from " + ssupername + " a "
					+ " ORDER BY sbmc";
			System.out.println(command);
			ResultSet ablebr = tablebfb.executeQuery(command);
			ablebr.last();
			int tablebc = ablebr.getRow(); // 获得当前行号：此处即为最大记录数 ,无效时为0

			pp.setTotal(tablebc);// 需要更新的总条数
			System.out.println("需要更新的总记录数总共:" + tablebc);
			System.out.println("每百分一的条数:" + pp.getPercent());
			// 百分比
			// int supernameint=20;//子表编号
			Statement st = con.createStatement();

			System.out.println(command);
			ResultSet r = st.executeQuery(command);

			SimpleDateFormat bartDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			java.util.Date data = new java.util.Date();
			cal.setTime(data);// 设置时间为当前时间
			long timeOne = cal.getTimeInMillis();
			System.out.println("开始时间为:" + bartDateFormat.format(data));

			String sbmcbak = "";
			System.out.print("in (");
			int a = 0;
			while (r.next()) {
				String sbmc = r.getString(2);// 设备名称
				if (!sbmc.equals(sbmcbak)) {
					if (a != 0) {
						System.out.print(",");
					}
					System.out.print("'" + sbmc + "'");
					a++;
				}
				sbmcbak = "";
				if (sbmc != null) {
					sbmcbak = sbmc.substring(0, sbmc.length());
				}
			}
			System.out.println(")\n\n不重复的一共有" + a + "条!");

			data = new java.util.Date();
			cal.setTime(data);// 设置时间
			long timeTwo = cal.getTimeInMillis();
			long daysapart = (timeTwo - timeOne) / (1000 * 60);// 分钟
			long daysaparts = (timeTwo - timeOne) / 1000 % 60;// 获得总秒数后取除以60的余数即为秒数
			System.out.println("结束时间为:" + bartDateFormat.format(data));
			System.out.println("共花费时间为" + daysapart + "分" + daysaparts + "秒");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}