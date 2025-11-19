package cses.Flight_Routes;

import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int to;
        int w;
        Edge(int t, int w) {
            this.to = t;
            this.w = w;
        }
    }

    static class State implements Comparable<State> {
        int node;
        long dist;
        State(int node, long dist) {
            this.node = node;
            this.dist = dist;
        }
        public int compareTo(State o) {
            return Long.compare(this.dist, o.dist);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Edge>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, c));
        }

        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.add(new State(1, 0));
        int[] count = new int[n + 1];
        List<Long> ans = new ArrayList<>();

        while (!pq.isEmpty() && ans.size() < k) {
            State cur = pq.poll();
            int u = cur.node;
            long dist = cur.dist;

            count[u]++;
            if (u == n) {
                ans.add(dist);
                if (ans.size() == k) break;
            }
            if (count[u] <= k) {
                for (Edge e : graph[u]) {
                    pq.add(new State(e.to, dist + e.w));
                }
            }
        }

        for (int i = 0; i < ans.size(); i++) {
            if (i > 0) out.print(" ");
            out.print(ans.get(i));
        }
        out.println();
        out.flush();
    }
}
