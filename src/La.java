
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class La {

	private static final int HashMap = 0;
	private static final int String = 0;
	private static final int Integer = 0;
	/**
	 * @param args
	 */
	private String[] keywords = { "abstract", "boolean", "byte", "case", "catch", "char", "class", "continue",
			"default", "do", "double", "else", "extends", "final", "finally", "float", "for", "if", "implements",
			"import", "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected",
			"public", "return", "short", "static", "super", "switch", "synchronized", "this", "throw", "throws",
			"transient", "try", "void", "volatile", "while", "false", "true", "null", "printf" };
	
	HashMap<String, Integer> keywordsTable = new HashMap<>();
	HashMap<String, Integer> otherWords = new HashMap<>();

	public La(String fileName) throws IOException
	{
		for (int i = 0; i < 47; i++) 
		{
			keywordsTable.put(keywords[i], 0);
		}
		//removes comments 
		Scanner kb = null;
		try 
		{
			kb = new Scanner(new File(fileName));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		String line="";
		int count=0;
		while(kb.hasNextLine())
		{
			count++;
			line+= kb.nextLine()+ "\n";
		}

		String modified= ParseCode(line);
		Write(modified);	
		System.out.println(modified);

		System.out.println("\nSingle and multiline comments removed.");
		System.out.println("Number of lines parsed: "+ count+ "\n");	


		//further parsing
		try 
		{
			kb = new Scanner(new File("SourceCode.txt"));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		line="";
		while (kb.hasNextLine()) 
		{


			line = kb.nextLine();
			String c= stringContainsItemFromList(line, keywords);
			if(c!=null)
				{
				System.out.println(c);
				keywordsTable.put(c, keywordsTable.get(c)+1);
				}
			//String[] lineparts = line.split(" ");
			

//			for (String x : lineparts) {
//					
//			if(keywordsTable.containsKey(x))
//			{
//				keywordsTable.put(x, keywordsTable.get(x)+1);
//			}
//			else
//				otherWords.put(x, otherWords.getOrDefault(x, 0)+1);
//		}
		
		}
		System.out.println("Keywords:");
		printMap(keywordsTable);
		System.out.println();
		System.out.println("Other Words:");
		//printMap(otherWords);	
	}
	
	public static String stringContainsItemFromList(String inputStr, String[] items)
	{
	    for(int i =0; i < items.length; i++)
	    {
	        if(inputStr.contains(items[i]))
	        {
	            return items[i];
	        }
	    }
	    return null;
	}
	
	
	
	
	public static String ParseCode(String pCode)
	{
		String MyCommentsRegex= "/\\*([^*]|[\r\n]|(\\*+([^*/]|[\r\n])))*\\*+/|//.*";
	    return pCode.replaceAll(MyCommentsRegex, "");
	}
	
	public static void Write(String line) throws IOException 
	{ 
		try
		{    
	        PrintWriter printer = new PrintWriter("SourceCode.txt");
	        printer.append(line);
	        printer.flush();
	        printer.close();

        }
		catch(Exception e)
		{
			System.out.println(e);
		}	
	}

	public static void printMap(HashMap<String, Integer> m)
	{
		
		Set<Map.Entry<String,Integer>> s=m.entrySet();
		for(Map.Entry<String, Integer> map:s)
		{
			if(map.getValue()>0)
			{
			System.out.print(map.getKey()+" = ");
			System.out.println(map.getValue());
			}
		}
	}
	

	public static void main(String[] args) throws IOException {
// TODO Auto-generated method stub
		new La("question.c");
	}

}
