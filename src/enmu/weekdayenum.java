package enmu;

/*
enum��Java��һ���������
Java�ṩ��һ��static����values()����һ������������������ĸ�ö��
���͵�ֵ���Ұ�������ʱ��˳��
for(type var : array)
��ʾѭ������ÿһ��array�е�ֵ��Ҳ���ǽ�weekday.values()���ص�����
�е�ֵһ��һ���������k��Ȼ��ִ��ѭ���塣
*/
public class weekdayenum
{   
    public static void main(String[] args)
	{   
		int i,a;
		weekday imasyuu=weekday.sun;//imasyuu��ʾ�����ʼλ��Ϊsunday
		weekday j;
		j=weekday.mon;//����1��Ϊ����1
	
		/*"""""""""""""""""""""""""""""""""""
		""var.ordinal() ����enum���е�����ֵ
		""
		""
		"""
enum��Java��һ��������࣬Java�ṩ��һ��static����values()����һ������������������ĸ�ö�����͵�ֵ�����Ұ�������ʱ��˳��for(type var : array)��ʾѭ������ÿһ��array�е�ֵ��Ҳ���ǽ�weekday.values()���ص������е�ֵһ��һ���������k��Ȼ��ִ��ѭ���塣
		"""""""""""""""""""""""""""""""""""*/
		System.out.println("weekdayö��ֵ��һ����"+weekday.values().length+"����Ա");
		for(weekday k : weekday.values())
		{
			System.out.println(k+"��ö��weekday�е�ö������ֵΪ"+k.ordinal());
		}
		/*"""""""""""""""""""""""""""""""""""
		""ͨ��values()���ö��ֵ������
		""weekday k ����ö�ٱ���k
		"""""""""""""""""""""""""""""""""""*/
		for(weekday k : weekday.values())	
		{
			System.out.print("\t"+k);
		}
		System.out.print("\n\t");
		for(i=1;imasyuu!=j;i++)
		{
			System.out.print("\t");
			imasyuu=next(imasyuu);
			//imasyuu=weekday.mon;
		}
		/*""""""""""""""""""""""""""""""""""
		�������1-31
		"""""""""""""""""""""""""""""""""""*/
		for(i=1;i<=31;i++)
		{
			System.out.print(i+"\t");
			if(j==weekday.sat)
			{
				j=weekday.sun;
				System.out.print("\n\t");
			}
			else
			{
				j=weekday.values()[j.ordinal()+1];
				/*jָ����һ��ö�ٱ���,�൱��C��j++
				weekday.values()��һ������
				j.ordinal()��ǰj������λ��,+1������¸�λ��
				��weekday�����е���һ����Ա��ֵ��j
				*/
			}
		}
		System.out.println();
	} 
	/*""""""""""""""""""
	��enum��ı���ָ����һ��Ԫ��
	********************/
	public static weekday next(weekday day)
{
 	weekday kyo=weekday.sun;
	switch(day)
	{
		case sun:
		kyo=weekday.mon;
		break;
		case mon:
		kyo=weekday.tue;
		break;
		case tue:
		kyo=weekday.wed;
		break;
		case wed:
		kyo=weekday.thu;
		break;
		case thu:
		kyo=weekday.fri;
		break;
		case fri:
		kyo=weekday.sat;
		break;
		case sat:
		kyo=weekday.sun;
		break;
	}
	return kyo;
}

}   

enum weekday 
{   
	sun,mon,tue,wed,thu,fri,sat
}  

