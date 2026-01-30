package codeforces.problems.C2184_Huge_Pile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class C2184_Huge_Pile {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/codeforces/problems/C2184_Huge_Pile/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/codeforces/problems/C2184_Huge_Pile/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        C2184_Huge_Pile solver = new C2184_Huge_Pile();

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();

            solver.solve(n, k);
        }

        out.flush();
    }

    private void solve(int n, int k) {
        int cnt = 0;

        if(n == k) {
            out.println(cnt);
            return;
        }
        
        while(n > 1) {
            int ceil = Math.ceilDiv(n, 2);
            int floor = Math.floorDiv(n, 2);

            cnt++;

            if(ceil == k || floor == k) {
                out.println(cnt);
                return;
            }

            if(ceil == floor || ceil%2 != 0) {
                n = ceil;
            }
            else {
                n = floor;
            }
        }

        out.println(-1);
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
