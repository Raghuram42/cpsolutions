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
        static PrintWriter out;
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
            //    int mod = 1000000007;
               int x = s.nextInt();
               int y = s.nextInt();
               int n = s.nextInt();
               int res = mod(x) ;

                int[] temp = new int[7];
                int ind = 1;
                //for(int p:hst)
                temp[ind++] = x;
                temp[ind++] = y;
                temp[ind++] = mod(y-x);
                temp[ind++] = x*-1;
                temp[ind++] = y*-1;
                temp[ind++] = mod(temp[3]*-1);
                
                res = mod(temp[n%6 == 0 ? 6 : n%6]);
            //    }
               out.println(res);
            //    out.println(mod(-1));
             
             out.flush();                
            }
            catch(Exception e){
            
                System.out.println(e);
                    return;
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
    }
