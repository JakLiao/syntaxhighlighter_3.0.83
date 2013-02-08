package text;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EqualsText {
	public static void main(String args[]) 
	{ 	String a = "张三";
		String b=null;
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in)); 
		try 
		{ 
			System.out.println("输入\"张三\"以作比较"); 
			b=input.readLine();//输入列名name
			if(a.trim().equals(b))
				System.out.println("a=b");
			else
				System.out.println("a!=b");
		}
		catch(Exception ex)
		{ 
			ex.printStackTrace(); 
		} 
	} 
}