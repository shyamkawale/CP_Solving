package codeforces.problems.B1797_Li_Hua_and_Pattern;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class B1797_Li_Hua_and_Pattern {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/codeforces/problems/B1797_Li_Hua_and_Pattern/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/codeforces/problems/B1797_Li_Hua_and_Pattern/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        B1797_Li_Hua_and_Pattern solver = new B1797_Li_Hua_and_Pattern();

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[][] mat = new int[n][n];
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    mat[r][c] = in.nextInt();
                }
            }

            solver.solve(n, k, mat);
        }

        out.flush();
    }

    private void solve(int n, int k, int[][] mat) {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int nr = n-1-r;
                int nc = n-1-c;
                if(r<nr || (r==nr && c<nc)) {
                    if(mat[r][c] != mat[nr][nc]) {
                        k--;
                        if(k < 0) {
                            out.println("NO");
                            return;
                        }
                    }
                }
            }
        }

        
        if(n % 2 != 0 || k % 2 == 0) out.println("YES");
        else out.println("NO");
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
