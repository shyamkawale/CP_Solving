package codeforces.problems.B2184_Hourglass;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class B2184_Hourglass {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/codeforces/problems/B2184_Hourglass/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/codeforces/problems/B2184_Hourglass/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        B2184_Hourglass solver = new B2184_Hourglass();

        int t = in.nextInt();
        while (t-- > 0) {
            int s = in.nextInt();
            int k = in.nextInt();
            int m = in.nextInt();

            solver.solve(s, k, m);
        }

        out.flush();
    }

    private void solve(int s, int k, int m) {
        int aboveRem = 0;

        if(k < s) {
            if((m/k) % 2 != 0) {
                aboveRem = k;
            }
            else {
                aboveRem = s;
            }
        }
        else {
            aboveRem = s;
        }

        int timeRem = m%k;

        int sandAfterHeLefts = aboveRem - timeRem;

        if(sandAfterHeLefts < 0) {
            out.println(0);
        }
        else {
            out.println(sandAfterHeLefts);
        }
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
