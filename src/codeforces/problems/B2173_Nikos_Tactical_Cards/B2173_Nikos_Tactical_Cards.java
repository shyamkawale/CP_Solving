package codeforces.problems.B2173_Nikos_Tactical_Cards;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class B2173_Nikos_Tactical_Cards {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/codeforces/problems/B2173_Nikos_Tactical_Cards/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/codeforces/problems/B2173_Nikos_Tactical_Cards/output.txt")
                : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        B2173_Nikos_Tactical_Cards solver = new B2173_Nikos_Tactical_Cards();

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            int[] b = new int[n];
            for (int i = 0; i < n; i++) {
                b[i] = in.nextInt();
            }

            solver.solve(n, a, b);
        }

        out.flush();
    }

    private void solve(int n, int[] a, int[] b) {
        Long mini = Math.min((long)-a[0], (long)b[0]);
        Long maxi = Math.max((long)-a[0], (long)b[0]);
        for(int i=1; i<n; i++) {
            long aMax = maxi-(long)a[i];
            long aMin = mini-(long)a[i];
            long bMax = (long)b[i]-mini;
            long bMin = (long)b[i]-maxi;
            mini = Math.min(aMin, bMin);
            maxi = Math.max(aMax, bMax);
        }

        out.println(maxi);
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
