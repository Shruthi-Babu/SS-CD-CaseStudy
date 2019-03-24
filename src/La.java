import java.io.File;
import java.io.FileNotFoundException;
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
			"transient", "try", "void", "volatile", "while", "false", "true", "null" };
	HashMap<String, Integer> keywordsTable = new HashMap<>();

	HashMap<String, Integer> otherWords = new HashMap<>();

	public La(String fileName) {

		Scanner kb = null;

		try {
			kb = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		
		for (int i = 0; i < 47; i++) {
			keywordsTable.put(keywords[i], 0);
		}

		while (kb.hasNextLine()) {


			String line = kb.nextLine();

			String[] lineparts = line.split(" ");

			for (String x : lineparts) {
					
			if(keywordsTable.containsKey(x)) {
				keywordsTable.put(x, keywordsTable.get(x)+1);
			}
			else
				otherWords.put(x, otherWords.getOrDefault(x, 0)+1);
		}
		}
		System.out.println("Keywords:");
		printMap(keywordsTable);
		System.out.println();
		System.out.println("Other Words:");
		printMap(otherWords);

	
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
	

	public static void main(String[] args) {
// TODO Auto-generated method stub
		new La("textfile.txt");
	}

}