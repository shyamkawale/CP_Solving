package cses.Sorting_And_Searching.SAS8_Maximum_Subarray_Sum;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
https://cses.fi/problemset/task/1643

Given an array of n integers,
find the maximum sum of values in a contiguous, nonempty subarray.

[Subarray with maximum sum]
*/
/**
 * MaximumSubarraySum solves the maximum subarray sum problem using two different approaches.
 * 
 * This class implements algorithms to find the contiguous subarray with the largest sum
 * within a given array of integers (which may contain negative numbers).
 * 
 * Algorithms:
 * 1. Prefix Sum Approach (solve1): Uses prefix sums with minimum prefix tracking to find
 *    the maximum subarray sum in a single pass. The idea is that the maximum sum ending
 *    at position i is the prefix sum at i minus the minimum prefix sum seen so far.
 *    
 * 2. Kadane's Algorithm (solve2): A dynamic programming approach that maintains the maximum
 *    sum ending at the current position. If the sum becomes negative, it starts fresh from
 *    the current element (as a negative sum would only decrease future sums).
 * 
 * Both approaches solve the classic Maximum Subarray Problem efficiently.
 * 
 * Time Complexity: O(n) for both algorithms
 * Space Complexity: O(1) auxiliary space (excluding input array)
 * 
 * @author ShyamKawale
 * @version 1.0
 */
@SuppressWarnings("unused")
public class MaximumSubarraySum {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Sorting_And_Searching/SAS8_Maximum_Subarray_Sum/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Sorting_And_Searching/SAS8_Maximum_Subarray_Sum/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        MaximumSubarraySum solver = new MaximumSubarraySum();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = in.nextInt();
            }
            solver.solve1(n, nums);
        }

        out.flush();
    }

    /*
        ** Prefix Sum **
    */
    private void solve1(int n, int[] nums) {
        long minPs = 0;
        long maxSum = Long.MIN_VALUE;

        long ps = 0;
        for (int i = 0; i < n; i++) {
            ps = ps + nums[i];

            maxSum = Math.max(maxSum, ps - minPs);

            minPs = Math.min(minPs, ps);
        }

        out.println(maxSum);
    }

    /*
        ** KADANE's ALGORITHM **
    */
    private void solve2(int n, int[] nums) {
        long maxSum = Long.MIN_VALUE;
        long sum = 0L;

        for (int i = 0; i < n; i++) {
            if(sum < 0) {
                sum = nums[i];
            }
            else {
                sum = sum + nums[i];
            }

            maxSum = Math.max(maxSum, sum);
        }

        out.println(maxSum);
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
