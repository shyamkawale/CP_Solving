package codeforces.contests.Contest_2197_11022026.A2197_Friendly_Numbers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class A2197_Friendly_Numbers {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL
                ? new FileInputStream("src/codeforces/contests/Contest_2197_11022026/A2197_Friendly_Numbers/input.txt")
                : System.in;
        outputStream = LOCAL
                ? new FileOutputStream("src/codeforces/contests/Contest_2197_11022026/A2197_Friendly_Numbers/output.txt")
                : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        A2197_Friendly_Numbers solver = new A2197_Friendly_Numbers();

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            solver.solve(n);
        }

        out.flush();
    }

    private void solve(int x) {
        int cnt = 0;
        for (int y = x; y <= x + 100; y++) {
            if(y - countDigitSum(y) == x) {
                cnt++;
            }
        }

        out.println(cnt);
    }

    private int countDigitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum = sum + n % 10;
            n = n / 10;
        }
        return sum;
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
