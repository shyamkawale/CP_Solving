package codeforces.problems.C2189_XOR_Convenience;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/* QUESTION:
 * Given a natural number n.
 * Find a permutation p of length n, such that for each i (2≤i≤n−1) there exists a j (i≤j≤n) such that pi = pj ^ i.
 *
 * It can be proven that under the constraints of the problem, there exists at least one suitable permutation p.
 *
 * A permutation of length n is an array consisting of n distinct integers from 1 to n in arbitrary order.
*/

/* 
 * The Core Idea: "All Roads Lead to Rome"
 * 
 * The problem asks us to find a permutation p where for every index i 
 * (from 2 to n-1), the value p[i] ⊕ i points to another value p[j] 
 * located at a later position (j ≥ i).
 * 
 * Instead of creating a messy web of pointers, we can use a Greedy Strategy:
 * Let's force every single index i to point to the very last element, index n.
 * 
 * The Target: We place the value 1 at the very last index n. So, p[n] = 1.
 * 
 * The Condition: For every other index i, we want:
 *   p[i] ⊕ i = p[n]
 *   p[i] ⊕ i = 1
 * 
 * The Solution: Using XOR algebra (a ⊕ b = c ⟺ a = c ⊕ b):
 *   p[i] = i ⊕ 1
 * 
 * Since p[n] is at the end (j=n), the condition j ≥ i is automatically satisfied!
 * 
 * Step-by-Step Construction for n=6:
 * 1. Set the anchor: p[6] = 1
 * 2. Fill the middle (i from 2 to n-1):
 *    i=2 → p[2] = 2 ⊕ 1 = 3
 *    i=3 → p[3] = 3 ⊕ 1 = 2
 *    i=4 → p[4] = 4 ⊕ 1 = 5
 *    i=5 → p[5] = 5 ⊕ 1 = 4
 * 3. Fill the first element: p[1] = 6 (remaining unused value)
 * 
 * Final Result: [6, 3, 2, 5, 4, 1]
 * 
 * Verification:
 *   At i=2: p[2] ⊕ 2 = 3 ⊕ 2 = 1 (Found at index 6 ✓)
 *   At i=4: p[4] ⊕ 4 = 5 ⊕ 4 = 1 (Found at index 6 ✓)
 */
public class C2189_XOR_Convenience {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL ? new FileInputStream("src/codeforces/problems/C2189_XOR_Convenience/input.txt") : System.in;
        outputStream = LOCAL ? new FileOutputStream("src/codeforces/problems/C2189_XOR_Convenience/output.txt") : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        C2189_XOR_Convenience solver = new C2189_XOR_Convenience();

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();

            solver.solve(n);
        }

        out.flush();
    }

    private void solve(int n) {
        int[] p = new int[n+1]; // n+1 becoz 1 based indexing given in problem
        boolean[] vis = new boolean[n+1];

        p[n] = 1;
        vis[1] = true;
        for(int i=2; i<n; i++) {
            p[i] = i ^ 1;
            vis[p[i]] = true;
        }

        for(int i=1; i<=n; i++) {
            if(!vis[i]) {
                p[1] = i;
                break;
            }
        }

        for(int i=1; i<=n; i++) {
            out.print(p[i] + " ");
        }
        out.println();
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
