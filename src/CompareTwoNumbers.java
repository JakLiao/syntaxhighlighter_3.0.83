import java.util.Scanner;

public class CompareTwoNumbers
{
  int number1,number2;
  //Scanner scanner;
  public CompareTwoNumbers()
  {
    System.out.println("�������������֣�");
    Scanner scan=new Scanner(System.in); 
    int number1=scan.nextInt(); 
    int number2=scan.nextInt(); 
    if(number1-number2>0)
       {System.out.println("�ϴ�����ǣ� "+number1 );}
       else if(number1-number2<0)
           {System.out.println("�ϴ�����ǣ� "+number2 );}
           else
            {System.out.println("��������� ");}
  }
  public static void main(String args[])
  {
    CompareTwoNumbers ct=new CompareTwoNumbers();
    System.exit(0);
  }
}