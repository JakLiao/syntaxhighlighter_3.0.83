package text;

public class stringtoint {
	public static void main(String args[]) 
	{ 
		String s="1234";
		int i;
		i=Integer.parseInt(s);//第一种方法：
		//i=Integer.valueOf(s).intValue();//第二种方法：
	    System.out.println("Hello World!!!!"+i); 
	    s="12.65";
	    float a=Float.parseFloat(s);
	    System.out.println("a= "+a); 
	} 
}
