package cses.Sorting_And_Searching.SAS7_Sum_of_Two_Values;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
https://cses.fi/problemset/task/1640

You are given an array of n integers,
find two values (at distinct positions) whose sum is target.
*/


/**
 * SumOfTwoValues solves the two-sum problem: finding two array elements that sum to a target value.
 * 
 * This class implements two different approaches to find a pair of numbers in an array
 * whose sum equals a given target value.
 * 
 * Approaches:
 * 1. Sorting + Two-Pointer: Sort the array while tracking original indices, then use
 *    two pointers converging from both ends to find the pair.
 * 2. HashMap (Two-Sum Technique): Use a hash map to store visited elements and their
 *    indices, then for each element check if (target - element) exists in the map.
 * 
 * Time Complexity:
 * - solve_twopointer: O(n log n) due to sorting
 * - solve_map: O(n) with hash map lookup
 * 
 * Space Complexity:
 * - solve_twopointer: O(n) for the 2D array
 * - solve_map: O(n) for the hash map
 * 
 * The class also includes a FastScanner utility for efficient input reading from files
 * or standard input streams.
 * 
 * @author ShyamKawale
 * @version 1.0
 */
public class SumOfTwoValues {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Sorting_And_Searching/SAS7_Sum_of_Two_Values/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Sorting_And_Searching/SAS7_Sum_of_Two_Values/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        SumOfTwoValues solver = new SumOfTwoValues();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int target = in.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = in.nextInt();
            }
            solver.solve_twopointer(n, target, nums);
            solver.solve_map(n, target, nums);
        }

        out.flush();
    }

    /*
       ** SORTING + TWO-POINTER **
    */
    private void solve_twopointer(int n, int target, int[] nums) {
        int[][] arr = new int[n][2]; // [value, originalIndex]

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i + 1; // 1-indexed
        }

        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        int start = 0;
        int end = n - 1;

        while (start < end) {
            int sum = arr[start][0] + arr[end][0];
            if (sum == target) {
                out.println(arr[start][1] + " " + arr[end][1]);
                return;
            } else if (sum > target) {
                end--;
            } else {
                start++;
            }
        }

        out.println("IMPOSSIBLE");
    }

    /*
       ** 2 SUM Technique - Pick 1 element then find other(target-first) **
    */
    private void solve_map(int n, int target, int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (map.containsKey(target - nums[i])) {
                out.println((map.get(target - nums[i]) + 1) + " " + (i + 1));
                return;
            }

            map.putIfAbsent(nums[i], i);
        }

        out.println("IMPOSSIBLE");
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
