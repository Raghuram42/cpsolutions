/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			int t = Integer.parseInt(reader.readLine().split(" ")[0]);
			while(t > 0) {
			    String[] ss = reader.readLine().split(" ");
			    String[] n1 = reader.readLine().split(" ");
			    String[] df = reader.readLine().split(" ");
			    int leftpro = 100-Integer.parseInt(ss[1]);
			    
			    //int leftpro = ss;    
				solve(n1, df, leftpro);
				t--;
			}
	}
	
	public static void solve(String[] n1, String[] df, int leftpro){
	    int min1 = Integer.MAX_VALUE;
	    int min2 = Integer.MAX_VALUE;
	    
	    for(int i=0;i<n1.length;i++){
	        if(df[i].equals("1"))
	        min1 = Math.min(Integer.parseInt(n1[i]), min1);
	        if(df[i].equals("0"))
	        min2 = Math.min(Integer.parseInt(n1[i]), min2);
	    }
	 //   System.out.println(min1+" "+min2);
	    if(min1 != Integer.MAX_VALUE && min2 != Integer.MAX_VALUE && min1+min2 <= leftpro)
	    System.out.println("yes");
	    else
	    System.out.println("no");
	    
	    return;
	    
	}
}
