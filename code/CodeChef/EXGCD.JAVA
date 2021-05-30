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
                static int[] dp;
                static int[] inp;
                static PrintWriter out;
                static int z;
                static int c;
                static Utility util;
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
                        int t= s.nextInt();
                        int N = (int)2e5+5;
                        int[] fx = new int[N];
                        int[] gx = new int[N];
                        int md = (int)1e9+7;
                        while(t>0){
                            int n = s.nextInt();

                            int[] a = new int[n];
                            int[] b = new int[n];
                            int i = 0;
                            while(i<n){
                                a[i] = s.nextInt();
                                b[i] = s.nextInt();
                                // out.println(a[i]+" "+b[i]);
                                i++;
                            }

                            for(i=1;i<N;i++)
                            {
                                fx[i] = 1;
                                for(int j=0;j<n;j++){
                                    fx[i] = (int)mod(1l*fx[i]*((b[j]/i)-((a[j]-1)/i)));
                                }
                            }

                            for(i=N-1;i>=1;i--){
                                gx[i] = fx[i];
                                
                                for(int j=2*i;j<N;j+=i){
                                    gx[i] = (int)mod(gx[i]+0l-mod(gx[j]*1l));
                                }
                            }

                            int ans = 0;

                            for(i=1;i<N;i++){
                            ans = (int)mod((1l*ans)+mod(1l*i*gx[i]));
                            // out.println(ans);
                            }

                            int q = 1;

                            for(i=0;i<n;i++)
                            q = (int)mod(q*1l*(b[i]-a[i]+1));
                            
                            // out.println(q+" "+ans+" "+util.modInverse(q, md));
                            out.println(mod(md-mod(1l*ans*util.modInverse(q*1l, md*1l))));
                            t--;
                        }
                        out.flush();                
                    }
                    catch(Exception e){
                        out.flush();       
                        System.out.println(e);
                            return;
                        }
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
          
            }
    }
