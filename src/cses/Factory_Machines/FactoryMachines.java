package cses.Factory_Machines;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FactoryMachines {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Factory_Machines/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Factory_Machines/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        FactoryMachines solver = new FactoryMachines();

        int t = 1;
        while (t-- > 0) {
            int machineCnt = in.nextInt();
            int productCnt = in.nextInt();
            int[] machines = new int[machineCnt];
            for(int i=0; i<machineCnt; i++) {
                machines[i] = in.nextInt();
            }
            solver.solve(machineCnt, productCnt, machines);
        }

        out.flush();
    }

    private void solve(int machineCnt, int productCnt, int[] machines) {
        Arrays.sort(machines);

        int a = machines[0];
        int b = machines[1];

        int x =  binarySearch(a, b, productCnt);
    }

    

    private int binarySearch(int a, int b, int productCnt) {
        int left = 0;
        int right = productCnt;

        while(left <= right) {

        }

        return 0;
    }



    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            br = new BufferedReader(new InputStreamReader(stream));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            try {
                return br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
