package cses.Sliding_Window.Sliding_Window_Sum;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SlidingWindowSum {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Sliding_Window/Sliding_Window_Sum/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Sliding_Window/Sliding_Window_Sum/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        SlidingWindowSum solver = new SlidingWindowSum();

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
            for(int i=1; i<n; i++) {
                nums[i] = (int)(((long)a*nums[i-1]+b) % c);
            }
            solver.solve(n, k, nums);
        }

        out.flush();
    }

    // x[0]=x
    // x[i]=(a*x[i-1]+b) mod c for i=2,3,..,n
    private void solve(int n, int k, int[] nums) {
        long sum = 0;
        long totalSum = 0;
        int start = 0;
        int end = 0;

        while(end < n) {
            sum = sum + nums[end];
            if(end-start+1 < k) {
                end++;
            }
            else {
                totalSum = totalSum ^ sum;
                sum = sum - nums[start];
                start++;
                end++;
            }
        }

        out.println(totalSum);
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
