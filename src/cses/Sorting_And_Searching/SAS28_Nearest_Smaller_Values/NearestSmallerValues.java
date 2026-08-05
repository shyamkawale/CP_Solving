package cses.Sorting_And_Searching.SAS28_Nearest_Smaller_Values;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
https://cses.fi/problemset/task/1645

Given an array of n integers, 
your task is to find for each array position the nearest position to its left having a smaller value.

Input
The first input line has an integer n: the size of the array.
The second line has n integers x1,x2,...,xn: the array values.

Output
Print n integers: for each array position the nearest position with a smaller value. 
If there is no such position, print 0.
*/
public class NearestSmallerValues {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Sorting_And_Searching/SAS28_Nearest_Smaller_Values/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Sorting_And_Searching/SAS28_Nearest_Smaller_Values/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        NearestSmallerValues solver = new NearestSmallerValues();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int[] nums = new int[n];
            for(int i=0; i<n; i++) {
                nums[i] = in.nextInt();
            }
            solver.solve(n, nums);
        }

        out.flush();
    }

    private void solve(int n, int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i=0; i<n; i++) {
            while(!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }

            int res = stack.isEmpty() ? 0 : (stack.peek()+1);
            out.print(res + " ");
            stack.push(i);
        }
        out.println();
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
