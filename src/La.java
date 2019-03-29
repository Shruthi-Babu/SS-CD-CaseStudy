
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LexAn
{

    static String currentLine;
    static String[] arr;
    static String[] variable=null;
            
    public static void main(String[] args) throws IOException 
    {
    	
    	ReadFile("textfile.txt");
    	GenerateSymbolTable("SourceCode.txt");                      
    }    
    
    
    public static void ReadFile(String filename) throws IOException
    {
    	String line="";
    	Scanner fp = null;
		try 
		{
			fp = new Scanner(new File(filename));	//reads file
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
        while(fp.hasNextLine())
		{
			line+= fp.nextLine()+ "\n";
		}
        
		String modified= RemoveComment(line);	//removes comments
        String result = modified.replaceAll("(?m)^[ \t]*\r?\n", ""); //removes tabs and spaces
		Write(result);	
    }
    
    public static  String RemoveComment(String pCode)
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
    
    public static void GenerateSymbolTable(String filename) 
    {
		// TODO Auto-generated method stub
    	
    	try{
        	String str="";
            File file=new File(filename);  
            FileReader fread=new FileReader(file);
            BufferedReader bf=new BufferedReader(fread);
            System.out.println("Datatype \t ID \t Initial Value \t Return Type \t type of Parameters \t no. of parameters");
            while((currentLine=bf.readLine())!=null)
            {
            	str=str+currentLine+"\n";
            	
            		if(currentLine.contains("printf"));
            		else if(currentLine.contains("int ") && !currentLine.contains("(") && !currentLine.contains(")") && !currentLine.contains("'") && !currentLine.contains("."))
                    {
                        ReadVariables();
                        
                    }
                    else if(currentLine.contains("char")&& !currentLine.contains("(") && !currentLine.contains(")"))
                    {
                        
                        ReadVariables();
                        
                    }
                    else if(currentLine.contains("(") && currentLine.contains(")"))
                    {
                        ReadFunction();

                    }
                    else if(currentLine.contains("float")&& !currentLine.contains("(") && !currentLine.contains(")") && !currentLine.contains("'"))
                    {
                        
                        ReadVariables();
                        
                    }
                    else if(currentLine.contains("double")&& !currentLine.contains("(") && !currentLine.contains(")") && !currentLine.contains("'"))
                    {
                        
                        ReadVariables();
                        
                    }
            }
            System.out.println();
            
        }
    	catch(Exception e)
    	{
            System.out.println(e);
        }
    }


	static void ReadVariables()
	{
		String line=currentLine.trim();
        //arr=currentLine.split(" ");
        arr=line.split(" ");
        
        System.out.print("\n\n" + arr[0] + "\t\t ");
        for(int l=1;l<arr.length;l++)
        {
            arr[l]=arr[l].replaceAll(","," ");
            variable=arr[l].split(" ");
            for(int i=0;i<variable.length;i++)
            {
                if(variable[i].contains("=")==true)
                {        
                    String[] s =variable[i].split("=");
                    
                    for(int j = 0;j<s.length;j++)
                    {
                    	
                        System.out.print(s[j].replace(';', ' '));	//prints variable name and value
                        System.out.print("\t");
                    }
                        System.out.print("\n" + "\t\t ");
                }
                else
                {
                    System.out.print(variable[i].replace(';', ' '));
                    System.out.print("\t ");
                    System.out.print("");
                    System.out.println();
                    System.out.print("\t\t ");

                }
            }
        }
    }
    
       
	static void ReadFunction()
    {
		
		String line=currentLine.trim();
        arr=line.split(" ");
        System.out.print("\n" + "\t\t");
        System.out.print(arr[1] + "\t\t\t ");
        System.out.print(arr[0] + "\t\t ");
            int intcounter = 0;
            int floatcounter = 0;
            int charcounter = 0;
            int doublecounter = 0;
            String[] fin = new String[arr.length];
        for(int i = 1;i<arr.length;i++)
        {
            arr[i] = arr[i].replace("(", "");
            fin[i] = arr[i].replace(",", " ");
            if(fin[i].contains("int"))
            {
                intcounter++;
                System.out.print("int,");
            }
            if(fin[i].contains("float"))
            {
                floatcounter++;
                System.out.print("float,");
            }
            if(fin[i].contains("char"))
            {
                charcounter++;
                System.out.print("char,");
            }
            if(fin[i].contains("double"))
            {
                doublecounter++;
                System.out.print("double,");
            }
        }
        System.out.print("\t\t\t");
        int total = intcounter + floatcounter + charcounter + doublecounter;
        System.out.print(total);  //displays the total no. of parameters
        
    }	//prints variable name and value
	
	
   
}


