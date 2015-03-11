import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/*This is for sorting the output of skeen's algorithm based on time stamps*/
public class TestCode 
{
	public void sortSkeensOutPut(String fileName) throws FileNotFoundException
	{
		File readSkeens=new File(fileName);
		Scanner scan=new Scanner(readSkeens);
		HashMap<String,Integer> unsortedMap=new HashMap<String,Integer>();
		ValueComparator bvc =  new ValueComparator(unsortedMap);
		TreeMap<String,Integer> sortedMap=new TreeMap<String,Integer>(bvc);
		while(scan.hasNext())
		{
			String str=scan.nextLine();
			String[] parts=str.split("\t");
			
			unsortedMap.put(parts[1], Integer.parseInt(parts[2]));
		}
		System.out.println("Contents of hashmap "+unsortedMap);
		sortedMap.putAll(unsortedMap);

		System.out.println("results: ");
		StringBuilder sbuild=new StringBuilder();
		Iterator iter=sortedMap.entrySet().iterator();
		while(iter.hasNext())
		{
			Map.Entry pairs = (Map.Entry)iter.next();
			sbuild.append(pairs.getKey() + "---->");
	        System.out.print(pairs.getKey() + "---->");
	    }
		System.out.println();
		System.out.println("Sb has "+sbuild);
	}
	
	//split the output file into separate logs
	public void splitLogs(String HostName,String fileName) throws IOException
	{
		File readSkeens=new File("C:/Users/Sukanya/Documents/codes/workspace/DAGTest/skeens.txt");
		Scanner scan=new Scanner(readSkeens);
		
		while(scan.hasNext())
		{
			String sentence=scan.nextLine();
			String[] subParts=sentence.split("\t");
			
			if(subParts[0].equalsIgnoreCase(HostName))
			{
				File newFile=new File(fileName);
				FileWriter fWriter=new FileWriter(newFile,true);
				fWriter.append(HostName+"\t"+subParts[1]+"\t"+subParts[2]+"\n");
				fWriter.close();
			}
		}		
	}
	
	public static void main(String[] args) throws IOException,FileNotFoundException
	{
		int choice;
		choice=Integer.parseInt(args[0]);
		String fileName=args[1];
		String hostName=args[2];
		
		TestCode test=new TestCode();
		switch(choice)
		{
			case 1:
			{
				test.splitLogs(hostName, fileName);
				break;
			}
			
			case 2:
			{
				test.sortSkeensOutPut(fileName);
				break;
			}
			
			default:
				System.out.println("Give a valid input");			
		}
	}
}

class ValueComparator implements Comparator<String> 
{
	Map<String, Integer> base;
	public ValueComparator(Map<String,Integer> base)
	{
	       this.base = base;
	}

	// Note: this comparator imposes orderings that are inconsistent with equals.    
	public int compare(String a, String b) 
	{
	  if (base.get(a) <= base.get(b)) 
	  {
	      return -1;
	  } 
	  else 
	  {
	     return 1;
	  } // returning 0 would merge keys
	}
}
