package cses.Range_Queries.RQ3_Dynamic_Range_Sum_Queries;
// package cses.Range_Queries.Dynamic_Range_Sum_Queries;

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
https://cses.fi/problemset/task/1648
Given an array of n integers, your task is to process q queries of the following types:

1. => update the value at position k to u
2. => what is the sum of values in range [a,b]?

*/
public class DynamicRangeSumQueries {
    static boolean LOCAL = false;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Range_Queries/Dynamic_Range_Sum_Queries/input.txt")
                : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Range_Queries/Dynamic_Range_Sum_Queries/output.txt")
                : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        DynamicRangeSumQueries solver = new DynamicRangeSumQueries();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int q = in.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = in.nextInt();
            }
            int[][] queries = new int[q][3];
            for (int i = 0; i < q; i++) {
                queries[i][0] = in.nextInt();
                queries[i][1] = in.nextInt();
                queries[i][2] = in.nextInt();
            }
            solver.solve(n, q, nums, queries);
        }

        out.flush();
    }

    private void solve(int n, int q, int[] nums, int[][] queries) {
        FenwickTree fenwickTree = new FenwickTree(nums);

        for (int[] query : queries) {
            int queryType = query[0];

            if(queryType == 1) {
                int k = query[1] - 1; /// 1-based that's why subtracted 1
                int newVal = query[2];
                int oldVal = nums[k];

                int delta = newVal - oldVal; // Delta (newVal <INVERSE_OP> oldVal)
                fenwickTree.update(k, delta);

                nums[k] = newVal;
            }
            else {
                int l = query[1] - 1; // 1-based that's why subtracted 1
                int r = query[2] - 1; // 1-based that's why subtracted 1

                long rangeSum = fenwickTree.rangeSum(l, r);

                out.println(rangeSum);
            }
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
                    tree[parent] += tree[i]; // Propagate sum to parent immediately
                }
            }
        }

        // Adds element of index 'i'
        // Time: O(log N)
        public void update(int idx, int delta) {
            while (idx < tree.length) {
                tree[idx] += delta;
                idx = idx | (idx + 1);
            }
        }

        // Returns prefix sum from 1 to idx
        // TC: O(log N)
        public long prefix(int idx) {
            long sum = 0;
            while (idx >= 0) {
                sum += tree[idx];
                idx = (idx & (idx+1)) - 1; // Move to parent range
            }
            return sum;
        }

        // Returns sum of range [L, R]
        public long rangeSum(int L, int R) {
            if (L > R) return 0;
            return prefix(R) - (L > 0 ? prefix(L-1) : 0);
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
