package cses.Movie_Festival_II;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class MovieFestivalII {
    static boolean LOCAL = false;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Movie_Festival_II/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Movie_Festival_II/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        MovieFestivalII solver = new MovieFestivalII();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[][] intervals = new int[n][2];
            for (int i = 0; i < n; i++) {
                intervals[i][0] = in.nextInt();
                intervals[i][1] = in.nextInt();
            }
            solver.solve(n, k, intervals);
        }

        out.flush();
    }

    private void solve2(int n, int k, int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] != b[1])
                return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        int count = 0;
        for (int[] interval : intervals) {
            while (!queue.isEmpty() && queue.peek() < interval[0] - 1) {
                queue.poll();
            }

            if (queue.size() < k) {
                queue.offer(interval[1]);
                count++;
            } else {
                if (queue.peek() <= interval[0]) {
                    queue.poll();
                    queue.offer(interval[1]);
                    count++;
                }
            }
        }

        out.println(count);
    }

    // 
    public void solve(int n, int k, int[][] intervals) {
        // 1. Sort by end time (same as your code)
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] != b[1])
                return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });

        // 2. Use TreeMap to store member finish times
        // Key: Finish Time, Value: Count of members finishing at this time
        TreeMap<Integer, Integer> members = new TreeMap<>();

        // Initially, all k members are free at time 0
        members.put(0, k);

        int count = 0;

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            // Find the member with the largest finish time <= start time
            // floorKey returns the greatest key less than or equal to the given key
            Integer bestMemberFinishTime = members.floorKey(start);

            if (bestMemberFinishTime != null) {
                count++;

                // Remove/Decrement the member from their old finish time
                int currentCount = members.get(bestMemberFinishTime);
                if (currentCount == 1) {
                    members.remove(bestMemberFinishTime);
                } else {
                    members.put(bestMemberFinishTime, currentCount - 1);
                }

                // Update member to new finish time (increment count for new time)
                members.put(end, members.getOrDefault(end, 0) + 1);
            }
        }

        out.println(count);
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
