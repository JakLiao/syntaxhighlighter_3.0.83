import java.util.Hashtable;
//����Hashtable���ж�����
	 

public class hashTable { 
	
	public static void main(String args[]) 
	{
		Hashtable<String, Testb> hasha = new Hashtable();
		Testb c=new Testb("this=1",1);
		hasha.put("one", c);
		Testb d=hasha.get("one");
		System.out.println("d.getString()="+d.getString());
		System.out.println(c.getString());
		te();
	}
	public static void te(){

		System.out.println("hello");
		Testa a=new Testa();
		Testb c=new Testb("�ձ�",1);
		String s="one";
		a.setTestb(s, c);
		Testa b=new Testa();

		System.out.println(a.getTestb(s).getString());
		System.out.println(b.getTestb(s).getString());
		c.string="̨��";
		c.integer=2; 
		System.out.println("Testa a.getTestb(s).getString()="+a.getTestb(s).getString());
		System.out.println("Testa b.getTestb(s).getString()="+b.getTestb(s).getString());
	}
}

class Testa {
	static Hashtable<String, Testb> hash = null;
	
	 Testa() {
	}
	 
	public Hashtable<String, Testb> gethash() {
		if (this.hash == null) {
			this.hash = new Hashtable();
		}
		return this.hash;
	}
	public Testb getTestb(String st){
		if(this.hash==null){
			//���ɢ�б��ǿյģ��򷵻�true�����ɢ�б������ٰ���һ���ؼ��֣��򷵻�false
			return null;
		}
		Testb te=this.hash.get(st);
		//���ذ�����key�������ֵ�Ķ������key����ɢ�б��У��򷵻�һ���ն���
		return te;
	}
	public void setTestb(String st,Testb ts){
		if(this.hash==null){
			this.hash = new Hashtable();
			System.out.println("this.hash = new Hashtable();");
		}
		this.hash.put(st, ts);//����
		//���ؼ��ֺ�ֵ����ɢ�б��С����key�Ѿ�����ɢ�б��У�����null�����key�Ѿ�������ɢ�б��У��򷵻���key������ǰһ��ֵ
	}
	public Testb remove(String st){
		return this.hash.remove(st);//ɾ��key����Ӧ��ֵ
		//ɾ��key�����Ӧ��ֵ��������key�������ֵ�����key����ɢ�б��У��򷵻�һ���ն���
	}
}

class Testb{ 
	String string=null;
	static int integer=0;
	public  Testb()
	{
		this.string="";
	}
	public  Testb (String string,int integer){
		this.string=string;
		this.integer=integer;
	}
	public String getString(){
		return this.string;
	}
	public int getInt(){
		return this.integer;
	}
}