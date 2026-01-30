package cses.Trees.T2_Tree_Matching;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
https://cses.fi/problemset/task/1130

You are given a tree consisting of n nodes.
A matching is a set of edges where each node is an endpoint of at most one edge. 
What is the maximum number of edges in a matching?

Input
The first input line contains an integer n: the number of nodes. 
The nodes are numbered 1,2,..,n.
Then there are n-1 lines describing the edges. 
Each line contains two integers a and b: there is an edge between nodes a and b.

Output
Print one integer: the maximum number of pairs.
*/

@SuppressWarnings("unused")
public class TreeMatching {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Trees/T2_Tree_Matching/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Trees/T2_Tree_Matching/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        TreeMatching solver = new TreeMatching();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            List<List<Integer>> adjList = new ArrayList<>();

            for(int i=0; i<n+1; i++) {
                adjList.add(new ArrayList<>());
            }

            for(int i=0; i<n-1; i++) {
                int u = in.nextInt();
                int v = in.nextInt();

                adjList.get(u).add(v);
                adjList.get(v).add(u);
            }

            // solver.solve_rec(n, adjList);
            // solver.solve_memo(n, adjList);
            solver.solve_greedy(n, adjList);
        }

        out.flush();
    }


    private void solve_rec(int n, List<List<Integer>> adjList) {
        int maxMatching = Math.max(helper1(1, 0, -1, adjList), helper1(1, 1, -1, adjList));

        out.println(maxMatching);
    }

    private int helper1(int node, int isSelected, int parent, List<List<Integer>> adjList) {
        
        if(isSelected == 0) {
            int edgesWhenRootUnselected = 0;
            for(int neighbor: adjList.get(node)) {
                if(neighbor == parent) continue;
                edgesWhenRootUnselected = edgesWhenRootUnselected + helper1(neighbor, 1, node, adjList);
            }

            return edgesWhenRootUnselected;
        }
        else {
            int edgesWhenRootSelected = 0;
            for(int neighbor: adjList.get(node)) {
                if(neighbor == parent) continue;
                int totalEdges = 0;
                for(int neighbor2: adjList.get(node)) {
                    if(neighbor2 == parent) continue;

                    if(neighbor2 == neighbor) {
                        totalEdges = totalEdges + 1 + helper1(neighbor2, 0, node, adjList);
                    }
                    else {
                        totalEdges = totalEdges + helper1(neighbor2, 1, node, adjList);
                    }
                }
                edgesWhenRootSelected = Math.max(edgesWhenRootSelected, totalEdges);
            }

            return edgesWhenRootSelected;
        }
    }

    private void solve_memo(int n, List<List<Integer>> adjList) {
        int[][] dp = new int[n+1][2];
        for(int i=0; i<n+1; i++) {
            dp[i][0] = -1;
            dp[i][1] = -1;
        }
        int maxMatching = Math.max(helper2(1, 0, -1, adjList, dp), helper2(1, 1, -1, adjList, dp));

        out.println(maxMatching);
    }

    private int helper2(int node, int isSelected, int parent, List<List<Integer>> adjList, int[][] dp) {
        if(dp[node][isSelected] != -1) {
            return dp[node][isSelected];
        }

        if(isSelected == 0) {
            int edgesWhenRootUnselected = 0;
            for(int neighbor: adjList.get(node)) {
                if(neighbor == parent) continue;
                edgesWhenRootUnselected = edgesWhenRootUnselected + helper2(neighbor, 1, node, adjList, dp);
            }

            dp[node][isSelected] = edgesWhenRootUnselected;
            return dp[node][isSelected];
        }
        else {
            int edgesWhenRootSelected = 0;
            for(int neighbor: adjList.get(node)) {
                if(neighbor == parent) continue;
                int totalEdges = 0;
                for(int neighbor2: adjList.get(node)) {
                    if(neighbor2 == parent) continue;

                    if(neighbor2 == neighbor) {
                        totalEdges = totalEdges + 1 + helper2(neighbor2, 0, node, adjList, dp);
                    }
                    else {
                        totalEdges = totalEdges + helper2(neighbor2, 1, node, adjList, dp);
                    }
                }
                edgesWhenRootSelected = Math.max(edgesWhenRootSelected, totalEdges);
            }

            dp[node][isSelected] = edgesWhenRootSelected;
            return dp[node][isSelected];
        }
    }

    /*
     * Greedy Solution
     * 
     * The greedy intuition is simple: Always match a leaf node with its parent if you can.
     * 
     * Why it works:
     * - A leaf node has only one edge connecting it to the rest of the tree (the edge to its parent).
     * - If we don't use this edge to match the leaf, the leaf will never be part of any matching 
     *   (since it has no children to match with).
     * - By matching the leaf with its parent immediately, we secure 1 matching edge. This "consumes" 
     *   the parent, but that is acceptable because the parent could at best contribute 1 edge to the 
     *   answer anyway (by matching with the grandparent). Using the edge (Leaf, Parent) is never 
     *   worse than saving the parent for later.
     * 
     * Algorithm:
     * 1. Perform a Post-Order Traversal (DFS) from the leaves up to the root.
     * 2. Maintain a boolean array visited[] to track matched nodes.
     * 3. For every node u, iterate through its children v.
     * 4. If a child v is not matched (visited), match u with v.
     * 5. Mark both u and v as visited and increment the count.
     * 6. Ignore the root's status at the very end (if it's unmatched, it stays unmatched).
     */
    int ans;
    private void solve_greedy(int n, List<List<Integer>> adjList) {
        boolean[] vis = new boolean[n+1];
        ans = 0;

        dfs(1, -1, vis, adjList);
        out.println(ans);
    }

    private void dfs(int node, int parent, boolean[] vis, List<List<Integer>> adjList) {
        for (int neighbor : adjList.get(node)) {
            if (neighbor == parent) continue;
            dfs(neighbor, node, vis, adjList);
            
            // Greedy check: If child 'v' is not matched yet, match 'u' with 'v'
            if (!vis[neighbor] && !vis[node]) {
                vis[neighbor] = true;
                vis[node] = true;
                ans++;
            }
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
