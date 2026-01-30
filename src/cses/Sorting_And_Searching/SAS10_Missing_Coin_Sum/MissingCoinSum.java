package cses.Sorting_And_Searching.SAS10_Missing_Coin_Sum;

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
/*
https://cses.fi/problemset/task/2183

You have n coins with positive integer values. 
What is the smallest sum you cannot create using a subset of the coins?
*/

/**
 * MissingCoinSum solves the minimum sum that cannot be created using coin subsets.
 * 
 * This class implements a greedy algorithm to find the smallest positive integer sum
 * that cannot be formed by any subset of the given coins.
 * 
 * Algorithm:
 * 1. Sort coins in ascending order
 * 2. Track the maximum reachable sum (currentReach)
 * 3. For each coin, if it exceeds currentReach, we found the answer
 * 4. Otherwise, add the coin to currentReach to extend the range of possible sums
 * 
 * Time Complexity: O(n log n) due to sorting
 * Space Complexity: O(1) excluding input
 * 
 * @author ShyamKawale
 * @version 1.0
 */
public class MissingCoinSum {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Sorting_And_Searching/SAS10_Missing_Coin_Sum/input.txt")
                : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Sorting_And_Searching/SAS10_Missing_Coin_Sum/output.txt")
                : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        MissingCoinSum solver = new MissingCoinSum();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int[] coins = new int[n];
            for(int i=0; i<n; i++) {
                coins[i] = in.nextInt();
            }
            solver.solve(n, coins);
        }

        out.flush();
    }

    private void solve(int n, int[] coins) {
        Arrays.sort(coins);
        
        long currentReach = 1;

        for(int coin: coins) {
            if(coin > currentReach) {
                break;
            }

            currentReach = currentReach + coin;
        }

        out.println(currentReach);
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
