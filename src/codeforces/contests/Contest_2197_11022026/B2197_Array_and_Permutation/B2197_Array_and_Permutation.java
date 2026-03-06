package codeforces.contests.Contest_2197_11022026.B2197_Array_and_Permutation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class B2197_Array_and_Permutation {
    static boolean LOCAL = System.getProperty("ONLINE_JUDGE") == null;

    static InputStream inputStream;
    static OutputStream outputStream;
    static FastScanner in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        inputStream = LOCAL
                ? new FileInputStream(
                        "src/codeforces/contests/Contest_2197_11022026/B2197_Array_and_Permutation/input.txt")
                : System.in;
        outputStream = LOCAL
                ? new FileOutputStream(
                        "src/codeforces/contests/Contest_2197_11022026/B2197_Array_and_Permutation/output.txt")
                : System.out;
        in = new FastScanner(inputStream);
        out = new PrintWriter(outputStream);
        B2197_Array_and_Permutation solver = new B2197_Array_and_Permutation();

        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] p = new int[n];
            for (int i = 0; i < n; i++) {
                p[i] = in.nextInt();
            }

            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }

            solver.solve(n, p, a);
        }

        out.flush();
    }

    // private void solve(int n, int[] p, int[] a) {
    //     for(int i=0; i<n; i++) {
    //         if(p[i] == a[i]) continue;

    //         int il = i-1;
    //         boolean leftPossible = false;
    //         while(il >= 0) {
    //             if(a[i] == p[il]) {
    //                 leftPossible = true;
    //                 break;
    //             }

    //             if(a[i] != a[il]) {
    //                 break;
    //             }

    //             il--;
    //         }

    //         if(leftPossible) continue;

    //         int ir = i+1;
    //         boolean rightPossible = false;
    //         while(ir < n) {
    //             if(a[i] == p[ir]) {
    //                 rightPossible = true;
    //                 break;
    //             }

    //             if(a[i] != a[ir]) {
    //                 break;
    //             }

    //             ir++;
    //         }

    //         if(rightPossible) continue;

    //         out.println("NO");
    //         return;
    //     }

    //     out.println("YES");
    // }

    private void solve(int n, int[] p, int[] a) {
        int[] posInP = new int[n + 1];
        for (int i = 0; i < n; i++) {
            posInP[p[i]] = i;
        }
        
        int lastPos = -1;
        boolean possible = true;

        lastPos = posInP[a[0]];

        for (int i = 1; i < n; i++) {
            if (a[i] != a[i - 1]) {
                int currentPos = posInP[a[i]];
                
                if (currentPos < lastPos) {
                    possible = false;
                    break;
                }
                lastPos = currentPos;
            }
        }

        out.println(possible ? "YES" : "NO");
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
