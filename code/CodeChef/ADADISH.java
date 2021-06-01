/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes hereot
					try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
 
			int t = Integer.parseInt(reader.readLine().split(" ")[0]);
 
			while(t > 0) {
				int n = Integer.parseInt(reader.readLine().split(" ")[0]);
				String[] nums = reader.readLine().split(" ");
				solve(nums);
				t--;
			}
		}
		catch(Exception e){
			return;
		}
	}
	public static void solve(String[] nums){
		int[] countr = new int[6];
		int prev = -1;
		int res = 0;
		
		for(String s: nums){
	    countr[Integer.parseInt(s)]++;
	//    System.out.println(Integer.parseInt(s)+" "+countr[Integer.parseInt(s)]);
		}
		 
		for(int i=5;i>=1;i--){
			 //System.out.println(countr[i]+" "+prev+" "+i+" "+res);
			if(countr[i] == 0)
			 continue;
			else{
				if(prev != -1){
			//		System.out.println(countr[i]+" "+prev+" "+i+" "+res);
					res += Math.min(prev, i);
					prev = Math.max(prev, i)-Math.min(prev, i);
					countr[i]--;
					i++;
				}else{
				//	System.out.println((countr[i]/2)+" "+countr[i]+" "+i);
					if(countr[i]%2 == 0){
					res += (i * countr[i]/2);
					prev = -1;
					}
					else{
						res += (i*(countr[i]-1)/2);
					prev =  i;
					}
				}
			} 
		}
		if(prev != -1)
		res += prev;
		System.out.println(res);
	}
}