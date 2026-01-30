package cses.Sorting_And_Searching.SAS14_Towers;

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
https://cses.fi/problemset/task/1073

You are given n cubes in a certain order, and your task is to build towers using them. 
Whenever two cubes are one on top of the other, the upper cube must be smaller than the lower cube.
You must process the cubes in the given order. 
You can always either place the cube on top of an existing tower, or begin a new tower.

What is the minimum possible number of towers?
*/
public class Towers {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Sorting_And_Searching/SAS14_Towers/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Sorting_And_Searching/SAS14_Towers/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        Towers solver = new Towers();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int[] cubes = new int[n];
            for (int i = 0; i < n; i++) {
                cubes[i] = in.nextInt();
            }
            solver.solve(n, cubes);
        }

        out.flush();
    }

    private void solve(int n, int[] cubes) {
        List<Integer> towers = new ArrayList<>(); // sorted

        for (int elem : cubes) {
            if (towers.isEmpty()) {
                towers.add(elem);
            } else {
                int ceilTower = binarySearch(towers, elem);

                if (ceilTower == -1) {
                    towers.add(elem);
                } else {
                    towers.set(ceilTower, elem);
                }
            }
        }

        out.println(towers.size());
    }

    private int binarySearch(List<Integer> towers, int elem) {
        int left = 0;
        int right = towers.size() - 1;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (towers.get(mid) <= elem) {
                left = mid + 1;
            } else {
                right = mid - 1;
                ans = mid;
            }
        }

        return ans;
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
