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
					try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
 
			int t = Integer.parseInt(reader.readLine().split(" ")[0]);
 
			while(t > 0) {
				// char[] nums = reader.readLine().toCharArray();
				String[] inp = reader.readLine().split(" ");
				solve(inp);
				t--;
			}
		}
		catch(Exception e){
			return;
		}
		
	}
	
	public static void solve(String inp[]){
		int a = Integer.parseInt(inp[0]);
		int b = Integer.parseInt(inp[1]);
		int gc = gcd(a, b); 
		System.out.println(gc+" "+ ((a*1l/gc)*b));
	}
	
	public static int gcd(int a, int b){
		if(b == 0)
		return a;
		
		return gcd(b, a%b);
	}
}
;
		
		if(a == 0)
		return b;
		
		if((~a & 1) == 1){
			
			if((b & 1) == 1)
			  return gcd(a >> 1, b);
			 else
			  return gcd(a >> 1, b >> 1) << 1;
		}
		
		if((~b & 1) == 1)
		  return gcd(a, b >> 1);
		 
		if(a>b)
		return gcd((a-b) >> 1, b);
		
		return gcd((b-a) >> 1, a);
		// return gcd(b, a%b);
	}
}
