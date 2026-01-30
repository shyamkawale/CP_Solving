package cses.Sorting_And_Searching.SAS6_Movie_Festival;

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
https://cses.fi/problemset/task/1629

In a movie festival n movies will be shown. You know the starting and ending time of each movie. 
What is the maximum number of movies you can watch entirely?
*/

/**
 * MovieFestival solves the maximum number of non-overlapping intervals problem.
 * 
 * This class implements a greedy algorithm to find the maximum number of movie intervals
 * that can be attended without any overlap (also known as the Activity Selection Problem
 * or Interval Scheduling Maximization Problem).
 * 
 * Algorithm:
 * 1. Sort all intervals by their end time (and by start time as a tiebreaker)
 * 2. Greedily select intervals: pick an interval if its start time is >= the end time
 *    of the last selected interval
 * 3. This greedy approach guarantees the maximum number of non-overlapping intervals
 * 
 * Time Complexity: O(n log n) due to sorting
 * Space Complexity: O(n) for storing intervals
 * 
 * @author ShyamKawale
 * @version 1.0
 */
public class MovieFestival {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Sorting_And_Searching/SAS6_Movie_Festival/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Sorting_And_Searching/SAS6_Movie_Festival/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        MovieFestival solver = new MovieFestival();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int[][] intervals = new int[n][2];
            for (int i = 0; i < n; i++) {
                intervals[i][0] = in.nextInt();
                intervals[i][1] = in.nextInt();
            }
            solver.solve(n, intervals);
        }

        out.flush();
    }

    // maximum continuos intervals
    private void solve(int n, int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] != b[1]) return a[1] - b[1];
            return a[0] - b[0];
        });

        int cnt = 0;
        int lastEnd = -1;
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            if (start >= lastEnd) {
                cnt++;
                lastEnd = end;
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
