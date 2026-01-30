package cses.Sliding_Window.Sliding_Window_Or;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SlidingWindowOr {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Sliding_Window/Sliding_Window_Or/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Sliding_Window/Sliding_Window_Or/output.txt")
                : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        SlidingWindowOr solver = new SlidingWindowOr();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int x = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            int[] nums = new int[n];
            nums[0] = x;
            for (int i = 1; i < n; i++) {
                nums[i] = (int) (((long) a * nums[i - 1] + b) % c);
            }
            solver.solve(n, k, nums);
        }

        out.flush();
    }

    private void solve(int n, int k, int[] nums) {
        
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
