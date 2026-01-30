package cses.Trees.T4_Tree_Distances_I;

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
https://cses.fi/problemset/task/1132

You are given a tree consisting of n nodes.
Your task is to determine for each node the maximum distance to another node.

Input
The first input line contains an integer n: the number of nodes. The nodes are numbered 1,2,...,n.
Then there are n-1 lines describing the edges. Each line contains two integers a and b: there is an edge between nodes a and b.

Output
Print n integers: for each node 1,2,..,n, the maximum distance to another node.
*/
public class TreeDistancesI {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Trees/T4_Tree_Distances_I/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Trees/T4_Tree_Distances_I/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        TreeDistancesI solver = new TreeDistancesI();

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

    private void solve(int n, List<List<Integer>> adjList) {

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
