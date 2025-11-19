package cses.FlightDiscount;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class FlightDiscount {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/FlightDiscount/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/FlightDiscount/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        FlightDiscount solver = new FlightDiscount();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();

            List<List<Pair<Integer, Integer>>> adjList = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                adjList.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                int w = in.nextInt();

                adjList.get(u).add(new Pair<>(v, w));
            }

            solver.solve(n, adjList);
        }

        out.flush();
    }

    private void solve(int n, List<List<Pair<Integer, Integer>>> adjList) {
        int src = 1;
        int dest = n;
        // v, wt till now, maxWt
        PriorityQueue<long[]> queue = new PriorityQueue<>((a, b) -> {
            long aDiscountedPrice = a[1] - a[2] + a[2]/2;
            long bDiscountedPrice = b[1] - b[2] + b[2]/2;

            return Long.compare(aDiscountedPrice, bDiscountedPrice);
        });
        queue.offer(new long[]{src, 0, 0});

        while(!queue.isEmpty()) {
            long[] polledNode = queue.poll();
            int currNode = (int)polledNode[0];
            long currWt = polledNode[1];
            long maxWt = polledNode[2];

            if(currNode == dest) {
                out.println(currWt - maxWt + maxWt/2);
                break;
            }

            for(Pair<Integer, Integer> neighbor: adjList.get(currNode)) {
                int neighborNode = neighbor.vertex;
                int wt = neighbor.weight;
                queue.offer(new long[]{neighborNode, currWt+wt, Math.max(maxWt, wt)});
            }
        }
    }

    static class Pair<U, W> {
        public final U vertex;
        public final W weight;

        public Pair(U vertex, W weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
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
