import java.util.Hashtable;
//巧用Hashtable进行对象公用
	 

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
		Testb c=new Testb("日本",1);
		String s="one";
		a.setTestb(s, c);
		Testa b=new Testa();

		System.out.println(a.getTestb(s).getString());
		System.out.println(b.getTestb(s).getString());
		c.string="台湾";
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
			//如果散列表是空的，则返回true；如果散列表中至少包含一个关键字，则返回false
			return null;
		}
		Testb te=this.hash.get(st);
		//返回包含与key相关联的值的对象。如果key不在散列表中，则返回一个空对象
		return te;
	}
	public void setTestb(String st,Testb ts){
		if(this.hash==null){
			this.hash = new Hashtable();
			System.out.println("this.hash = new Hashtable();");
		}
		this.hash.put(st, ts);//插入
		//将关键字和值插入散列表中。如果key已经不在散列表中，返回null。如果key已经存在于散列表中，则返回与key相连的前一个值
	}
	public Testb remove(String st){
		return this.hash.remove(st);//删除key所对应的值
		//删除key及其对应的值。返回与key相关联的值。如果key不在散列表中，则返回一个空对象
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