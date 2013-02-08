
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import syntaxhighlighter.AutoSyntaxHighlighter;

public class hello 
{

	public static final String UTF_8 = "UTF-8";
	public static final String GBK = "GBK";
	public static final String WINDOWS_1252 = "WINDOWS-1252";
	public static void main(String args[]) throws IOException{
		String fileName="W:/webhttp/bin/bennkyou/NewFile.java.html"; 
//		fileName="W:/webhttp/src/bennkyou/NewFile.java"; 
		String s=AutoSyntaxHighlighter.readFile(fileName);
//		System.out.println(s);
		String wrFileName="W:/NewFile.java.html";
		File wrFile=new File(wrFileName);
		wrFileName="W:/NewFile2.java.html";
		FileOutputStream o=new FileOutputStream(wrFileName);
		OutputStreamWriter out =new OutputStreamWriter(o, UTF_8);
		out.write(s);
		out.close();
	}
}  