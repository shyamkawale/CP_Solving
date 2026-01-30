package cses.Sorting_And_Searching.SAS15_Traffic_Lights;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

/*
https://cses.fi/problemset/task/1163

There is a street of length x whose positions are numbered 0,1,...,x. 
Initially there are no traffic lights, but n sets of traffic lights are added to the street one after another.
Your task is to calculate the length of the longest passage without traffic lights after each addition.
*/
/**
 * TrafficLights solves the traffic lights problem from CSES.
 * 
 * This class implements a dynamic segment tracking solution that maintains the largest
 * gap between traffic lights as new lights are added on a street of length x.
 * 
 * Algorithm:
 * 1. Initialize with two lights at positions 0 and x, creating one segment of length x
 * 2. For each new light added at position p:
 *    - Find the left and right neighboring lights using floor/ceiling operations
 *    - Remove the old segment (right - left) from the segment frequency map
 *    - Add two new segments created by splitting at position p
 *    - Output the largest segment length after each addition
 * 3. Uses TreeSet for efficient light position management and TreeMap for segment tracking
 * 
 * Data Structures:
 * - TreeSet<Integer> lights: Maintains sorted positions of traffic lights
 * - TreeMap<Integer, Integer> segments: Tracks segment lengths and their frequencies
 * 
 * Time Complexity: O(n log n) where n is the number of lights
 * - Each light addition requires O(log n) operations for TreeSet/TreeMap
 * 
 * Space Complexity: O(n) for storing lights and segments
 * 
 * @author ShyamKawale
 * @version 1.0
 */
public class TrafficLights {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Sorting_And_Searching/SAS15_Traffic_Lights/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Sorting_And_Searching/SAS15_Traffic_Lights/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        TrafficLights solver = new TrafficLights();

        int t = 1;
        while (t-- > 0) {
            int x = in.nextInt();
            int n = in.nextInt();
            int[] pos = new int[n];
            for (int i = 0; i < n; i++) {
                pos[i] = in.nextInt();
            }
            solver.solve(x, n, pos);
        }

        out.flush();
    }

    private void solve(int x, int n, int[] pos) {
        // segment points
        TreeSet<Integer> lights = new TreeSet<>();
        lights.add(0);
        lights.add(x);

        // segment distance with its frequency..
        TreeMap<Integer, Integer> segments = new TreeMap<>();
        segments.put(x, 1);

        for (int i = 0; i < n; i++) {
            int p = pos[i];

            // find neighbors
            Integer left = lights.floor(p);
            Integer right = lights.ceiling(p);

            // remove old segment
            int oldSeg = right - left;
            segments.computeIfPresent(oldSeg, (k, v) -> v == 1 ? null : v - 1);

            // add two new segments
            int seg1 = p - left;
            int seg2 = right - p;

            segments.put(seg1, segments.getOrDefault(seg1, 0) + 1);
            segments.put(seg2, segments.getOrDefault(seg2, 0) + 1);

            // add new light
            lights.add(p);

            // print largest segment
            out.print(segments.lastKey() + " ");
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
