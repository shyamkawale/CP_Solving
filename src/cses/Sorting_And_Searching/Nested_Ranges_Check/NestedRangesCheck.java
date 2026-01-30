package cses.Sorting_And_Searching.Nested_Ranges_Check;

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
https://cses.fi/problemset/task/2168

Given n ranges, your task is to determine for each range if it contains some other range and if some other range contains it.
Range [a,b] contains range [c,d] if a <= c and d <= b.
*/
public class NestedRangesCheck {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Nested_Ranges_Check/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Nested_Ranges_Check/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        NestedRangesCheck solver = new NestedRangesCheck();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int[][] intervals = new int[n][2];
            for(int i=0; i<n; i++) {
                intervals[i][0] = in.nextInt();
                intervals[i][1] = in.nextInt();
            }
            solver.solve(n, intervals);
        }

        out.flush();
    }

    private void solve(int n, int[][] intervals) {
        int[][] arr = new int[n][3];
        for(int i=0; i<n; i++) {
            arr[i][0] = intervals[i][0];
            arr[i][1] = intervals[i][1];
            arr[i][2] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            if(a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(b[1], a[1]);
        });

        int[] contains = new int[n];
        // Sweep right -> left: find intervals that contain another
        int minRight = Integer.MAX_VALUE;
        for (int i=n-1; i>=0; i--) {
            if (arr[i][1] >= minRight) 
                contains[arr[i][2]] = 1;
            minRight = Math.min(minRight, arr[i][1]);
        }

        int[] containedBy = new int[n];
        // Sweep left -> right: find intervals contained by a previous one
        int maxRight = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i][1] <= maxRight)
                containedBy[arr[i][2]] = 1;
            maxRight = Math.max(maxRight, arr[i][1]);
        }

        for(int idx: contains) {
            out.print(idx + " ");
        }
        out.println();
        for(int idx: containedBy) {
            out.print(idx + " ");
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
