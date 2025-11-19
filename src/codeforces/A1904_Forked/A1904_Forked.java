package codeforces.A1904_Forked;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class A1904_Forked {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/codeforces/A1904_Forked/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/codeforces/A1904_Forked/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        A1904_Forked solver = new A1904_Forked();

        int t = in.nextInt();
        while (t-- > 0) {
            long a = in.nextLong();
            long b = in.nextLong();
            long xk = in.nextLong();
            long yk = in.nextLong();
            long xq = in.nextLong();
            long yq = in.nextLong();

            solver.solve(a, b, xk, yk, xq, yq);
        }

        out.flush();
    }

    private void solve(long a, long b, long xk, long yk, long xq, long yq) {
        
        long[][] dir = new long[][]{
            {+a, +b}, {+a, -b}, {-a, +b}, {-a, -b}, 
            {+b, +a}, {+b, -a}, {-b, +a}, {-b, -a}};

            HashSet<Long> king = new HashSet<>();
            for (int i = 0; i < 8; i++) {
                long nxk = xk + dir[i][0];
                long nyk = yk + dir[i][1];
                king.add(encode(nxk, nyk));
            }

            HashSet<Long> queen = new HashSet<>();
            for (int i = 0; i < 8; i++) {
                long nxq = xq + dir[i][0];
                long nyq = yq + dir[i][1];
                queen.add(encode(nxq, nyq));
            }

            // count intersection (iterate smaller set)
            int cnt = 0;
            if (king.size() < queen.size()) {
                for (long p : king) {
                    if (queen.contains(p)) cnt++;
                }
            } else {
                for (long p : queen) {
                    if (king.contains(p)) cnt++;
                }
            }

        out.println(cnt);
    }

    static long encode(long x, long y) {
        return (x << 32) ^ (y & 0xffffffffL);
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
