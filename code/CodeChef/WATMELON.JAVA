
import java.util.*;
import java.lang.*;
import java.io.*;

class FastReader
{
        BufferedReader br; 
    StringTokenizer st; 

    public FastReader() 
    { 
        br = new BufferedReader(new
                 InputStreamReader(System.in)); 
    } 

    String next() 
    { 
        while (st == null || !st.hasMoreElements()) 
        { 
            try
            { 
                st = new StringTokenizer(br.readLine()); 
            } 
            catch (IOException  e) 
            { 
                e.printStackTrace(); 
            } 
        } 
        return st.nextToken(); 
    } 

    int nextInt() 
    { 
        return Integer.parseInt(next()); 
    } 
long nextLong() 
    { 
        return Long.parseLong(next()); 
    } 

    double nextDouble() 
    { 
        return Double.parseDouble(next()); 
    } 

    String nextLine() 
    { 
        String str = ""; 
        try
        { 
            str = br.readLine(); 
        } 
        catch (IOException e) 
        { 
            e.printStackTrace(); 
        } 
        return str; 
    } 
}
class  codechef
{
    public static void main(String args[])
    { FastReader sc=new FastReader();
    int t=sc.nextInt();
    while(t-->0)
    { int n=sc.nextInt();
    int a[]=new int[n];
    for(int i=0;i<n;i++)
    {
        a[i]=sc.nextInt();
    }
     if(n == 1 && a[0] < 0)
     System.out.println("NO");
     else{
         int sum1 = 0;
         int sum2 = 0;
         for(int i=0;i<n;i++){
             if(a[i] <0)
             sum1 += a[i]*-1;
             else
             sum2 += a[i];
         }
         
         if(sum1>sum2)
         System.out.println("NO");
         else
         System.out.println("YES");
         
     }
	  }
    }
}