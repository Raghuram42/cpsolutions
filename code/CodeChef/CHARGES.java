import java.io.*;
import java.util.*;

class Codechef {

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            List<Byte> buf = new ArrayList<Byte>();
            // byte[] buf = new byte[1000]; // line length
            int cnt = 0, c;

            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf.add((byte) c);
                // buf[c] = (byte) c;
                cnt++;
            }

            byte[] buf2 = new byte[buf.size()];
            int i = 0;
            for (Byte b : buf)
                buf2[i++] = b;

            return new String(buf2, 0, cnt);

        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
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
    static HashMap<Integer, List<Integer>> tree;
    static HashMap<Integer, List<Integer>> dpf;
    static HashMap<String, Long> hsm2;
    static HashMap<Integer, Long> factMap;
    static HashMap<Integer, HashSet<Integer>> graph;
    static HashMap<Integer, Long> hasallans;
    static HashMap<String, String> dp;
    static int[] indegrees;
    static boolean[] visited;
    static boolean ONLINE_JUDGE = true;
    static int itr;
    static boolean hasanyval;

    public static void main(String[] args) {
        // Comment this code while running in Online Judge
        try {
            // System.out.println(System.getProperty("ONLINE_JUDGE"));
            if (System.getProperty("ONLINE_JUDGE") == null && !ONLINE_JUDGE) {
                FileOutputStream output = new FileOutputStream("output.txt");
                PrintStream out = new PrintStream(output);
                System.setOut(out);

                InputStream input = new FileInputStream("input.txt");
                System.setIn(input);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Reader s = new Reader();
            util = new Utility();
            out = new PrintWriter(System.out);
            itr = 0;
            int t = s.nextInt();

            while (t > 0) {
                int n = s.nextInt();
                int k = s.nextInt();

                String inp = s.readLine().trim();

                while (inp.length() == 0)
                    inp = s.readLine().trim();

                char[] ary = inp.toCharArray();

                int dis = 0;
                int i = 1;
                while (i < ary.length) {

                    if (ary[i] == ary[i - 1])
                        dis += 2;
                    else
                        dis++;
                    // out.println(ary[i] + " " + ary[i - 1] + " " + dis);
                    i++;
                }

                i = 0;
                // out.println(dis + " dis ");
                while (i < k) {
                    int q = s.nextInt();

                    ary[q - 1] = ary[q - 1] == '0' ? '1' : '0';

                    if (q - 2 >= 0) {
                        if (ary[q - 2] == ary[q - 1])
                            dis += 1;
                        else
                            dis -= 1;
                    }
                    if (q < ary.length) {
                        if (ary[q - 1] == ary[q])
                            dis += 1;
                        else
                            dis -= 1;
                    }
                    out.println(dis);
                    i++;
                }
                t--;
            }
            out.flush();
        }

        catch (Exception e) {
            out.flush();
            System.out.println(e);
            return;
        }
    }

    static HashMap<Integer, HashSet<Integer>> groupedEmp;
    static HashMap<Integer, List<Integer>> graph1;
    static HashSet<Integer> visited1;
    static int[][] dpary;

    public static int solve(int[][] skill_cost, int[][] groupings, int B) {
        dpary = new int[1001][10001];

        for (int i = 0; i <= 1000; i++)
            Arrays.fill(dpary[i], -1);
        ArrayList<int[]> skilSet = new ArrayList<int[]>();
        HashSet<Integer> addedgroups = new HashSet<>();
        groupedEmp = new HashMap<>();
        graph1 = new HashMap<Integer, List<Integer>>();
        visited1 = new HashSet<>();
        for (int[] group : groupings) {
            int u = group[0];
            int v = group[1];
            if (!graph1.containsKey(u))
                graph1.put(u, new ArrayList<>());

            if (!graph1.containsKey(v))
                graph1.put(v, new ArrayList<>());

            graph1.get(u).add(v);
            graph1.get(v).add(u);
        }
        for (int i : graph1.keySet())
            processGraph(i, i);

        for (int next : groupedEmp.keySet()) {
            int[] comGro = new int[2];
            for (int emp : groupedEmp.get(next)) {
                addedgroups.add(emp);

                comGro[0] += skill_cost[emp][0];
                comGro[1] += skill_cost[emp][1];

            }
            skilSet.add(comGro);
        }

        for (int i = 1; i < skill_cost.length; i++) {
            if (!addedgroups.contains(i))
                skilSet.add(skill_cost[i]);
        }

        return helper(skilSet, 0, B);
    }

    public static void processGraph(int i, int mainP) {
        if (visited1.contains(i))
            return;
        visited1.add(i);
        if (!groupedEmp.containsKey(mainP))
            groupedEmp.put(mainP, new HashSet<Integer>());
        for (int next : graph1.get(i)) {
            groupedEmp.get(mainP).add(next);
            processGraph(next, mainP);
        }

    }

    public static int helper(ArrayList<int[]> inp, int i, int B) {
        if (i >= inp.size())
            return 0;

        if (dpary[i][B] != -1)
            return dpary[i][B];
        if (B - inp.get(i)[1] >= 0)
            return dpary[i][B] = Math.max(inp.get(i)[0] + helper(inp, i + 1, B - inp.get(i)[1]), helper(inp, i + 1, B));
        return dpary[i][B] = helper(inp, i + 1, B);
    }

    static int invertBits(int n) {
        // Calculate number of bits of N-1;
        int x = (int) (Math.log(n) / Math.log(2));

        int m = 1 << x;

        m = m | m - 1;

        n = n ^ m;
        // System.out.println(n);
        return n;
    }

    public static long bnc(int n, int r) {
        if (r > n)
            return 0;
        long m = 1000000007;
        long inv[] = new long[r + 1];
        inv[1] = 1;

        for (int i = 2; i <= r; i++) {
            inv[i] = m - (m / i) * inv[(int) (m % i)] % m;
        }

        int ans = 1;

        // for 1/(r!) part
        for (int i = 2; i <= r; i++) {
            ans = (int) (((ans % m) * (inv[i] % m)) % m);
        }

        // for (n)*(n-1)*(n-2)*...*(n-r+1) part
        for (int i = n; i >= (n - r + 1); i--) {
            ans = (int) (((ans % m) * (i % m)) % m);
        }
        return ans;
    }

    public static long fact(int n) {
        if (n <= 1)
            return 1;

        if (factMap.containsKey(n))
            return factMap.get(n);

        factMap.put(n, mod(n * 1l * fact(n - 1)));
        return factMap.get(n);
    }

    public static void remove(int[] fre, int[] edge) {
        fre[edge[0]]--;
        fre[edge[1]]--;

        if (fre[edge[0]] == 0)
            z--;
        if (fre[edge[1]] == 0)
            z--;

        if (fre[edge[0]] == fre[edge[1]] && fre[edge[0]] == 0)
            c--;

    }

    public static int bsearch(ArrayList<Integer> primes, int target) {
        int l = 0;
        int r = primes.size() - 1;
        while (l < r) {

            int mid = (l + r) / 2;
            if (primes.get(mid) == target)
                return mid + 1;
            else if (primes.get(mid) < target)
                l = mid + 1;
            else
                r = mid;

        }
        // out.println(target+" "+l);
        // return l;
        return primes.get(l) <= target ? l + 1 : l;
    }

    public static void add(int[] fre, int[] edge) {
        fre[edge[0]]++;
        fre[edge[1]]++;

        if (fre[edge[0]] == 1 && fre[edge[1]] == 1) {
            c++;
        }

        if (fre[edge[0]] == 1)
            z++;
        if (fre[edge[1]] == 1)
            z++;

    }

    public static long getn(long n) {
        if ((n >> 1) == 0)
            return 0;
        // out.println(n);
        return 1 + getn(n >> 1);
    }

    public static boolean[] preprocess_semiprimes(int n) {
        boolean[] semiprimes = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            ArrayList<Integer> facts = util.prime_Factors(i);
            if (facts.size() == 2 && facts.get(0) != facts.get(1))
                semiprimes[i] = true;
        }
        return semiprimes;
    }

    static class Query {
        int l;
        int r;
        int i;

        Query(int l, int r, int i) {
            this.l = l;
            this.r = r;
            this.i = i;
        }
    }

    static class Pr {
        int pos;
        int neg;

        Pr(int pos, int neg) {
            this.pos = pos;
            this.neg = neg;
        }
    }

    static class Pair {
        int len;
        int e;
        int o;

        Pair(int len, int e, int o) {
            this.len = len;
            this.e = e;
            this.o = o;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Pair))
                return false;

            Pair curr = (Pair) obj;

            if (curr.len == len && curr.e == e && curr.o == o && curr.hashCode() == this.hashCode())
                return true;
            return false;
        }

        @Override
        public int hashCode() {
            return this.len * 31 + this.e * 31 + this.o;
        }

        @Override
        public String toString() {
            return this.len + " " + this.e;
        }
    }

    public static long mod(long n) {
        return n < 0 ? 1000000007 + n : n % 1000000007;
    }

    static class Utility {
        // Complexity: O(Log min(a, b))
        public long ecu_gcd(long a, long b) {
            if (a == 0)
                return b;

            return ecu_gcd(b % a, a);
        }

        public int[] extended_ecu_gcd(int a, int b) {

            if (a == 0) {
                return new int[] { b, 0, 1 };
            }

            int[] temp = extended_ecu_gcd(b % a, a);

            return new int[] { temp[0], (temp[2] - (b / a) * temp[1]), temp[1] };
        }

        static ArrayList<Integer> primesnums;
        static boolean hasprimes = false;

        // Complexity: O(NLogN)
        public ArrayList<Integer> sieveOfEr_primes(int n) {
            hasprimes = true;
            boolean[] primes = new boolean[n + 1];
            primesnums = new ArrayList<Integer>();
            Arrays.fill(primes, true);
            int i = 2;
            for (i = 2; i * i <= n; i++) {
                if (primes[i]) {
                    for (int p = i * i; p <= n; p += i)
                        primes[p] = false;
                }
            }

            for (i = 2; i <= n; i++)
                if (primes[i])
                    primesnums.add(i);
            return primesnums;
        }

        public ArrayList<Integer> prime_Factors(int n) {
            ArrayList<Integer> res = new ArrayList<Integer>();

            while (n % 2 == 0) {
                res.add(2);
                n = n / 2;
            }

            for (int i = 3; i * i <= n; i = i + 2) {
                while (n % i == 0) {
                    res.add(i);
                    n = n / i;
                }
            }
            if (n > 2)
                res.add(n);

            return res;
        }

        public int prime_Factors2(int n) {
            HashMap<Integer, Integer> res = new HashMap<Integer, Integer>();

            while (n % 2 == 0) {
                res.put(2, res.getOrDefault(2, 0) + 1);
                n = n / 2;
            }

            for (int i = 3; i * i <= n; i = i + 2) {
                while (n % i == 0) {
                    res.put(i, res.getOrDefault(i, 0) + 1);
                    n = n / i;
                }
            }
            if (n > 2)
                res.put(n, res.getOrDefault(n, 0) + 1);

            int ress = 1;
            for (Map.Entry<Integer, Integer> en : res.entrySet()) {
                ress *= (en.getValue() + 1);
            }

            return ress;
        }

        public long[] fibnocnc(long k) {

            if (k == 0)
                return new long[] { 0, 1 };

            long[] t = fibnocnc(k >> 1);
            long a = (t[0] * (2 * t[1] - t[0]));
            long b = (t[0] * t[0] + t[1] * t[1]);

            if ((k & 1) == 1)
                return new long[] { b, (b + a) };
            return new long[] { a, b };
        }

        public long sumofN(long n) {
            return n * (n + 1) / 2;
        }

        public long pos_quadratic_root(long a, long b, long c) {
            return (-b + (long) Math.sqrt(b * b - 4 * a * c)) / 2 * a;
        }

        public long modInverse(long a, long m) {
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

        public TreeSet<Integer> getallFactors(int n) {
            TreeSet<Integer> hst = new TreeSet<Integer>();
            hst.add(1);
            for (int i = 2; i * 1l * i <= n; i++) {
                if (n % i == 0) {
                    hst.add(i);
                    if (i != n / i)
                        hst.add(n / i);
                }
            }
            return hst;
        }
    }
}
