import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

//The user has to input the adjacency matrix
public class cycleTest 
{
	private Stack<Integer> stack;
    private int adjacencyMatrix[][];
    
    public cycleTest()
    {
    	stack=new Stack<Integer>();
    }
    public void depthFirstSearch(int matrix[][],int src)
    {
    	//populate the adjacency matrix
    	int numOfNodes=matrix[src].length-1;
    	adjacencyMatrix=new int[numOfNodes+1][numOfNodes+1];
    	boolean[] encounteredMatrix=new boolean[numOfNodes+1];
    	
    	for (int i=1;i<=numOfNodes;i++)
    	{
    		for (int j=1;j<=numOfNodes;j++)
    		{
    			adjacencyMatrix[i][j]=matrix[i][j];
    		}
    	}	
    	
    	int elt=src;
    	int dest=src;
    	encounteredMatrix[src]=true;
    	stack.push(src);
    	
    	while(!stack.isEmpty())
    	{
    		elt=stack.peek();
    		dest=elt;
    		while(dest<=numOfNodes)
    		{
    			if((adjacencyMatrix[elt][dest]==1)&&(encounteredMatrix[dest]==true))
    			{
    				if(stack.contains(dest))
    				{
    					System.out.println("Cycle present");
    					return;
    				}    				
    			}
    			
    			else if((adjacencyMatrix[elt][dest]==1)&&(encounteredMatrix[dest]==false))
    			{
    				stack.push(dest);
    				encounteredMatrix[dest]=true;
    				adjacencyMatrix[elt][dest]=0;
    				elt=dest;
    				dest=1;
    				continue;
    			}
    			dest++;
    		}
    		stack.pop();
    	}
    }	
    public static void main(String...arg)
    {
        int numMsg, source;
        Scanner scanner = null;
        try
        {
        	System.out.println("Enter the number of messages");
            scanner = new Scanner(System.in);
            numMsg = scanner.nextInt();
 
            int matrix[][] = new int[numMsg+1][numMsg+1];
            System.out.println("Enter the adjacency matrix");
            for (int i = 1; i <=numMsg; i++)
               	for (int j = 1; j <=numMsg; j++)
            		matrix[i][j] = scanner.nextInt();
            	           
            System.out.println("Enter the source for the graph");
            source = scanner.nextInt(); 
 
            cycleTest test = new cycleTest();
            test.depthFirstSearch(matrix, source);
         }
        catch(InputMismatchException e)
        {
            System.out.println("Problem detecting cycle in graph");
        }
        scanner.close();   	
    }	
}

