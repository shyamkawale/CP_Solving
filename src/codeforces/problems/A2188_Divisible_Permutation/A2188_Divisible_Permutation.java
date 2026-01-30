package codeforces.problems.A2188_Divisible_Permutation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class A2188_Divisible_Permutation {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/codeforces/problems/A2188_Divisible_Permutation/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/codeforces/problems/A2188_Divisible_Permutation/output.txt")
                : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        A2188_Divisible_Permutation solver = new A2188_Divisible_Permutation();

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();

            solver.solve(n);
        }

        out.flush();
    }

    private void solve(int n) {
        int[] nums = new int[n+1];

        nums[n] = 1;

        for(int i=n-1; i>=1; i--) {
            if((n-i)%2 == 0) {
                nums[i] = nums[i+1] - i;
            }
            else {
                nums[i] = nums[i+1] + i;
            }
        }

        for(int i=1; i<=n; i++) {
            out.print(nums[i] + " ");
        }

        out.println();
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
