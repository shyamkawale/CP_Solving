package cses.Sorting_And_Searching.SAS35_Maximum_Subarray_Sum_II;

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
https://cses.fi/problemset/task/1644

Given an array of n integers, 
your task is to find the maximum sum of values in a contiguous subarray with length between a and b.

Input
The first input line has three integers n, a and b: the size of the array and the minimum and maximum subarray length.
The second line has n integers x1,x2,....,xn: the array values.

Output
Print one integer: the maximum subarray sum.
*/
public class MaximumSubarraySumII {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL
                ? new FileInputStream("src/cses/Sorting_And_Searching/SAS35_Maximum_Subarray_Sum_II/input.txt")
                : System.in;
        outputStream = LOCAL
                ? new FileOutputStream("src/cses/Sorting_And_Searching/SAS35_Maximum_Subarray_Sum_II/output.txt")
                : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        MaximumSubarraySumII solver = new MaximumSubarraySumII();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            solver.solve(n);
        }

        out.flush();
    }

    private void solve(int n) {

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
