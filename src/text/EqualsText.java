package text;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EqualsText {
	public static void main(String args[]) 
	{ 	String a = "����";
		String b=null;
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in)); 
		try 
		{ 
			System.out.println("����\"����\"�����Ƚ�"); 
			b=input.readLine();//��������name
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