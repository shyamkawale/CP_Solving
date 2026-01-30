package cses.Trees.T1_Subordinates;

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
https://cses.fi/problemset/task/1674

Given the structure of a company, your task is to calculate for each employee the number of their subordinates.

Input
The first input line has an integer n: the number of employees. 
The employees are numbered 1,2,..,n, and employee 1 is the general director of the company.
After this, there are n-1 integers: for each employee 2,3,...,n their direct boss in the company.

Output
Print n integers: for each employee 1,2,..,n the number of their subordinates.
*/
public class Subordinates {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Trees/T1_Subordinates/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Trees/T1_Subordinates/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        Subordinates solver = new Subordinates();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();

            List<List<Integer>> adjList = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                adjList.add(new ArrayList<>());
            }

            for (int v = 2; v <= n; v++) {
                int u = in.nextInt();

                adjList.get(u).add(v);
            }

            solver.solve(n, adjList);
        }

        out.flush();
    }

    private void solve(int n, List<List<Integer>> adjList) {
        int[] subordinates = new int[n+1];
        dfs(1, subordinates, adjList);

        for(int i=1; i<=n; i++) {
            out.print(subordinates[i] + " ");
        }
    }

    private int dfs(int curr, int[] subordinates, List<List<Integer>> adjList) {
        int subs = 0;
        for(int neighbor: adjList.get(curr)) {
            subs = subs + dfs(neighbor, subordinates, adjList);
        }

        subordinates[curr] = subs;
        return subs+1;
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

