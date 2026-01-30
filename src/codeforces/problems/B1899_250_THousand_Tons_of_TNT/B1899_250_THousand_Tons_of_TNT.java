package codeforces.problems.B1899_250_THousand_Tons_of_TNT;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class B1899_250_THousand_Tons_of_TNT {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/codeforces/problems/B1899_250_THousand_Tons_of_TNT/input.txt")
                : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/codeforces/problems/B1899_250_THousand_Tons_of_TNT/output.txt")
                : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        B1899_250_THousand_Tons_of_TNT solver = new B1899_250_THousand_Tons_of_TNT();

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            long[] weights = new long[n];
            for (int i = 0; i < n; i++) {
                weights[i] = in.nextLong();
            }

            solver.solve(n, weights);
        }

        out.flush();
    }

    // O(n) + O(factors*n)
    private void solve(int n, long[] weights) {
        long[] ps = new long[n+1];
        ps[0] = 0;
        for(int i=0; i<n; i++) {
            ps[i+1] = ps[i] + weights[i];
        }

        long ans = 0;
        for(int hop=1; hop<n; hop++) {
            if(n%hop != 0) continue;

            long max = Long.MIN_VALUE;
            long min = Long.MAX_VALUE;
            for(int idx=hop; idx<n+1; idx=idx+hop) {
                long diff = ps[idx] - ps[idx-hop];
                if(diff > max) max = diff;
                if(diff < min) min = diff;
                ans = Math.max(ans, Math.abs(max-min));
            }
        }

        out.println(ans);
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
