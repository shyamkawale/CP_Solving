package cses.Range_Queries.RQ5_Range_Xor_Queries;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
https://cses.fi/problemset/task/1650

Given an array of n integers, your task is to process q queries of the form: 
what is the xor sum of values in range [a,b]?

*/
public class RangeXorQueries {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Range_Queries/Range_Xor_Queries/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Range_Queries/Range_Xor_Queries/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        RangeXorQueries solver = new RangeXorQueries();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int q = in.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = in.nextInt();
            }
            int[][] queries = new int[q][2];
            for (int i = 0; i < q; i++) {
                queries[i][0] = in.nextInt();
                queries[i][1] = in.nextInt();
            }
            solver.solve(n, q, nums, queries);
        }

        out.flush();
    }

    // private void solve(int n, int q, int[] nums, int[][] queries) {
    //     int[] px = new int[n+1];
    //     px[0] = 0;
    //     for(int i=0; i<n; i++) {
    //         px[i+1] = px[i] ^ nums[i];
    //     }

    //     for(int[] query: queries) {
    //         int l = query[0]-1;
    //         int r = query[1]-1;

    //         int rangeXor = px[r+1] ^ px[l];

    //         out.println(rangeXor);
    //     }
    // }

    private void solve(int n, int q, int[] nums, int[][] queries) {
        FenwickTree fenwickTree = new FenwickTree(nums);

        for (int[] query : queries) {
            int l = query[0] - 1; // 1-based that's why subtracted 1
            int r = query[1] - 1; // 1-based that's why subtracted 1

            long rangeXor = fenwickTree.rangeXor(l, r);

            out.println(rangeXor);
        }
    }

    public class FenwickTree {
        private long[] tree;

        public FenwickTree(int n) {
            tree = new long[n];
        }

        public FenwickTree(int[] nums) {
            tree = new long[nums.length];
            // Copy Initial Data
            for (int i = 0; i < nums.length; i++) {
                tree[i] = nums[i];
            }
            for (int i = 0; i < tree.length; i++) {
                int parent = i | (i + 1);
                if (parent < tree.length) {
                    tree[parent] ^= tree[i]; // Propagate sum to parent immediately
                }
            }
        }

        // Adds element of index 'i'
        // Time: O(log N)
        public void update(int idx, int delta) {
            while (idx < tree.length) {
                tree[idx] ^= delta;
                idx = idx | (idx + 1);
            }
        }

        // Returns prefix sum from 1 to idx
        // TC: O(log N)
        public long prefix(int idx) {
            long sum = 0;
            while (idx >= 0) {
                sum ^= tree[idx];
                idx = (idx & (idx+1)) - 1; // Move to parent range
            }
            return sum;
        }

        // Returns sum of range [L, R]
        public long rangeXor(int L, int R) {
            if (L > R) return 0;
            return prefix(R) ^ (L > 0 ? prefix(L-1) : 0);
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










