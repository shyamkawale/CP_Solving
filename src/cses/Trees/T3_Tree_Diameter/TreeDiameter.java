package cses.Trees.T3_Tree_Diameter;

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
https://cses.fi/problemset/task/1131

You are given a tree consisting of n nodes.
The diameter of a tree is the maximum distance between two nodes. 
Your task is to determine the diameter of the tree.

Input
The first input line contains an integer n: the number of nodes. The nodes are numbered 1,2,..,n.
Then there are n-1 lines describing the edges. Each line contains two integers a and b: there is an edge between nodes a and b.

Output
Print one integer: the diameter of the tree.
*/
public class TreeDiameter {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Trees/T3_Tree_Diameter/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Trees/T3_Tree_Diameter/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        TreeDiameter solver = new TreeDiameter();

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

            solver.solve(n, adjList);
        }

        out.flush();
    }

    int maxDiameter = 0;
    private void solve(int n, List<List<Integer>> adjList) {
        helper(1, -1, adjList);

        out.println(maxDiameter);
    }

    private int helper(int node, int parent, List<List<Integer>> adjList) {
        int maxHeight1 = 0;
        int maxHeight2 = 0;

        for(int neighbor: adjList.get(node)) {
            if(neighbor == parent) continue;

            int height = helper(neighbor, node, adjList);

            maxHeight2 = Math.max(maxHeight2, Math.min(maxHeight1, height));
            maxHeight1 = Math.max(maxHeight1, height);
        }

        maxDiameter = Math.max(maxDiameter, maxHeight1 + maxHeight2);

        return Math.max(maxHeight1, maxHeight2) + 1;
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
