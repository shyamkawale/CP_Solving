package codeforces.problems.B2175_XOR_Array;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class B2175_XOR_Array {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/codeforces/problems/prefix_sum/B2175_XOR_Array/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/codeforces/problems/prefix_sum/B2175_XOR_Array/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        B2175_XOR_Array solver = new B2175_XOR_Array();

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int l = in.nextInt();
            int r = in.nextInt();

            solver.solve(n, l, r);
        }

        out.flush();
    }

    private void solve(int n, int l, int r) {
        int[] p = new int[n+1];

        for(int i=0; i<=n; i++) {
            p[i] = i;
        }

        p[r] = p[l-1];

        // out.println(Arrays.toString(p));
        for(int i=1; i<=n; i++) {
            int val = p[i] ^ p[i-1];
            out.print(val + " ");
        }
        out.println();
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
