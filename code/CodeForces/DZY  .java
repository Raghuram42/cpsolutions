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
        public static void main(String[] args){
        ///Comment this code while running in Online Judge
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
               PrintWriter out = new PrintWriter(System.out); 
                String n = s.readLine().trim();
                while(n.length() == 0)
                n = s.readLine().trim();

                int k = s.nextInt();

                int i = 0;
                int[] mapr = new int[26];
                int max = 0;
                while(i<26){
                    mapr[i] = s.nextInt();
                    max = Math.max(max, mapr[i]);
                    i++;
                }

                long res = 0;
                for(i = 0;i<(n.length()+k);i++){
                    if(i<n.length())
                    res += (mapr[n.charAt(i)-'a']*(i+1));
                    else
                    res += (max*(i+1));
                }

                out.println(res);
                out.flush();                
            }
            catch(Exception e){
            
                System.out.println(e);
                    return;
                }
            
        }
    }
