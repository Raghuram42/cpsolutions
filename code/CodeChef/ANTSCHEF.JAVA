import java.io.*;
import java.util.*;

         class Codechef{

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
                    List<Byte> buf = new ArrayList<Byte>();
                //   byte[] buf = new byte[1000]; // line length 
                int cnt = 0, c; 
        
                while ((c = read()) != -1) 
                { 
                    if (c == '\n') 
                        break; 
                    buf.add((byte) c); 
                    //   buf[c] = (byte) c;
                    cnt++;
                } 
                
                byte[] buf2 = new byte[buf.size()];
                int i = 0;
                for(Byte b: buf)
                    buf2[i++] = b;

                return new String(buf2, 0, cnt); 

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
            static PrintWriter out;
            static Utility util;
            static long[] mi;
            static long[] vi;
            static int c;
            static int z;
            static long ans[];
            static HashMap<Integer, TreeSet<Integer>> inp;
            static boolean ONLINE_JUDGE = true;
            public static void main(String[] args){
            // Comment this code while running in Online Judge
                try {
                    // System.out.println(System.getProperty("ONLINE_JUDGE"));
                    if (System.getProperty("ONLINE_JUDGE") == null && !ONLINE_JUDGE) {
                        FileOutputStream output=new FileOutputStream("output.txt");
                        PrintStream out=new PrintStream(output);
                        System.setOut(out);
                        
                        InputStream input=new FileInputStream("input.txt");
                        System.setIn(input); 
                    }
                    
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                try{
                    Reader s=new Reader(); 
                    util = new Utility();
                    out = new PrintWriter(System.out); 
           
                    int t = s.nextInt();

                        while(t > 0){
                        int n = s.nextInt();

                        int i = 1;
                        Pr[] sizs = new Pr[n+1];

                        TreeMap<Integer, ArrayList<Integer>> g = new TreeMap<Integer, ArrayList<Integer>>();
                        inp = new HashMap<Integer, TreeSet<Integer>>();    
                        
                        while(i<=n){

                            int m = s.nextInt();
                            int pos = 0;
                            int neg = 0;
                            inp.put(i, new TreeSet<Integer>());
                            int p = 0;
                            while(p<m){
                              int xi = s.nextInt();
                              if(xi < 0)
                              neg++;
                              else
                              pos++;
                                inp.get(i).add(xi);
                              if(!g.containsKey(Math.abs(xi)))
                              g.put(Math.abs(xi), new ArrayList<Integer>());

                              g.get(Math.abs(xi)).add(xi < 0 ? -i : i);
                                p++;
                            }
                            sizs[i] = new Pr(pos, neg);
                            i++;
                        }
                        // out.println(g+" ");
                        out.println(solve(g, sizs));
                        t--;
                    }
                // long p = Integer.MAX_VALUE+0l+1;
                // out.println((2&-2));    
                

                    out.flush();                
                }
                catch(Exception e){
                    out.flush();       
                    System.out.println(e);
                        return;
                    }
            }
            public static long solve(TreeMap<Integer, ArrayList<Integer>> g, Pr[] sz){
                long res = 0;
                for(Integer key : g.keySet()){
                    ArrayList<Integer> lst = g.get(key);
                    long tempres = 0;
                    
                    if(lst.size() > 1 ){
                        // if()
                        
                        for(Integer li : lst){
                            Pr currcoll = sz[Math.abs(li)];
                            
                            if(li < 0){                                                                                                 
                                if(currcoll.neg > 0){
                                currcoll.neg--;
                                }
                                tempres += (currcoll.neg);
                            }else{
                                if(currcoll.pos > 0){
                                currcoll.pos--;
                                // res += 1;
                                }
                                
                                tempres += (currcoll.pos);
                            }
                            // out.println(tempres+" af "+ key+" " + li+" "+lst+" "+currcoll.pos+" "+currcoll.neg+" "+hasnay);
                        }
                        
                        // if(hasnay)
                        tempres += 1;
                        
                        
                    }
                    else{
                        Pr currcoll = sz[Math.abs(lst.get(0))];
                        if(lst.get(0) < 0){                                                                                                 
                            if(currcoll.neg > 0){
                            currcoll.neg--;
                            
                            }
                            tempres += (currcoll.pos+0l);
                        }else{
                            if(currcoll.pos > 0){
                            currcoll.pos--;
                            // res += 1;
                            }
                            
                            tempres += (currcoll.neg+0l);
                        }
                        
                        // out.println(tempres+" ");
                    }
                    // out.println("key "+key+" "+tempres);
                  res += tempres;
                }
                return res;
            }
            public static void remove(int[] fre, int[] edge){
                fre[edge[0]]--;
                fre[edge[1]]--;

                if(fre[edge[0]] == 0)
                z--;
                if(fre[edge[1]] == 0)
                z--;

                if(fre[edge[0]] == fre[edge[1]] && fre[edge[0]] == 0)
                c--;

            }

            public static void add(int[] fre, int[] edge){
                fre[edge[0]]++;
                fre[edge[1]]++;

                if(fre[edge[0]] == 1 && fre[edge[1]] == 1){
                c++;
                }

                
                if(fre[edge[0]] == 1)
                z++;
                if(fre[edge[1]] == 1)
                z++;

            }

            public static long getn(long n){
                if((n>>1) == 0)
                return 0;
                // out.println(n);
                return 1+getn(n>>1);
            }

            public static boolean[] preprocess_semiprimes(int n){  
                boolean[] semiprimes = new boolean[n+1];
                for(int i=2;i<=n;i++){
                    ArrayList<Integer> facts = util.prime_Factors(i);
                    if(facts.size() == 2 && facts.get(0) != facts.get(1))
                    semiprimes[i] = true;
                }
                return semiprimes;
            }
            
         static class Query{
             int l;
             int r;
             int i;

             Query(int l, int r, int i){
                this.l = l;
                this.r= r;
                this.i = i;
             }
         }

         static class Pr{
             int pos;
             int neg;

             Pr(int pos, int neg){
                 this.pos = pos;
                 this.neg = neg;
             }
         }

        static class Pair{
            int len;
            int e;
            int o;

            Pair(int len, int e, int o){
                this.len = len;
                this.e = e;
                this.o = o;
            }
            
            @Override
            public boolean equals(Object obj){
                if(obj == null || !(obj instanceof Pair))
                return false;

                Pair curr = (Pair)obj;

                if(curr.len == len && curr.e == e && curr.o == o && curr.hashCode() == this.hashCode())
                return true; 
                return false;
            }

            @Override
            public int hashCode(){
                return this.len*31+this.e*31+this.o;
            }
        }
        public static long mod(long n){
            return n<0 ? 1000000007+n :n00000007;
        }
    static class Utility{
        //Complexity: O(Log min(a, b))
        public long ecu_gcd(long a, long b){
            if(a == 0)
            return b;

            return ecu_gcd(b%a, a);
        }

        public int[] extended_ecu_gcd(int a, int b){

            if(a==0){
                return new int[]{b, 0, 1};
            }

            int[] temp = extended_ecu_gcd(b%a,a);

            return new int[]{temp[0], (temp[2]-(b/a)*temp[1]), temp[1]};
        }

        static ArrayList<Integer> primesnums;
        static boolean hasprimes = false;
        //Complexity: O(NLogN)
        public ArrayList<Integer> sieveOfEr_primes(int n){
            hasprimes =true;
            boolean[] primes = new boolean[n+1];
            primesnums = new ArrayList<Integer>();
            Arrays.fill(primes, true);
            int i= 2;
            for(i=2;i*i<=n;i++){
                if(primes[i]){
                    for(int p=i*i;p<=n;p+=i)
                    primes[p] = false;
                }
            }
            
            for(i=2;i<=n;i++)
            if(primes[i])
            primesnums.add(i);
            return primesnums;
        }

        public ArrayList<Integer> prime_Factors(int n){
            ArrayList<Integer> res = new ArrayList<Integer>();

            while(n%2 == 0){
                res.add(2);
                n = n/2;
            }

            for(int i=3;i*i<=n;i=i+2){
                while(n%i == 0){
                    res.add(i);
                    n = n/i;
                }
            }
            if(n > 2)
            res.add(n);

            return res;
        }
        
        public int prime_Factors2(int n){
            HashMap<Integer, Integer> res = new HashMap<Integer, Integer>();

            while(n%2 == 0){
                res.put(2, res.getOrDefault(2, 0)+1);
                n = n/2;
            }

            for(int i=3;i*i<=n;i=i+2){
                while(n%i == 0){
                    res.put(i, res.getOrDefault(i, 0)+1);
                    n = n/i;
                }
            }
            if(n > 2)
            res.put(n, res.getOrDefault(n, 0)+1);
            

            int ress = 1;
            for(Map.Entry<Integer, Integer> en : res.entrySet()){
                ress *= (en.getValue()+1);
            }

            return ress;
        }
        
        

        public long[] fibnocnc(long k){
            
            if(k == 0)
            return  new long[]{0,1};

            long[] t = fibnocnc(k>>1);
            long a = (t[0]*(2*t[1]-t[0]));
            long b = (t[0]*t[0]+t[1]*t[1]);
            
            if((k & 1) == 1)
            return new long[]{b, (b+a)};
            return new long[]{a, b};
        }  
        
        public long sumofN(long n){
         return n*(n+1)/2;               
        }

        public long pos_quadratic_root(long a, long b, long c){
            return (-b +(long)Math.sqrt(b*b -4*a*c))/2*a;
        }
        public long modInverse(long a, long m) 
        { 
            // out.println(a+" "+m);
            long m0 = m; 
            long y = 0, x = 1; 
      
            if (m == 1) 
                return 0; 
      
            while (a > 1) { 
                // q is quotient 
                
                long q = a / m; 
      
                long t = m; 
      
                // m is remainder now, process 
                // same as Euclid's algo 
                m = a % m; 
                a = t; 
                t = y; 
      
                // Update x and y 
                y = x - q * y; 
                x = t; 
            } 
      
            // Make x positive 
            if (x < 0) 
                x += m0; 
                // out.println(x);
            return x; 
        } 
      
        public TreeSet<Integer> getallFactors(int n){
            TreeSet<Integer> hst = new TreeSet<Integer>();
            hst.add(1);
            for(int i=2;i*1l*i <=n;i++){
                if(n%i == 0){
                    hst.add(i);
                    if(i != n/i)
                    hst.add(n/i);
                }
            }
            return hst;
        }
        }
}
