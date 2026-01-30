package cses.Subarray_Sums_II;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SubarraySumsII {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Subarray_Sums_II/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Subarray_Sums_II/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        SubarraySumsII solver = new SubarraySumsII();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int target = in.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = in.nextInt();
            }
            solver.solve(n, target, nums);
        }

        out.flush();
    }

    private void solve(int n, int target, int[] nums) {
        Map<Long, Long> freqMap = new HashMap<>();

        long ps = 0L;
        freqMap.put(ps, 1L);

        long count = 0;
        for(int i=0; i<n; i++) {
            ps = ps + nums[i];
            
            count = count + freqMap.getOrDefault(ps-target, 0L);

            freqMap.put(ps, freqMap.getOrDefault(ps, 0L)+1);
        }

        out.println(count);
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
