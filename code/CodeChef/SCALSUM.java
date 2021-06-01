// Working program using Reader Class 
import java.io.DataInputStream; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.Scanner; 
import java.util.StringTokenizer; 
import java.util.*;
class Codechef 
{ 
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
	static ArrayList<Integer>[] graph;
	static int[] deps;
	static int maxdep;
	static long[] dotprod;
	static ArrayList<Integer>[] depwisenodes;
	static boolean prepop[];
	static int[] ids;
	static int[] parents;
	static ArrayList<Long>[] memo;
	static int[] weights;
    public static void main(String[] args) throws IOException 
	{ 
		try{
		Reader s=new Reader(); 
	    int n = s.nextInt();
	    int q  = s.nextInt();
	    
	    graph = new ArrayList[n+1];
	    // depwisenodes = new ArrayList
	    dotprod = new long[n+1];
	    ids = new int[n+1];
	    parents = new int[n+1];
	    memo = new ArrayList[n+1];
	    deps = new int[n+1];
	    weights = new int[n+1];
	    prepop = new boolean[n+1];
	    
	    for(int i=0;i<=n;i++)
	     {
	     	graph[i] = new ArrayList<Integer>();
	     	memo[i] = new ArrayList<Long>();
	     }
	     
	     //System.out.println("1");
	     int p = 1;
	     while(p<=n){
	     	weights[p] = s.nextInt();
	     	p++;
	     }
	     //System.out.println("2");
	     p = 1;
	     while(p<n){
	     	int a = s.nextInt();
	     	int b = s.nextInt();
	     	
	     	graph[a].add(b);
	     	graph[b].add(a);
	     	p++;
	     }
	     //System.out.println("3");
	     dfs(1, 0, 1);
	     //System.out.println("dfs");
	     depwisenodes = new ArrayList[maxdep+1];
	     for(int i=0;i<=maxdep;i++)
	      depwisenodes[i] = new ArrayList<Integer>();
	     //System.out.println("dep");
	     for(int i=1;i<=n;i++)
	        depwisenodes[deps[i]].add(i);
	        
	     //System.out.println("4");   
	     int b = (int)Math.sqrt(n);
	    for(int i=0;i<=maxdep;i+=b){
	    	int lv = i;
	    	int sz = depwisenodes[i].size();
	    	
	    	for(int j = i;j<=Math.min(i+b, maxdep);j++){
	    		if(sz > depwisenodes[j].size())
	    		{
	    			sz = depwisenodes[j].size();
	    			lv = j;
	    		}
	    	}
	    	
	    	
	    	int id = 0;
	    	
	    	for(int po=0;po<sz;po++)
	    	 ids[depwisenodes[lv].get(po)] = id++;
	    // System.out.println("after ids ");   	 
	    	 for(int f: depwisenodes[lv]){
	    	 	for(int ss: depwisenodes[lv]){
	    	 		if(ids[f] <= ids[ss])
	    	 		memo[lv].add(query(f, ss));
	    	 		else
	    	 		memo[lv].add(memo[lv].get(ids[ss]*sz+ids[f]));
	    	 		
	    	 		// System.out.println(" f "+f+" s: "+ss);
	    	 	}
	    	 	
	    	 	prepop[f] = true;
	    	 }
	    	 
	    }
	    // System.out.println("5");
	    p = 1;
	    while(p<=q){
	    	int x = s.nextInt();
	    	int y = s.nextInt();
	    	
	    	long res = query(x, y);
	    	
	    	System.out.println(smod(res));
	    	p++;
	    }
	      
	      
		}
		catch(Exception e){
// 			System.out.println(e);
			return;
		}
	
	}
	public static long query(int q, int u){
	long res = 0;
	// System.out.println(q+" bef "+u);
	while(q != u && !prepop[q]){
		res = smod(smod(res) + smod(smod(weights[q])*smod(weights[u])));
		q = parents[q];
		u = parents[u];
	}
	// System.out.println(q+" aft "+u);
	if(q == u)
	  res = smod(smod(res) + smod(dotprod[q]));
	else
	  res = smod(smod(res)+smod(memo[deps[q]].get(ids[q]*depwisenodes[deps[q]].size() + ids[u])));
	// System.out.println(" end "+res);  
	  return res;
	
	}
	
	public static void dfs(int i, int dep, int parent){
		// System.out.println(i+" "+dep);
		parents[i] = parent;
		deps[i] = dep;
		maxdep = Math.max(dep, maxdep);
		dotprod[i] = smod(smod(dotprod[parent]) + smod(smod(weights[i])*smod(weights[i])));
		for(int v: graph[i])
		   if(v != parent)
		     dfs(v, dep+1, i);
		
	}
	public static long smod(long p){
		return pB94967296L;
	}
	
}
   ystem.out.println(memo[q]+" "+memo[q].get(u)+" "+deps[q]+" "+b);
    if(deps[q]%b == 0){
    	Long res = memo[q].get(u);
    	// System.out.println(memo[q]+" "+memo[q].get(u)+" "+deps[q]+" "+b);
    	if(res != null)
    	return res;
    	res = smod(smod(smod(weights[q])*smod(weights[u]))+ smod(query(parents[q], parents[u])));
    	
    	memo[q].put(u, res);
    	
    	return smod(res);
    	
    }
    return smod(smod(weights[q])*smod(weights[u])+ smod(query(parents[q], parents[u])));
	}
	
	public static void dfs(int i, int dep, int parent){
		// System.out.println(i+" "+dep);
		parents[i] = parent;
		deps[i] = dep;
		maxdep = Math.max(dep, maxdep);
		dotprod[i] = smod(smod(dotprod[parent]) + smod(smod(weights[i])*smod(weights[i])));
		for(int v: graph[i])
		   if(v != parent)
		     dfs(v, dep+1, i);
		
	}
	public static long smod(long p){
		return pB94967296L;
	}
	
}
   