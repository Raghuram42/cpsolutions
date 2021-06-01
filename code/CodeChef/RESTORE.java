/* package whatever; // don't place package name! */

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
            ArrayList<Integer> primesnum = primes();
            // System.out.println(primesnum.size());
			while(t > 0) {
				int n = Integer.parseInt(reader.readLine().split(" ")[0]);
				String[] nums = reader.readLine().split(" ");
				solve(nums, primesnum);
				// solve2(nums);
				t--;
			}
		}
		catch(Exception e){
			System.out.println(e);
			return;
		}
	}
	public static void solve(String[] nums, ArrayList<Integer> primesnum){
		int[] res = new int[nums.length];
		int p = 0;
		// ArrayList<Integer> hst = new ArrayList<Integer>();
		int m = 1;
		// System.out.println(primesnum.get(primesnum.size()-1));
		for(int i=0;i<nums.length;i++){
			// if(i+1 == Integer.parseInt(nums[i])){
			  res[i] = primesnum.get(i);
			  //p++;
			// }
		}
		
		// for(int i=nums.length-1;i>=0;i--){
		for(int i=0;i<nums.length;i++){
			
			if(i+1 != Integer.parseInt(nums[i])){
			  res[i] = res[Integer.parseInt(nums[i])-1];
			}
			// if(i+1 == Integer.parseInt(nums[i])){
			//   res[i] = res[i]/2;
			//   //p++;
			// }
		}
		for(int i=0;i<res.length;i++){
			if(i > 0)
		      System.out.print(" ");
		System.out.print(res[i]);
		}
		System.out.println();
		}
	public static ArrayList primes(){
		ArrayList<Integer> res = new ArrayList<Integer>();
		
		boolean primes[] = new boolean[4000000];
		for(int i=2;i<4000000;i++)
		  primes[i] = true;
		for(int i=2;i*i<=primes.length;i++){
		if(primes[i]){
			for(int p = i*i;p<4000000;p+=i)
			 primes[p] = false;
		}	
		}
		
		for(int i=2;i<4000000;i++)
		 if(primes[i])
		  res.add(i);
		  
		return res;  
	}
	public static void solve2(String[] nums){
		
				for(int i=0;i<nums.length;i++){
			if(i > 0)
		      System.out.print(" ");
		System.out.print(nums[i]);
	}
	System.out.println();
	}
}