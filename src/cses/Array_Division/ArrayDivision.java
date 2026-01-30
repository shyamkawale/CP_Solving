package cses.Array_Division;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ArrayDivision {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Array_Division/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Array_Division/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        ArrayDivision solver = new ArrayDivision();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] nums = new int[n];
            for(int i=0; i<n; i++) {
                nums[i] = in.nextInt();
            }
            solver.solve(n, k, nums);
        }

        out.flush();
    }

    // divide the array into k subarrays so that the maximum sum in a subarray is as small as possible.
    private void solve(int n, int k, int[] nums) {
        long sum = 0L;
        int maxi = 0;
        for(int num: nums) {
            maxi = Math.max(maxi, num);
            sum = sum + num;
        }

        long left = maxi;
        long right = sum;
        long ans = -1;

        while(left <= right) {
            long mid = left + (right-left)/2;

            if(checkIfPossible(n, mid, k, nums)) {
                ans = mid;
                right = mid-1;
            }
            else {
                left = mid+1;
            }
        }

        out.println(ans);
    }

    private boolean checkIfPossible(int n, long maxSum, int k, int[] nums) {
        int cntSubarray = 0;
        long sum = 0;

        for(int i=0; i<n; i++) {        
            sum = sum + nums[i];

            if(sum > maxSum) {
                sum = nums[i];
                cntSubarray++;
            }
        }

        cntSubarray++;
        return cntSubarray <= k;
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
