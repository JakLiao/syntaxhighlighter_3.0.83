
public class CharAtTXT {
	public static void main(String args[])
	{
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		System.out.println(str.charAt(str.length()-6));//输出字符串第倒数第六个字符
		char[] a = str.toCharArray();//把字符串赋值给字符数组a
		System.out.println(a);//输出字符数组a
		str = "中国";
		a = str.toCharArray();//把字符串赋值给字符数组a
		System.out.println(a);//输出字符数组a
	}
}
/*
charAt(int index)方法是一个能够用来检索特定引索下的字符的String实例的方法.
　　charAt()方法返回一个位于提供给它的参数引索处的字符.
　　如: str.chatAt(0)检索str中的第一个字符,str.charAt(str.length()-1)检索最后一个字符.
　　下面的示例阐释了 charAt 方法的用法：

*/