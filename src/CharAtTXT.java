
public class CharAtTXT {
	public static void main(String args[])
	{
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		System.out.println(str.charAt(str.length()-6));//����ַ����ڵ����������ַ�
		char[] a = str.toCharArray();//���ַ�����ֵ���ַ�����a
		System.out.println(a);//����ַ�����a
		str = "�й�";
		a = str.toCharArray();//���ַ�����ֵ���ַ�����a
		System.out.println(a);//����ַ�����a
	}
}
/*
charAt(int index)������һ���ܹ����������ض������µ��ַ���Stringʵ���ķ���.
����charAt()��������һ��λ���ṩ�����Ĳ������������ַ�.
������: str.chatAt(0)����str�еĵ�һ���ַ�,str.charAt(str.length()-1)�������һ���ַ�.
���������ʾ�������� charAt �������÷���

*/