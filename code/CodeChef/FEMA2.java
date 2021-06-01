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
 
			while(t > 0) {
				String[] nk = reader.readLine().split(" ");
				int n = Integer.parseInt(nk[0]);
				int k = Integer.parseInt(nk[1]);
				String str = reader.readLine();
				solve(str, k);
				t--;
			}
		}
		catch(Exception e){
			System.out.println(e);
			return;
		}
	}
	
	public static void solve(String str, int k){
		Queue<Integer> irons = new LinkedList<Integer>();
		Queue<Integer> magnets = new LinkedList<Integer>();
		ArrayList<Integer> blocks = new ArrayList<Integer>();
		int[] sheets = new int[str.length()];
		
		for(int i=0;i<str.length();i++){
			
			if(str.charAt(i) == 'I')
			  irons.offer(i);
			else if(str.charAt(i) == 'M')  
			  magnets.offer(i);
			else if(str.charAt(i) == 'X')
			  blocks.add(i);
			  
			if(str.charAt(i) == ':')  
			 sheets[i] = (i==0)? 1 : sheets[i-1]+1;
			 else
			  sheets[i] = (i==0)? 0 : sheets[i-1];
		}
		int max = 0;
		while(magnets.size() > 0 && irons.size() > 0){
				int mag = magnets.peek();
				int ir = irons.peek();
				int cost = k+1-(Math.abs(mag-ir))-(Math.abs(sheets[mag]-sheets[ir]));
		       //System.out.println("mg: "+mag+" ir "+ir+" "+cost+" "+max);
				if(ir < mag && cost <= 0)
				{
					irons.poll();
				}else if(cost > 0 && (blocks.size() <= 0 || ((ir < mag && lower(blocks, mag, ir)) || (ir > mag && upper(blocks, mag, ir))))){
					max++;
					magnets.poll();
					irons.poll();
				}else if(ir < mag){
					irons.poll();
				}else{
					magnets.poll();
				}
			}
		
		System.out.println(max);
		
		
	}
	
	public static boolean lower(ArrayList<Integer> b, int t, int iron ){
		// if(b.size)
		int l =0;
		int r = b.size()-1;
		
		while(l<r-1){
			int mid = (l+r)/2;
			if(b.get(mid) < t)
			  l = mid;
			else
			 r = mid-1;
		}
		if(r < 0 || r>=b.size())
		return true;
		int val = b.get(r) < t ? b.get(r) : b.get(l);
		// System.out.println("val low "+val);
		return (val > t && val > iron || val < t & val < iron);
		}
	
	public static boolean upper(ArrayList<Integer> b, int t, int iron ){
		int l =0;
		int r = b.size()-1;
		
		while(l<r){
			int mid = (l+r)/2;
			if(b.get(mid) < t)
			  l = mid+1;
			else
			 r = mid;
		}
		if(r < 0 || r >= b.size())
		 return true;
		int val =  b.get(r);
		// System.out.println("val upp "+val);
		return (val > t && val > iron || val < t & val < iron);
		
	}
}
