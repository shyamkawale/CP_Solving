package cses.Sorting_And_Searching.SAS5_Restaurant_Customers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
https://cses.fi/problemset/task/1619

You are given the arrival and leaving times of n customers in a restaurant.
What was the maximum number of customers in the restaurant at any time?
*/

/**
 * RestaurantCustomers solves the maximum number of overlapping intervals problem.
 * 
 * This class implements an event-based algorithm to find the maximum number of customers
 * present in a restaurant at any point in time. Each customer has an arrival and departure
 * time, and the goal is to determine the peak occupancy.
 * 
 * Algorithm:
 * 1. Create events for each customer: arrival (+1) and departure (-1)
 * 2. Sort all events by time (ascending). If times are equal, process arrivals before departures
 *    to correctly count simultaneous arrival and departure as overlapping
 * 3. Iterate through sorted events, maintaining a running count of current customers
 * 4. Track the maximum count encountered during iteration
 * 
 * This approach correctly handles edge cases where a customer arrives exactly when another
 * departs at the same time, counting both as overlapping.
 * 
 * Time Complexity: O(n log n) due to sorting events
 * Space Complexity: O(n) for storing events list
 * 
 * Note: The solve_flaws() method demonstrates an incorrect approach and is kept for reference.
 *       Use the solve() method for correct results.
 * 
 * @author ShyamKawale
 * @version 1.0
 */
@SuppressWarnings("unused")
public class RestaurantCustomers {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Sorting_And_Searching/SAS5_Restaurant_Customers/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Sorting_And_Searching/SAS5_Restaurant_Customers/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        RestaurantCustomers solver = new RestaurantCustomers();

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

    // maximum overlapping intervals
    private void solve(int n, int[][] intervals) {
        List<int[]> events = new ArrayList<>(2 * n);

        for (int i = 0; i < n; i++) {
            events.add(new int[]{intervals[i][0], +1});  // arrival
            events.add(new int[]{intervals[i][1], -1});  // leaving
        }

        Collections.sort(events, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0]; // Sort by time ascending

            // If times are equal, process arrival (+1) before leaving (-1)
            // This maximizes the overlap count (i.e if person1 leaves and person2 comes at time t then both will be counted)
            // +1 should come before -1. In descending order of type: 1 > -1.
            return b[1] - a[1];
        });

        int currCustomers = 0;
        int maxCustomers = 0;
        for (int[] evt : events) {
            currCustomers = currCustomers + evt[1];
            maxCustomers = Math.max(maxCustomers, currCustomers);
        }

        out.println(maxCustomers);
    }


    // This solution has flaws!! (https://gemini.google.com/share/8395d11c2263)
    // For every interval find all overlapping interval ahead such that all intervals start lies before all end of intervals(minEnd)
    private void solve_flaws(int n, int[][] intervals) {
        int maxCnt = 1;

        Arrays.sort(intervals, (a, b) -> {
            if(a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        for(int i=0; i<n; i++) {
            int cnt = 1;
            int minEnd = intervals[i][1];
            for(int j=i+1; j<n; j++) {
                if(intervals[j][0] <= intervals[i][1]) {
                    if(intervals[j][0] <= minEnd) {
                        cnt++;
                        minEnd = Math.min(minEnd, intervals[j][1]);
                    }
                    else {
                        maxCnt = Math.max(maxCnt, cnt);
                        minEnd = intervals[j][1];
                        cnt = 2; // i and j are already overlaped.. that is why set to 2
                    }
                }
                else break;
            }
            maxCnt = Math.max(maxCnt, cnt);
        }

        out.println(maxCnt);
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
