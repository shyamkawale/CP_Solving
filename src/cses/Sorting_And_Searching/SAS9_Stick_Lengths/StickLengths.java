package cses.Sorting_And_Searching.SAS9_Stick_Lengths;

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
https://cses.fi/problemset/task/1074

There are n sticks with some lengths. 
Your task is to modify the sticks so that each stick has the same length.

You can either lengthen and shorten each stick. 
Both operations cost x where x is the difference between the new and original length.

What is the minimum total cost?
*/

/**
 * StickLengths solves the minimum effort problem to make all sticks equal length.
 * 
 * This class implements a greedy algorithm to find the minimum total effort required
 * to make all sticks the same length by adjusting them to a target height.
 * 
 * Algorithm:
 * 1. Sort all stick lengths in ascending order
 * 2. Select the median stick length as the target height
 * 3. Calculate the total effort as the sum of absolute differences between each stick
 *    and the target height
 * 4. The median minimizes the sum of absolute deviations, making it the optimal choice
 * 
 * Time Complexity: O(n log n) due to sorting
 * Space Complexity: O(n) for storing stick lengths
 * 
 * @author ShyamKawale
 * @version 1.0
 */
public class StickLengths {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Sorting_And_Searching/SAS9_Stick_Lengths/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Sorting_And_Searching/SAS9_Stick_Lengths/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        StickLengths solver = new StickLengths();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int[] sticks = new int[n];
            for (int i = 0; i < n; i++) {
                sticks[i] = in.nextInt();
            }
            solver.solve(n, sticks);
        }

        out.flush();
    }

    private void solve(int n, int[] sticks) {
        Arrays.sort(sticks);

        long effort = 0;
        int middleStickHeight = sticks[n / 2];
        for (int i = 0; i < n; i++) {
            effort = effort + Math.abs(sticks[i] - middleStickHeight);
        }

        out.println(effort);
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
