package cses.Range_Queries.Range_Update_Queries;

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
https://cses.fi/problemset/task/1651

Given an array of n integers, your task is to process q queries of the following types:

1. => increase each value in range [a,b] by u
2. => what is the value at position k?
*/
@SuppressWarnings("unused")
public class RangeUpdateQueries {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Range_Queries/Range_Update_Queries/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Range_Queries/Range_Update_Queries/output.txt")
                : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        RangeUpdateQueries solver = new RangeUpdateQueries();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int q = in.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = in.nextInt();
            }
            int[][] queries = new int[q][4];
            for (int i = 0; i < q; i++) {
                queries[i][0] = in.nextInt(); 
                queries[i][1] = in.nextInt();

                if(queries[i][0] == 1) {
                    queries[i][2] = in.nextInt();
                    queries[i][3] = in.nextInt();
                }
            }
            solver.solve4(n, q, nums, queries);
        }

        out.flush();
    }

    private void solve4(int n, int q, int[] nums, int[][] queries) {
        FenwickTree fenwickTree = new FenwickTree(new int[n+1]);

        for(int[] query: queries) {
            if(query[0] == 1) {
                int l = query[1]-1;
                int r = query[2]-1;
                int u = query[3];

                fenwickTree.update(l, u);
                fenwickTree.update(r+1, -u);
            }
            else {
                int k = query[1]-1;

                long prefixDiffSum = fenwickTree.prefix(k);

                long updatedNum = prefixDiffSum + nums[k];
                out.println(updatedNum);
            }
        }


    }

    private void solve3(int n, int q, int[] nums, int[][] queries) {
        long[] diff = new long[n+1];

        for(int[] query: queries) {
            if(query[0] == 1) {
                int l = query[1]-1;
                int r = query[2]-1;
                int u = query[3];

                diff[l] = diff[l] + (long) u;
                diff[r+1] = diff[r+1] - (long) u;
            }
            else {
                int k = query[1]-1;

                long currentDiff = 0;
                for(int i=0; i<=k; i++) {
                    currentDiff = currentDiff + diff[i];
                }

                long updatedNum = currentDiff + nums[k];
                out.println(updatedNum);
            }
        }

    }

    private void solve2(int n, int q, int[] nums, int[][] queries) {
        List<int[]> updationList = new ArrayList<>();
        for(int[] query: queries) {
            if(query[0] == 1) {
                int l = query[1]-1;
                int r = query[2]-1;
                int u = query[3];
                
                updationList.add(new int[]{l, r, u});
            }
            else {
                int k = query[1]-1;
                long ans = nums[k];
                for(int[] upd: updationList) {
                    if(k >= upd[0] && k <= upd[1]) {
                        ans = ans + (long) upd[2];
                    }
                }
                out.println(ans);
            }
        }
    }

    private void solve1(int n, int q, int[] nums, int[][] queries) {
        for(int[] query: queries) {
            if(query[0] == 1) {
                int l = query[1]-1;
                int r = query[2]-1;
                int u = query[3];
                for(int i=l; i<=r; i++) {
                    nums[i] = nums[i] + u;
                }
            }
            else {
                int k = query[1]-1;
                out.println(nums[k]);
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
