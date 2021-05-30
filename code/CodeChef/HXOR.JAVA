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
        // static 
        public static void main(String[] args){
        // Comment this code while running in Online Judge
            // try {
            //     // System.out.println(System.getProperty("ONLINE_JUDGE"));
            //     if (System.getProperty("ONLINE_JUDGE") == null) {
            //         FileOutputStream output=new FileOutputStream("output.txt");
            //         PrintStream out=new PrintStream(output);
            //         System.setOut(out);
                    
            //         InputStream input=new FileInputStream("input.txt");
            //         System.setIn(input); 
            //     }
                
            // } catch (FileNotFoundException e) {
            //     e.printStackTrace();
            // }

            try{
                Reader s=new Reader(); 
               PrintWriter out = new PrintWriter(System.out); 
               int t = s.nextInt();
               int k = 30;

            while(t>0){
                int n = s.nextInt();
                int x = s.nextInt();
             
             long[] inp = new long[n];
             ArrayList<Long>[] powers = new ArrayList[k+1];
             for(int l=0;l<=30;l++)
             powers[l] = new ArrayList<Long>();
                   
             int i = 0;
             while(i<n){
                 inp[i] = s.nextLong();
                 if(i!=n-1)
                 {
                     for(int l=k;l>=0;l--){
                         if((inp[i] & (1l<<l)) != 0)
                         powers[l].add((long) i);
                     }
                 }
                 i++;
             }
                 long[] cnt = new long[31];
                 
                 
                 for(i=0;i<n-1;i++){
                     for(int l=k;l>=0;l--){
                         long v = (1l<<l);
                         if((inp[i] & v) == 0)
                         continue;

                         inp[i] = inp[i]^v;

                         long sec = 0;
                         if(++cnt[l] < powers[l].size()){
                             sec = powers[l].get((int)cnt[l]++);
                            //  cnt[l] = cnt[l]+2;
                         }else {
                             sec = inp.length-1;
                         }
                         inp[(int)sec] = inp[(int)sec]^v;
                         x--;
                         if(x == 0)
                         break;
                     }
                     if(x == 0)
                     break;
                 }
             if(n == 2 && x%2 == 1){
                         inp[inp.length-1] = inp[inp.length-1]^1;
                    inp[inp.length-2] = inp[inp.length-2]^1;
                 }
        //          if(x%2==1 && inp[inp.length-1] == 0 && inp[inp.length-2] != 0 || x%2 == 1 && n%2 == 0 && inp[inp.length-1] == 0 && inp[inp.length-2] == 0){
        //     inp[inp.length-1] = inp[inp.length-1]^1;
        //     inp[inp.length-2] = inp[inp.length-2]^1;
        //  }
                 
                 
    //   if(inp[inp.length-1] != 0 && x%2 == 1 && n%2 == 0){
    //                 inp[inp.length-1] = inp[inp.length-1]^1;
    //                 inp[inp.length-2] = inp[inp.length-2]^1;
    //              }

            //   if(inp[inp.length-1] == 0 && x%2 != 0){
            //         inp[inp.length-1] = inp[inp.length-1]^1;
            //         inp[inp.length-2] = inp[inp.length-2]^1;
            //      }
            //     int ll = x%2 == 0 ? x/2: (x/2)+1;
            //     while(ll>0){
            //   inp[inp.length-1] = inp[inp.length-1]^1;
            //   inp[inp.length-2] = inp[inp.length-2]^1;  
            //         ll--;
            //     }
                //  out.println((-1%2));
            //   i = 0;
            // int j = i+1;
            // while(i<n && j < n){                
            //         if(x == 0)
            //         break;
                    
            //         long a  = inp[i];
            //         long b = inp[j];
            //         int tmpind = j;

            //         long near = (long)Math.ceil(Math.log(a)/Math.log(2));
                    
            //         while((1l<<near) > a)
            //         near--;
            //         if(a==0)
            //         near = 0;
                    
            //             tmpind = minv(inp, i, near);
            //             b = inp[tmpind];
                    
            //         inp[i] = a^(1l<<near);
            //         inp[tmpind] = b^(1l<<near);
            //         // out.println(i+" "+tmpind+" "+j+" "+near+" "+a+" "+b+" i "+inp[i]+" j "+inp[tmpind]+" "+(1l<<near)+" "+((1l<<near) < a));    
                    
            //            while(i< n && j<n && inp[i] == 0 && inp[j] == 0){
            //             i = j+1;
            //             j = j+2; 
            //             }

            //             while(i< n && j < n && inp[i] ==  0 && inp[j] != 0){
            //                 i = j;
            //                 j++;
            //             }

            //             while(j<n && inp[j] == 0)
            //             j++;

            //         x--; 
            //     }
            
            //   if(x%2 == 1){
            //       for(i=0;i<n;i++){
            //       if(inp[i] != 0)
            //       {
            //       break;
            //       }
            //     }
                
                
            //     for(j=i+1;j<n;j++)
            //     {
            //         if(inp[j] != 0)
            //         break;
            //     }
                
            //     if(i>= n-1){
            //         i = n-2;
            //         j = n-1;
            //     }
            //     else if(i == j){
            //         i = i-1;
            //         if(j == n)
            //         {
            //             j = i;
            //             i = i-1;
                        
            //         }
            //     }else if(i<n && j >= inp.length)
            //     {
            //         j = inp.length-1;
            //     }

            //     // System.out.println(i+" "+j+" "+inp[i]+" "+inp[j]);
            //     //   j = n-1;
            //     long p = inp[i] == 0 ? 0 : (long)Math.ceil(Math.log(inp[i])/Math.log(2));
            //     // System.out.println(p+" bef "+inp[i]);
            //     while(p != 0 && (1l<<p) > inp[i])
            //         p--;
                    
            //       inp[i] = inp[i]^(1l<<p);
            //       inp[j] = inp[j]^(1l<<p);
            //   }
             for(i=0;i<n;i++){
             out.print(inp[i]);
             if(i!=n-1)
             out.print(" ");
             }
             out.println();
             t--;
            }
            // int y = 1;
            // out.println((y++));
             out.flush();                
            }
            catch(Exception e){
            
                // System.out.println(e);
                    return;
                }
            
        }
        public static int minv(long[] inp, int i, long near){
            
            for(int j=i+1;j<inp.length;j++){
            if(inp[j] != 0 && (inp[j] ^ (1l<<near)) == 0 || ((inp[j] ^ (1l<<near)) < inp[j]))               
            return j;
            }
            return inp.length-1;
        }
            }
