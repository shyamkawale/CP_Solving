package codeforces.problems.A2028_Alices_Adventures_in_Chess;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class A2028_Alices_Adventures_in_Chess_Prob {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/codeforces/problems/A2028_Alices_Adventures_in_Chess/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/codeforces/problems/A2028_Alices_Adventures_in_Chess/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        A2028_Alices_Adventures_in_Chess_Prob solver = new A2028_Alices_Adventures_in_Chess_Prob();

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();
            String s = in.next();

            solver.solve(n, a, b, s);
        }

        out.flush();
    }

    private void solve(int n, int a, int b, String s) {
        int currX = 0;
        int currY = 0;

        if (currX == a && currY == b) {
            out.println("YES");
            return;
        }

        for (int i = 0; i < 100; i++) {
            for (Character ch : s.toCharArray()) {
                if (ch == 'N')
                    currY++;
                else if (ch == 'E')
                    currX++;
                else if (ch == 'S')
                    currY--;
                else if (ch == 'W')
                    currX--;

                if (currX == a && currY == b) {
                    out.println("YES");
                    return;
                }
            }
        }

        out.println("NO");
        return;
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