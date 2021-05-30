    import java.io.*;
    import java.util.*;
            public class Codechef{

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
                static int[] dp;
                static long[] fib;
                static long c;
                static int[] inp;
                static PrintWriter out;
                static Utility util;
                public static void main(String[] args){
                // Comment this code while running in Online Judge
                    try {
                        // System.out.println(System.getProperty("ONLINE_JUDGE"));
                        if (System.getProperty("ONLINE_JUDGE") == null) {
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
                        out = new PrintWriter(System.out); 

                        util = new Utility();
                        int n = s.nextInt();
                        int t = s.nextInt();

                        c = 0;
                        int sq = (int)Math.sqrt(n);
                        int[] inp = new int[n+1];

                        int i = 1;

                        while(i<=n)
                        inp[i++] = s.nextInt();

                        Query[] queries = new Query[t];
                        i=0;

                        while(i<t){
                            queries[i] = new Query(s.nextInt(), s.nextInt(), i);
                            i++;
                        }

                        Arrays.sort(queries, new Comparator<Query>(){
                            @Override
                            public int compare(Query q1, Query q2){
                                int a = (q1.l-1)/sq;
                                int b = (q2.l-1)/sq;

                                if(a != b)
                                return a-b;

                                return a%2 == 0 ? q1.r-q2.r: q2.r-q1.r;
                            }
                        });


                        long[] ans = new long[t];
                        int[] freq = new int[1000001];
                        int lft = 1;
                        int rgt = 0;

                        for(Query q: queries){

                         

                            while(lft > q.l){
                                lft--;
                                add(freq, inp[lft]);
                            }

                            while(rgt < q.r){
                                rgt++;
                                add(freq, inp[rgt]);
                            }

                            while(lft < q.l){
                                remove(freq, inp[lft]);
                                lft++;
                            }

                            while(rgt > q.r){
                                remove(freq, inp[rgt]);
                                rgt--;
                            }

                            ans[q.i] = c;
                        }

                      for(i=0;i<t;i++)
                      out.println(ans[i]);

                    out.flush();                
                    }
                    catch(Exception e){
                        out.flush();       
                        System.out.println(e);
                            return;
                        }
                    
                }
            
                public static void remove(int[] freq, int val){
                    
                    long prev  = freq[val]*1l*freq[val]*1l*val;
                    freq[val]--;
                    c = c-prev+(freq[val]*1l*freq[val]*1l*val);

                }

                public static void add(int[] freq, int val){
                    
                    long prev  = freq[val]*1l*freq[val]*1l*val;
                    freq[val]++;
                    c = c-prev+(freq[val]*1l*freq[val]*1l*val);
                    
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
                        this.r = r;
                        this.i = i;
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
            public static int mod(int n){
                return n<0 ? 1000000007+n :n%1000000007;
            }
        static class Utility{
            //Complexity: O(Log min(a, b))
            public int ecu_gcd(int a, int b){
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
            }
    }
