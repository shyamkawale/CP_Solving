package cses.Sorting_And_Searching.SAS27_Sum_of_Four_Values;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
https://cses.fi/problemset/task/1642

You are given an array of n integers, 
and your task is to find four values (at distinct positions) whose sum is x.

Input
The first input line has two integers n and x: the array size and the target sum.
The second line has n integers a1,a2,....,an: the array values.

Output
Print four integers: the positions of the values. 
If there are several solutions, you may print any of them. 
If there are no solutions, print IMPOSSIBLE.
*/
public class SumofFourValues {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Sorting_And_Searching/SAS27_Sum_of_Four_Values/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Sorting_And_Searching/SAS27_Sum_of_Four_Values/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        SumofFourValues solver = new SumofFourValues();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int target = in.nextInt();
            int[] nums = new int[n];
            for(int i=0; i<n; i++) {
                nums[i] = in.nextInt();
            }
            solver.solve(n, target, nums);
        }

        out.flush();
    }

    private void solve(int n, int target, int[] nums) {
        // store value + original index
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i; // store original index
        }

        // sort by value
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

        for(int k=0; k<n; k++) {
            for (int i = k+1; i < n; i++) {
                int need = target - arr[k][0] - arr[i][0];

                int left = i + 1;
                int right = n - 1;

                while (left < right) {
                    int sum = arr[left][0] + arr[right][0];

                    if (sum == need) {
                        // print original indices +1
                        out.println((arr[k][1] + 1) + " " + (arr[i][1] + 1) + " " + (arr[left][1] + 1) + " " +(arr[right][1] + 1));
                        return;
                    }

                    if (sum < need)
                        left++;
                    else
                        right--;
                }
            }
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
