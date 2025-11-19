package cses.Flight_Routes;

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

public class FlightRoutes { 
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Flight_Routes/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Flight_Routes/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        FlightRoutes solver = new FlightRoutes();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();
            
            List<List<Pair<Integer, Integer>>> adjList = new ArrayList<>();
            for(int i=0; i<n+1; i++) {
                adjList.add(new ArrayList<>());
            }

            for(int i=0; i<m; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                int w = in.nextInt();

                adjList.get(u).add(new Pair<>(v, w));
            }

            solver.solve(n, k, adjList);
        }

        out.flush();
    }

    private void solve(int n, int k, List<List<Pair<Integer, Integer>>> adjList) {
        int src = 1;
        int dest = n;

        // Min-heap: [node, distance], sorted by distance
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.offer(new long[]{src, 0L});

        int[] count = new int[n + 1]; // count of how many times each node is processed
        List<Long> ans = new ArrayList<>();

        while (!pq.isEmpty() && ans.size() < k) {
            long[] cur = pq.poll();
            int u = (int) cur[0];
            long dist = cur[1];

            count[u]++;
            if (u == dest) {
                ans.add(dist);
                if (ans.size() == k) break;
            }

            if (count[u] <= k) { // Expand neighbors only if not exceeded k visits
                for (Pair<Integer, Integer> edge : adjList.get(u)) {
                    int v = edge.vertex;
                    int w = edge.weight;
                    pq.offer(new long[]{v, dist + w});
                }
            }
        }

        // Output result
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.size(); i++) {
            if (i > 0) sb.append(" ");
            sb.append(ans.get(i));
        }
        out.println(sb.toString());
    
    }


    // private void solve(int n, int k, List<List<Pair<Integer, Integer>>> adjList) {
    //     int src = 1;
    //     int dest = n;

    //     PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b-a);
    //     int[] pathVis = new int[n+1];
    //     dfs(src, dest, k, pathVis, 0, heap, adjList);

    //     String res = new String();
    //     while(!heap.isEmpty()) {
    //         res = heap.poll() + " " + res;
    //     }

    //     out.println(res);
    // }

    // private void dfs(int node, int dest, int k, int[] pathVis, int price, PriorityQueue<Integer> heap,
    // List<List<Pair<Integer, Integer>>> adjList) {
    //     pathVis[node] = 1;

    //     if(node == dest) {
    //         heap.add(price);
    //         if(heap.size() > k) {
    //             heap.poll();
    //         }
    //         pathVis[node] = 0;
    //         return;
    //     }

    //     for(Pair<Integer, Integer> neighbor: adjList.get(node)) {
    //         int neighborNode = neighbor.vertex;
    //         int wt = neighbor.weight;

    //         if(pathVis[neighborNode] == 0) {
    //             dfs(neighborNode, dest, k, pathVis, price+wt, heap, adjList);
    //         }
    //     }
        
    //     pathVis[node] = 0;
    // }

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
