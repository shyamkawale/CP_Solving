package codeforces.B1808_Playing_in_a_Casino;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1808_Playing_in_a_Casino {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/codeforces/B1808_Playing_in_a_Casino/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/codeforces/B1808_Playing_in_a_Casino/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        B1808_Playing_in_a_Casino solver = new B1808_Playing_in_a_Casino();

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            long[][] mat = new long[m][n];
            for (int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    mat[j][i] = in.nextLong();
                }
            }

            solver.solve(n, m, mat);
        }

        out.flush();
    }

    private void solve(int n, int m, long[][] mat) {
        long sum = 0;

        for(int i=0; i<m; i++) {
            Arrays.sort(mat[i]);
        }

        for(int i=0; i<m; i++) {
            int k = 1;
            for(int j=n-1; j>=0; j--) {
                sum = sum + (n-k)*mat[i][j];
                k=k+2;
            }
        }

        out.println(sum);
    }

    // TLE solution
    // public static void main(String[] args) throws Exception {
    //     inputStream = LOCAL ? new FileInputStream("src/codeforces/B1808_Playing_in_a_Casino/input.txt") : System.in;
    //     outputStream = LOCAL ? new FileOutputStream("src/codeforces/B1808_Playing_in_a_Casino/output.txt") : System.out;
    //     in = new FastScanner(inputStream);
    //     out = new PrintWriter(outputStream);
    //     B1808_Playing_in_a_Casino solver = new B1808_Playing_in_a_Casino();
 
    //     int t = in.nextInt();
    //     while (t-- > 0) {
    //         int n = in.nextInt();
    //         int m = in.nextInt();
    //         int[][] mat = new int[n][m];
    //         for (int i = 0; i < n; i++) {
    //             for(int j = 0; j < m; j++) {
    //                 mat[i][j] = in.nextInt();
    //             }
    //         }
 
    //         solver.solve(n, m, mat);
    //     }
 
    //     out.flush();
    // }
 
    // private void solve(int n, int m, int[][] mat) {
    //     int sum = 0;
 
    //     for(int p1=0; p1<n; p1++) {
    //         for(int p2=p1+1; p2<n; p2++) {
    //             for(int card=0; card<m; card++) {
    //                 sum = sum + Math.abs(mat[p1][card] - mat[p2][card]);
    //             }
    //         }
    //     }
 
    //     out.println(sum);
    // }

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
