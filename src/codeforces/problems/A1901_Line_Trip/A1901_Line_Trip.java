package codeforces.problems.A1901_Line_Trip;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class A1901_Line_Trip {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/codeforces/problems/A1901_Line_Trip/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/codeforces/problems/A1901_Line_Trip/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        A1901_Line_Trip solver = new A1901_Line_Trip();

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int x = in.nextInt();
            int[] gs = new int[n];
            for (int i = 0; i < n; i++) {
                gs[i] = in.nextInt();
            }

            solver.solve(n, x, gs);
        }

        out.flush();
    }

    private void solve(int n, int x, int[] gs) {
        int startGsDist = gs[0];
        int endGsDist = x-gs[n-1];

        int maxDistBetGs = Math.max(startGsDist, 2*endGsDist);
        for(int i=1; i<n; i++) {
            maxDistBetGs = Math.max(maxDistBetGs, gs[i] - gs[i-1]);
        }

        out.println(maxDistBetGs);
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
