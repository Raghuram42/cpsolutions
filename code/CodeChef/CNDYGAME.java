// Working program using Reader Class 
import java.io.DataInputStream; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.Scanner; 
import java.util.StringTokenizer; 

class Codechef 
{ 
	
	static long[] dp;
		static long mod;
	static class Reader 
	{ 
		final private int BUFFER_SIZE = 1 << 16; 
		private DataInputStream din; 
		private byte[] buffer; 
		private int bufferPointer, bytesRead; 

		public Reader() 
		{ 
			din = new DataInputStream(System.in); 
			buffer = new byte[BUFFER_SIZE]; 
			bufferPointer = bytesRead = 0; 
		} 

		public Reader(String file_name) throws IOException 
		{ 
			din = new DataInputStream(new FileInputStream(file_name)); 
			buffer = new byte[BUFFER_SIZE]; 
			bufferPointer = bytesRead = 0; 
		} 

		public String readLine() throws IOException 
		{ 
			byte[] buf = new byte[64]; // line length 
			int cnt = 0, c; 
			while ((c = read()) != -1) 
			{ 
				if (c == '\n') 
					break; 
				buf[cnt++] = (byte) c; 
			} 
			return new String(buf, 0, cnt); 
		} 

		public int nextInt() throws IOException 
		{ 
			int ret = 0; 
			byte c = read(); 
			while (c <= ' ') 
				c = read(); 
			boolean neg = (c == '-'); 
			if (neg) 
				c = read(); 
			do
			{ 
				ret = ret * 10 + c - '0'; 
			} while ((c = read()) >= '0' && c <= '9'); 

			if (neg) 
				return -ret; 
			return ret; 
		} 

		public long nextLong() throws IOException 
		{ 
			long ret = 0; 
			byte c = read(); 
			while (c <= ' ') 
				c = read(); 
			boolean neg = (c == '-'); 
			if (neg) 
				c = read(); 
			do { 
				ret = ret * 10 + c - '0'; 
			} 
			while ((c = read()) >= '0' && c <= '9'); 
			if (neg) 
				return -ret; 
			return ret; 
		} 

		public double nextDouble() throws IOException 
		{ 
			double ret = 0, div = 1; 
			byte c = read(); 
			while (c <= ' ') 
				c = read(); 
			boolean neg = (c == '-'); 
			if (neg) 
				c = read(); 

			do { 
				ret = ret * 10 + c - '0'; 
			} 
			while ((c = read()) >= '0' && c <= '9'); 

			if (c == '.') 
			{ 
				while ((c = read()) >= '0' && c <= '9') 
				{ 
					ret += (c - '0') / (div *= 10); 
				} 
			} 

			if (neg) 
				return -ret; 
			return ret; 
		} 

		private void fillBuffer() throws IOException 
		{ 
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); 
			if (bytesRead == -1) 
				buffer[0] = -1; 
		} 

		private byte read() throws IOException 
		{ 
			if (bufferPointer == bytesRead) 
				fillBuffer(); 
			return buffer[bufferPointer++]; 
		} 

		public void close() throws IOException 
		{ 
			if (din == null) 
				return; 
			din.close(); 
		} 
	} 
    static long mx;
    static StringBuffer sb;
	public static void main(String[] args) throws IOException 
	{ 
		Reader s=new Reader(); 
		mod = 1000000007;
		int t = s.nextInt();
		sb = new StringBuffer();
		while(t>0){
		int n = s.nextInt();
		dp = new long[n+1];
		long[] inp = new long[n];
		
		int p = 0;
		mx = 0;
		while(p<n){
			inp[p] = s.nextLong();
			p++;
		}
		prepop(inp);
		int q = s.nextInt();
		int k = 0;
		long[] queries = new long[q];
			while(q>0){
				   queries[k] = (s.nextLong())%mod;
				   k++;
					q--;
				}
				solve(inp, queries);
				t--;
		}
		if(sb.length() > 0)
		sb.deleteCharAt(sb.length()-1);
		System.out.println(sb);

	} 
		static 	boolean flg;
	public static void prepop(long[] inp){
		long res = 0;
	 flg = false;
		int saveind = -1;
		if(inp[0] == 1)
				flg = true;
		 
		if(flg){
			return;
		}
	        for(int i=0;i<inp.length;i++){
			long val = inp[i];
			if(val == 1 && i != inp.length-1)
			 dp[i+1] = inp[i-1]%2 == 1 ? dp[i]: (dp[i]+1)%mod;
			 else if(i>0 && inp[i-1]%2 == 1 && inp[i-1] != 1)
			 dp[i+1] = (dp[i]+val-1)%mod;
			 else if(i>0 && inp[i-1] == 1 && inp[i-2]%2 == 0){
			 	dp[i+1] = (dp[i]+val-2)%mod;
			 }
			 else
			 dp[i+1] = (dp[i]+val)%mod;
		}	
	}
		public static void solve(long[] inp, long[] queries){
         for(int q = 0;q < queries.length; q++){
         long r = queries[q];
         if(!flg && (r<=inp.length*1l)){
         	long res = 0;
      
		sb.append((dp[(int)r]%mod)).append(System.getProperty("line.separator"));
		

         }
		 else if(flg){
         	long res = (((r%mod)/(inp.length*1l))+(r%(inp.length*1l) != 0 ? 1: 0))%mod; 
         	if(r != 1 && r%(inp.length*1l) == 1)
         	 res = (res-1)%mod;
         	 sb.append((res%mod)).append(System.getProperty("line.separator"));
         
		}
	
		else{
			long val = inp[(int)(inp.length-1)];
			long multiplr = r/(inp.length*1l);
			int rema = (int)(r%(inp.length*1l));
		 long res = val%2 == 0 ? dp[inp.length]-1 :dp[inp.length];
		 long res2 = dp[rema];
			long ans = (((res%mod)*(multiplr)%mod)%mod) + res2 + (val%2 == 0 && rema == 0 ? 1: 0);
	
			sb.append((ans%mod)).append(System.getProperty("line.separator"));
		}
		mx = Math.max(mx, queries[q]);
         }
          
  
	}
	
} 
