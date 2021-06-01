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
                byte[] buf = new byte[1000]; // line length 
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
                int n = s.nextInt();
                
                int i = 0;
                int start  = -1;
                int end = -1;
                int prev = -1;
                int[] inp = new int[n];
                int[] clone = new int[n];
                
                while(i<n){
                inp[i] = s.nextInt();
                clone[i] = inp[i];
                i++;
                }
                Arrays.sort(clone);
                // int count = 0;
                boolean founds = false;
                int inds = -1;
                int inde = -1;
                for(i=0;i<n;i++){
                    // System.out.println(inp[i]+" "+clone[i]+" "+inds+" "+inde);
                    if(inp[i] != clone[i]){
                    //   count++;
                    if(!founds){
                      founds = true;
                      if(inds == -1)
                      inds = i;
                    }else {
                        if(inde == -1 || inp[inde] > inp[i]){
                            inde = i;
                        
                    }
                }
                      
                    }
                }
                if(inde != -1 && inds != -1)
                swap(inp, inds, inde);
                else{
                    inde = 0;
                    inds = 0;
                }
                if(Arrays.equals(inp, clone)){
                    System.out.println("yes");
                    System.out.println((inds+1)+" "+(1+inde));
                }
                else
                System.out.println("no");
                
                
            }
            catch(Exception e){
            
                System.out.println(e);
                    return;
                }
            
        }
        public static void swap(int[] inp, int i, int j){
            if(i == j)
            return;
            int k = j;
            for(int p =i;p<=(j+i)/2;p++){
                int tmp = inp[k];
                 inp[k] = inp[p];
                 inp[p] = tmp;
                k--;
            }
        }
        // public static boolean allsame(int[] ){}
    }
