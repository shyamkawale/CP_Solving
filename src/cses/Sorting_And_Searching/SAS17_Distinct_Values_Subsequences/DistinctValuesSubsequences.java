package cses.Sorting_And_Searching.SAS17_Distinct_Values_Subsequences;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/*
https://cses.fi/problemset/task/3421

Given an array of n integers, 
count the number of subsequences where each element is distinct.

A subsequence is a sequence of array elements from left to right that may have gaps.
*/

/**
 * DistinctValuesSubsequences solves the problem of counting distinct subsequences
 * from a given array of integers.
 * 
 * This class implements two methods to calculate the number of distinct subsequences:
 * 1. `solve2`: Uses a mathematical approach based on frequency of elements to compute
 *    the total number of distinct subsequences, excluding the empty subsequence.
 * 2. `solve1`: Utilizes a recursive backtracking approach to count distinct subsequences
 *    by maintaining a set of selected elements to avoid duplicates.
 * 
 * Time Complexity:
 * - solve2: O(n)
 * - solve1: O(2^n) in the worst case due to recursive exploration of subsequences
 * 
 * Space Complexity:
 * - solve2: O(m) for storing frequencies
 * - solve1: O(n) for the recursion stack and the set of selected elements
 * 
 * @author ShyamKawale
 * @version 1.0
 */
public class DistinctValuesSubsequences {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Sorting_And_Searching/SAS17_Distinct_Values_Subsequences/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Sorting_And_Searching/SAS17_Distinct_Values_Subsequences/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        DistinctValuesSubsequences solver = new DistinctValuesSubsequences();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int[] nums = new int[n];
            for(int i=0; i<n; i++) {
                nums[i] = in.nextInt();
            }
            solver.solve2(n, nums);
            solver.solve1(n, nums);
        }

        out.flush();
    }

    /**
     * B. Combinatorial / Frequency Map (Large N)
     * Used when N is large (e.g. 100,000) and the "distinct" constraint relies only on values, not positions.
     * Calculates the number of distinct subsequences using a frequency-based approach.
     * 
     * This method is optimal for large arrays where the "distinct" constraint depends
     * only on unique values, not their positions.
     * 
     * Key Insight: For each unique integer appearing 'k' times, we have (k+1) choices:
     * pick any one of its k occurrences, or pick none at all.
     * 
     * Formula: Total = (Product of all (freq(val) + 1)) - 1
     * 
     * The subtraction of 1 excludes the empty subsequence.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(m) where m is the number of unique values
     */
    private void solve2(int n, int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        long ans = 1;
        long MOD = 1_000_000_007;

        for (int count : freq.values()) {
            // (count + 1) represents picking any one of the occurrences or picking none
            ans = (ans * (count + 1)) % MOD;
        }

        // Subtract the empty subsequence case
        out.println(ans - 1);
    }

    /*
    * ** Recursive Backtracking (Small N) **
    *
    * Used when N <= 20 or when constraints are complex (e.g., adjacent elements must sum to prime).
    * 
    * • Logic: Explore all subsets using recursion.
    *
    * • Key Fixes:
    *
    * 1. Base case returns `1` (representing the empty set found).
    * 2. Sum the branches (`pick + notPick`) instead of adding `+1`.
    */
    private void solve1(int n, int[] nums) {
        int cnt = helper(0, nums);
        out.println(cnt);
    }

    Set<Integer> set = new HashSet<Integer>();
    private int helper(int n, int[] nums) {
        if(n >= nums.length) {
            return 0; // as it is invalid index
        }

        int pick = 0;
        // pick only if this element is not selected yet..
        if(!set.contains(nums[n])) {
            set.add(nums[n]);
            pick = helper(n+1, nums) + 1; // add one as we have selected
            set.remove(nums[n]); // Backtrack: remove that element from set
        }

        int notPick = helper(n+1, nums) + 0; // add zero as we have not selected..

        return pick + notPick;
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
