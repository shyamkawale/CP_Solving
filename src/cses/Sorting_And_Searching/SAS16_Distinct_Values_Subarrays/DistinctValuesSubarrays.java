package cses.Sorting_And_Searching.SAS16_Distinct_Values_Subarrays;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
https://cses.fi/problemset/task/3420

Given an array of n integers, 
count the number of subarrays where each element is distinct.

Sliding Window
*/
public class DistinctValuesSubarrays {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Sorting_And_Searching/SAS16_Distinct_Values_Subarrays/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Sorting_And_Searching/SAS16_Distinct_Values_Subarrays/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        DistinctValuesSubarrays solver = new DistinctValuesSubarrays();

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
        int start = 0;
        int end = 0;
        long cnt = 0;
        Set<Integer> set = new HashSet<>();

        while(end < n) {
            if(set.contains(nums[end])) {
                while(set.contains(nums[end])) {
                    set.remove(nums[start]);
                    start++;
                }
            }
            else {
                set.add(nums[end]);
                cnt = cnt + (end-start+1);
                end++;
            }
        }

        out.println(cnt);
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
