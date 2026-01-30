package cses.Sorting_And_Searching.SAS19_Josephus_Problem_II;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
https://cses.fi/problemset/task/2163

Consider a game where there are n children (numbered 1,2,...,n) in a circle. 
During the game, repeatedly k children are skipped and one child is removed from the circle. 
In which order will the children be removed?
*/
public class JosephusProblemII {
    static boolean LOCAL = true;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/cses/Sorting_And_Searching/SAS19_Josephus_Problem_II/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/cses/Sorting_And_Searching/SAS19_Josephus_Problem_II/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        JosephusProblemII solver = new JosephusProblemII();

        int t = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();

            solver.solve_Queue(n, k);
            solver.solve_FenwickTree(n, k);
        }

        out.flush();
    }

    private void solve_Queue(int n, int k) {
        Queue<Integer> queue = new ArrayDeque<>();

        for(int i=1; i<=n; i++) {
            queue.offer(i);
        }

        while(!queue.isEmpty()) {
            for(int i=0; i<k; i++) {
                int miss = queue.poll();
                queue.offer(miss);
            }
            out.print(queue.poll() + " ");
        }

        out.println();
    }

    private void solve_FenwickTree(int n, int k) {
        // Build a Fenwick Tree (BIT)
        // bit[i] stores the sum of active people in range
        FenwickTree ft = new FenwickTree(n);
        
        // Initially, all N people are present (add 1 at every index)
        for (int i = 1; i <= n; i++) {
            ft.add(i, 1);
        }

        int currentRank = 0; // 0-indexed rank among REMAINING people

        for (int remaining = n; remaining > 0; remaining--) {
            // Calculate which 'rank' we need to remove next.
            // We use modulo arithmetic to skip K people efficiently without looping.
            currentRank = (currentRank + k) % remaining;

            // Find the actual original index of the person with this rank
            // Note: Our rank is 0-based, so we look for the (currentRank + 1)-th person
            int indexToRemove = ft.findKth(currentRank + 1);

            out.print(indexToRemove + " ");

            // Remove this person (set their value to 0 in the tree, so subtract 1)
            ft.add(indexToRemove, -1);
            
            // Note: We do NOT update currentRank here explicitly because the 
            // sequence "shifts" automatically when an element is removed. 
            // The next person naturally falls into the 'currentRank' slot relative to the new array.
        }
        out.println();
    }

    // Standard Fenwick Tree Implementation
    static class FenwickTree {
        int[] tree;
        int size;

        public FenwickTree(int n) {
            this.size = n;
            this.tree = new int[n + 1];
        }

        public void add(int index, int delta) {
            while (index <= size) {
                tree[index] += delta;
                index += index & (-index);
            }
        }

        public int query(int index) {
            int sum = 0;
            while (index > 0) {
                sum += tree[index];
                index -= index & (-index);
            }
            return sum;
        }

        // Binary lifting to find the index with the given rank in O(log N)
        // This finds the smallest index 'idx' such that query(idx) == k
        public int findKth(int k) {
            int idx = 0;
            // Iterate bits from highest to lowest (powers of 2)
            for (int i = Integer.highestOneBit(size); i > 0; i /= 2) {
                int nextIdx = idx + i;
                if (nextIdx <= size && tree[nextIdx] < k) {
                    idx = nextIdx;
                    k -= tree[idx];
                }
            }
            return idx + 1;
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
