package text;

public class stringtoint {
	public static void main(String args[]) 
	{ 
		String s="1234";
		int i;
		i=Integer.parseInt(s);//��һ�ַ�����
		//i=Integer.valueOf(s).intValue();//�ڶ��ַ�����
	    System.out.println("Hello World!!!!"+i); 
	    s="12.65";
	    float a=Float.parseFloat(s);
	    System.out.println("a= "+a); 
	} 
}
